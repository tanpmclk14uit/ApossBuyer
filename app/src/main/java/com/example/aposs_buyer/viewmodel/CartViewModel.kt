package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aposs_buyer.model.*
import com.example.aposs_buyer.model.dto.CartDTO
import com.example.aposs_buyer.model.dto.DeliveryAddressDTO
import com.example.aposs_buyer.model.dto.OrderDTO
import com.example.aposs_buyer.model.dto.OrderItemDTO
import com.example.aposs_buyer.responsitory.AuthRepository
import com.example.aposs_buyer.responsitory.CartRepository
import com.example.aposs_buyer.responsitory.DeliveryAddressRepository
import com.example.aposs_buyer.responsitory.OrderRepository
import com.example.aposs_buyer.utils.LoadingState
import com.example.aposs_buyer.utils.OrderStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.DecimalFormat
import java.util.*
import java.util.stream.Collectors
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val authRepository: AuthRepository,
    private val orderRepository: OrderRepository,
    private val deliveryAddressRepository: DeliveryAddressRepository,
) : ViewModel() {

    private val _lstCartItem = MutableLiveData<ArrayList<CartItem>>()
    val lstCartItem: LiveData<ArrayList<CartItem>> get() = _lstCartItem

    private val _choseList = MutableLiveData<ArrayList<CartItem>>()
    val choseList: LiveData<ArrayList<CartItem>> get() = _choseList

    val total = MutableLiveData<String>()
    private val totalInt = MutableLiveData<Int>()
    val size = MutableLiveData<Int>()
    val choseSize = MutableLiveData<Int>()
    val defaultAddress = MutableLiveData<Address>()
    private val defaultAddressDTO = MutableLiveData<DeliveryAddressDTO>()

    var loadingStatus = MutableLiveData<LoadingState>()

    init {
        loadCartList()
        _lstCartItem.value = ArrayList()
        _choseList.value = ArrayList()
    }

    private fun calculateTotal(): String {
        return if (_lstCartItem.value != null) {
            var sum = 0
            for (i in 0 until _lstCartItem.value!!.size) {
                if (_lstCartItem.value!![i].isChoose) {
                    sum += _lstCartItem.value!![i].price * _lstCartItem.value!![i].amount
                }
            }
            totalInt.value = sum
            val formatter = DecimalFormat("#,###")
            val formattedNumber: String = formatter.format(sum)
            "$formattedNumber VNĐ"
        } else "0 VNĐ"
    }

    fun reCalculateTotal() {
        total.value = calculateTotal()
        size.value = _lstCartItem.value!!.size
    }

    fun setChoseSize() {
        choseSize.value = _choseList.value!!.size
    }

    fun removeItem(position: Int) {
        val cartItem = _lstCartItem.value!![position]
        _lstCartItem.value!!.removeAt(position)
        deleteCartItem(cartItem.id)
    }

    fun setNewChose() {
        _choseList.value = getChose()
    }

    private fun getChose(): ArrayList<CartItem> {
        val choseList: ArrayList<CartItem> = arrayListOf()
        for (i in 0 until _lstCartItem.value!!.size) {
            if (_lstCartItem.value!![i].isChoose)
                choseList.add(_lstCartItem.value!![i])
        }
        return choseList
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

    private var cartItemsDTO: List<CartDTO> = ArrayList()

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

    private fun updateCartItem(cartDTO: CartDTO) {
        viewModelScope.launch {
            val currentAccessToken = authRepository.getCurrentAccessTokenFromRoom()
            if (currentAccessToken != null) {
                val updateResponse =
                    cartRepository.updateCart(
                        currentAccessToken,
                        cartDTO
                    )
                if (updateResponse.isSuccessful) {
                    Log.d("cart", updateResponse.body().toString())
                    return@launch
                } else {
                    if (updateResponse.code() == 401) {
                        Log.d("cart", "Expire access token")
                        if (authRepository.loadNewAccessTokenSuccess()) {
                            updateCartItem(cartDTO)
                        }
                    }
                }
            }
        }
    }

    fun updateCart() {
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

    private fun makeNewOrder(): OrderDTO {
        val listOrderItemDTO = _choseList.value!!.stream().map {
            convertToOrderItemDTO(it)
        }.collect(Collectors.toList())
        return OrderDTO(
            id = -1L,
            orderTime = Calendar.getInstance().time,
            orderStatus = OrderStatus.Pending,
            orderItemDTOList = listOrderItemDTO,
            totalPrice = totalInt.value!!,
            address = defaultAddress.value!!.getFullAddress(),
            cancelReason = null
        )
    }

    fun addNewOrder() {
        viewModelScope.launch {
            val orderDTO = makeNewOrder()
            val currentAccessToken = authRepository.getCurrentAccessTokenFromRoom()
            if (currentAccessToken != null) {
                val response = orderRepository.orderService.addNewOrder(
                    orderDTO,
                    currentAccessToken
                )
                if (response.isSuccessful) {
                    Log.d("checkout", "done")
                    for (i: Int in 0 until choseList.value!!.size) {
                        deleteCartItem(choseList.value!![i].id)
                    }
                    _choseList.value = ArrayList()
                    return@launch
                } else {
                    if (response.code() == 401) {
                        if (authRepository.loadNewAccessTokenSuccess()) {
                            addNewOrder()
                        }
                    }
                }
            }
        }
    }

    private fun convertToOrderItemDTO(orderItem: CartItem): OrderItemDTO {
        return OrderItemDTO(
            id = 0,
            name = orderItem.name,
            property = orderItem.property,
            price = orderItem.price,
            imageUrl = orderItem.image.imgURL,
            quantity = orderItem.amount,
            product = orderItem.product,
        )
    }

    fun loadDefaultAddress() {
        viewModelScope.launch {
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
                    return@launch
                } else {
                    if (defaultAddressResponse.code() == 401) {
                        if (authRepository.loadNewAccessTokenSuccess()) {
                            loadDefaultAddress()
                        }
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

    fun loadCartList() {
        viewModelScope.launch {
            loadingStatus.value = LoadingState.Loading
            val currentAccessToken = authRepository.getCurrentAccessTokenFromRoom()
            if (!currentAccessToken.isNullOrBlank()) {
                val allCartItemsResponse =
                    cartRepository.getAllCart(currentAccessToken)
                if (allCartItemsResponse.isSuccessful) {
                    cartItemsDTO = allCartItemsResponse.body()!!
                    _lstCartItem.value = cartItemsDTO.stream().map {
                        toCartItem(it)
                    }.collect(Collectors.toList()).toCollection(ArrayList())
                    loadingStatus.value = LoadingState.Success
                    total.value = calculateTotal()
                    size.value = _lstCartItem.value!!.size
                    _choseList.value = getChose()
                    choseSize.value = _choseList.value!!.size
                    return@launch
                } else {
                    if (allCartItemsResponse.code() == 401) {
                        if (authRepository.loadNewAccessTokenSuccess()) {
                            loadCartList()
                        } else {
                            loadingStatus.value = LoadingState.Fail
                        }
                    }
                }
            }
        }
    }

    fun isHoldProductSuccess(): Boolean {
        var result = false
        loadingStatus.value = LoadingState.Loading
        val listOrderItemDTO = getChose().stream().map {
            convertToOrderItemDTO(it)
        }.collect(Collectors.toList())
        val currentAccessToken = authRepository.getCurrentAccessTokenFromRoom()
        if (!currentAccessToken.isNullOrBlank()) {
            runBlocking {
                val holdResponse = async {
                    orderRepository.orderService.holdProduct(
                        listOrderItemDTO,
                        currentAccessToken
                    )
                }
                runBlocking {
                    when (holdResponse.await().code()) {
                        200 -> {
                            result = true
                        }
                        401 -> {
                            if (authRepository.loadNewAccessTokenSuccess()) {
                                isHoldProductSuccess()
                            }
                        }
                        else -> {
                            result = false
                        }
                    }
                }
            }
        }
        return result
    }

    fun reduceHold() {
        viewModelScope.launch {
            loadingStatus.value = LoadingState.Loading
            val listOrderItemDTO = getChose().stream().map {
                convertToOrderItemDTO(it)
            }.collect(Collectors.toList())
            val currentAccessToken = authRepository.getCurrentAccessTokenFromRoom()
            if (!currentAccessToken.isNullOrBlank()) {
                val reduceHoldResponse =
                    orderRepository.orderService.reduceHold(
                        listOrderItemDTO,
                        currentAccessToken
                    )
                if (reduceHoldResponse.isSuccessful) {
                    loadingStatus.value = LoadingState.Success
                } else {
                    if (reduceHoldResponse.code() == 401) {
                        if (authRepository.loadNewAccessTokenSuccess()) {
                            isHoldProductSuccess()
                        } else {
                            loadingStatus.value = LoadingState.Fail
                        }

                    }
                }
            }
        }
    }
}