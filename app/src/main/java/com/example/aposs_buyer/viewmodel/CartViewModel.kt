package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aposs_buyer.model.*
import com.example.aposs_buyer.model.dto.CartDTO
import com.example.aposs_buyer.model.dto.DeliveryAddressDTO
import com.example.aposs_buyer.responsitory.AuthRepository
import com.example.aposs_buyer.responsitory.CartRepository
import com.example.aposs_buyer.responsitory.DeliveryAddressRepository
import com.example.aposs_buyer.utils.Converter
import com.example.aposs_buyer.utils.LoadingStatus
import com.example.aposs_buyer.utils.OrderStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.util.*
import java.util.stream.Collectors
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val authRepository: AuthRepository,
    private val deliveryAddressRepository: DeliveryAddressRepository,
) : ViewModel() {

    private var cartItemsDTO: List<CartDTO> = ArrayList()

    private val _lstCartItem = MutableLiveData<ArrayList<CartItem>>()
    val lstCartItem: LiveData<ArrayList<CartItem>> get() = _lstCartItem

    private val _choseList = MutableLiveData<ArrayList<CartItem>>()
    val choseList: LiveData<ArrayList<CartItem>> get() = _choseList

    var total = 0
    val totalCurrency = MutableLiveData<String>()
    val checkOutEnable = MutableLiveData<Boolean>()
    var loadingStatus = MutableLiveData<LoadingStatus>()

    //
    val defaultAddress = MutableLiveData<Address>()
    private val defaultAddressDTO = MutableLiveData<DeliveryAddressDTO>()


    init {
        loadCartList()
        _choseList.value = ArrayList()
    }

    fun loadCartList() {
        viewModelScope.launch {
            loadingStatus.value = LoadingStatus.Loading
            val currentAccessToken = authRepository.getCurrentAccessTokenFromRoom()
            if (!currentAccessToken.isNullOrBlank()) {
                val allCartItemsResponse =
                    cartRepository.getAllCart(currentAccessToken)
                if (allCartItemsResponse.isSuccessful) {
                    cartItemsDTO = allCartItemsResponse.body()!!
                    _lstCartItem.value = cartItemsDTO.stream().map {
                        toCartItem(it)
                    }.collect(Collectors.toList()).toCollection(ArrayList())
                    loadingStatus.value = LoadingStatus.Success
                    _choseList.value = getChoseCartItems()
                    trackingEnableCheckOutButton()
                    total = calculateTotal()
                    totalCurrency.value = Converter.convertFromIntToCurrencyString(total)
                    return@launch
                } else {
                    if (allCartItemsResponse.code() == 401) {
                        if (authRepository.loadNewAccessTokenSuccess()) {
                            loadCartList()
                        } else {
                            loadingStatus.value = LoadingStatus.Fail
                        }
                    }
                }
            }
        }
    }

    private fun calculateTotal(): Int {
        return if (choseList.value != null) {
            var sum = 0
            for (cartItem in _choseList.value!!) {
                sum += cartItem.amount * cartItem.price
            }
            return sum
        } else 0
    }

    fun reCalculateTotal() {
        total = calculateTotal()
        totalCurrency.value = Converter.convertFromIntToCurrencyString(total)
    }

    fun removeItem(position: Int) {
        val cartItem = _lstCartItem.value!![position]
        _lstCartItem.value!!.removeAt(position)
        deleteCartItem(cartItem.id)
    }

    private fun getChoseCartItems(): ArrayList<CartItem> {
        return _lstCartItem.value!!.stream().filter { it.isChoose }.collect(Collectors.toList())
            .toCollection(ArrayList())
    }

    fun trackingEnableCheckOutButton() {
        checkOutEnable.value = getChoseCartItems().isNotEmpty()
    }

    private fun toCartItem(cartDTO: CartDTO): CartItem {
        val image = Image(cartDTO.imageUrl)
        return CartItem(
            id = cartDTO.id,
            image = image,
            name = cartDTO.name,
            price = cartDTO.price,
            amount = cartDTO.quantity,
            property = cartDTO.property,
            isChoose = cartDTO.select,
            product = cartDTO.productId,
        )
    }


    private fun deleteCartItem(id: Long) {
        viewModelScope.launch {
            val currentAccessToken = authRepository.getCurrentAccessTokenFromRoom()
            if (currentAccessToken != null) {
                val deleteResponse =
                    cartRepository.deleteCart(
                        currentAccessToken,
                        id
                    )
                if (deleteResponse.isSuccessful) {
                    Log.d("cart", deleteResponse.body().toString())
                    loadCartList()
                    return@launch
                } else {
                    if (deleteResponse.code() == 401) {
                        Log.d("cart", "Expire access token")
                        if (authRepository.loadNewAccessTokenSuccess()) {
                            deleteCartItem(id)
                        }
                    }
                }
            }
        }
    }

    private suspend fun updateCartItem(cartDTO: CartDTO) {
        val currentAccessToken = authRepository.getCurrentAccessTokenFromRoom()
        if (currentAccessToken != null) {
            val updateResponse =
                cartRepository.updateCart(
                    currentAccessToken,
                    cartDTO
                )
            if (!updateResponse.isSuccessful) {
                if (updateResponse.code() == 401) {
                    if (authRepository.loadNewAccessTokenSuccess()) {
                        updateCartItem(cartDTO)
                    }
                }
            }
        }
    }

    fun updateCart() {
        runBlocking {
            withContext(NonCancellable) {
                for (cartItem: CartItem in _lstCartItem.value!!) {
                    for (cartItemDTO: CartDTO in cartItemsDTO) {
                        if (cartItemDTO.id == cartItem.id && (cartItem.isChoose != cartItemDTO.select || cartItem.amount != cartItemDTO.quantity)) {
                            cartItemDTO.select = cartItem.isChoose
                            cartItemDTO.quantity = cartItem.amount
                            updateCartItem(cartItemDTO)
                        }
                    }
                }
            }
        }
    }

    private fun convertToOrderBillingItem(cartItem: CartItem): OrderBillingItem {
        return OrderBillingItem(
            id = 0,
            name = cartItem.name,
            property = cartItem.property,
            price = cartItem.price,
            image = cartItem.image,
            amount = cartItem.amount,
            product = cartItem.product,
        )
    }

    fun makeNewOrder(): Order {
        runBlocking {
            loadDefaultAddress()
        }
        val orderBillingItem = _choseList.value!!.stream().map {
            convertToOrderBillingItem(it)
        }.collect(Collectors.toList())
        return Order(
            id = -1L,
            orderTime = Calendar.getInstance().time,
            status = OrderStatus.Pending,
            billingItems = orderBillingItem,
            totalPrice = total,
            address = defaultAddress.value!!.getFullAddress()
        )
    }

    private suspend fun loadDefaultAddress() {
        val currentAccessToken = authRepository.getCurrentAccessTokenFromRoom()
        if (!currentAccessToken.isNullOrBlank()) {
            val defaultAddressResponse =
                deliveryAddressRepository.getUserDefaultAddress(
                    currentAccessToken
                )
            if (defaultAddressResponse.isSuccessful) {
                val defaultAddressDTOResponseBody = defaultAddressResponse.body()
                defaultAddressDTO.value = defaultAddressDTOResponseBody!!
                defaultAddress.value =
                    convertDeliveryAddressDTOToAddress(defaultAddressDTO.value!!)
            } else {
                if (defaultAddressResponse.code() == 401) {
                    if (authRepository.loadNewAccessTokenSuccess()) {
                        loadDefaultAddress()
                    }
                }
            }
        }
    }


    private fun convertDeliveryAddressDTOToAddress(deliveryAddressDTO: DeliveryAddressDTO): Address {
        return Address(
            id = deliveryAddressDTO.id,
            name = deliveryAddressDTO.name,
            gender = deliveryAddressDTO.gender,
            phoneNumber = deliveryAddressDTO.phoneNumber,
            city = Province(deliveryAddressDTO.province.id, deliveryAddressDTO.province.name),
            district = District(
                deliveryAddressDTO.district.id,
                deliveryAddressDTO.district.name,
                deliveryAddressDTO.district.province
            ),
            ward = Ward(
                deliveryAddressDTO.ward.id,
                deliveryAddressDTO.ward.name,
                deliveryAddressDTO.ward.district
            ),
            isDefaultAddress = deliveryAddressDTO.isDefault,
            addressLane = deliveryAddressDTO.addressLane
        )
    }
}