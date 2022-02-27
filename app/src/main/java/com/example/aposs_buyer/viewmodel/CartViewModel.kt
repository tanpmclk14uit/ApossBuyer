package com.example.aposs_buyer.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.*
import com.example.aposs_buyer.model.dto.*
import com.example.aposs_buyer.responsitory.AuthRepository
import com.example.aposs_buyer.responsitory.CartRepository
import com.example.aposs_buyer.responsitory.DeliveryAddressRepository
import com.example.aposs_buyer.responsitory.OrderRepository
import com.example.aposs_buyer.responsitory.database.AccountDatabase
import com.example.aposs_buyer.utils.LoadingState
import com.example.aposs_buyer.utils.LoadingStatus
import com.example.aposs_buyer.utils.OrderStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.util.*
import java.util.stream.Collector
import java.util.stream.Collectors
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val authRepository: AuthRepository,
    private val orderRepository: OrderRepository,
    private val deliveryAddressRepository: DeliveryAddressRepository,
    @ApplicationContext val context: Context
) : ViewModel() {

    private val _lstCartItem = MutableLiveData<ArrayList<CartItem>>()
    val lstCartItem: LiveData<ArrayList<CartItem>> get() = _lstCartItem

    private val _choseList = MutableLiveData<ArrayList<CartItem>>()
    val choseList: LiveData<ArrayList<CartItem>> get() = _choseList

    val total = MutableLiveData<String>()
    val totalInt = MutableLiveData<Int>()
    val size = MutableLiveData<Int>()
    val choseSize = MutableLiveData<Int>()
    val defaultAddress = MutableLiveData<Address>()
    val defaultAddressDTO = MutableLiveData<DeliveryAddressDTO>()

    var tokenDTO: TokenDTO? = null
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    var loadingStatus = MutableLiveData<LoadingState>()

    init {
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
        if (tokenDTO != null) {
            coroutineScope.launch {
                val deleteResponse =
                    cartRepository.deleteCart(
                        tokenDTO!!.getFullAccessToken(),
                        id
                    )
                if (deleteResponse.code() == 200) {
                    Log.d("cart", deleteResponse.body().toString())
                    return@launch
                } else {
                    if (deleteResponse.code() == 401) {
                        Log.d("cart", "Expire access token")
                        val accessTokenResponse =
                            authRepository.getAccessTokenFromRefreshToken(tokenDTO!!.refreshToken)
                        if (accessTokenResponse.code() == 200) {
                            tokenDTO!!.accessToken = accessTokenResponse.body()!!
                            AccountDatabase.getInstance(context).accountDao.updateAccessToken(
                                tokenDTO!!.accessToken
                            )
                            deleteCartItem(id)
                        }
                    }
                }
            }
        }
    }

    private fun updateCartItem(cartDTO: CartDTO) {
        if (tokenDTO != null) {
            coroutineScope.launch {
                val updateResponse =
                    cartRepository.updateCart(
                        tokenDTO!!.getFullAccessToken(),
                        cartDTO
                    )
                if (updateResponse.code() == 200) {
                    Log.d("cart", updateResponse.body().toString())
                    return@launch
                } else {
                    if (updateResponse.code() == 401) {
                        Log.d("cart", "Expire access token")
                        val accessTokenResponse =
                            authRepository.getAccessTokenFromRefreshToken(tokenDTO!!.refreshToken)
                        if (accessTokenResponse.code() == 200) {
                            tokenDTO!!.accessToken = accessTokenResponse.body()!!
                            AccountDatabase.getInstance(context).accountDao.updateAccessToken(
                                tokenDTO!!.accessToken
                            )
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

    val addingAddressStatus = MutableLiveData<LoadingStatus>()
    fun addNewOrder() {
        addingAddressStatus.value = LoadingStatus.Loading
        coroutineScope.launch {
            if (tokenDTO != null) {
                val listOrderItemDTO = _choseList.value!!.stream().map {
                    convertToOrderItemDTO(it)
                }.collect(Collectors.toList())
                val orderDTO = OrderDTO(
                    id = 0,
                    orderTime = Calendar.getInstance().time,
                    orderStatus = OrderStatus.Pending,
                    orderItemDTOList = listOrderItemDTO,
                    totalPrice = totalInt.value!!,
                    address = defaultAddress.value!!.getFullAddress(),
                    cancelReason = null
                )
                val response = orderRepository.orderService.addNewOrder(
                    orderDTO,
                    tokenDTO!!.getFullAccessToken()
                )
                if (response.code() == 200) {
                    Log.d("checkout", "done")
                    addingAddressStatus.value = LoadingStatus.Success
                    for (i: Int in 0 until choseList.value!!.size) {
                        deleteCartItem(choseList.value!![i].id)
                    }
                    _choseList.value = ArrayList()
                    return@launch
                }
                if (response.code() == 401) {
                    val accessTokenResponse = authRepository.getAccessTokenFromRefreshToken(tokenDTO!!.refreshToken)
                    if (accessTokenResponse.code() == 200) {
                        tokenDTO!!.accessToken = accessTokenResponse.body()!!
                        AccountDatabase.getInstance(context).accountDao.updateAccessToken(tokenDTO!!.accessToken)
                        addNewOrder()
                    }
                } else {
                    Log.d("checkout", "fail")
                    addingAddressStatus.value = LoadingStatus.Fail
                }
            }
        }
    }

    fun convertToOrderItemDTO(orderItem: CartItem): OrderItemDTO {
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

    val loadAddressStatus = MutableLiveData<LoadingStatus>()
    fun loadDefaultAddress() {
        Log.d("address", "load")
        loadAddressStatus.value = LoadingStatus.Loading
        coroutineScope.launch {
            if (tokenDTO != null) {
                val defaultAddressRespone =
                    deliveryAddressRepository.deliveryAddressService.getDefaultAddress(
                        tokenDTO!!.getFullAccessToken()
                    )
                if (defaultAddressRespone.code() == 200) {
                    val defaultAddressDTOResponseBody = defaultAddressRespone.body()
                        defaultAddressDTO.value = defaultAddressDTOResponseBody!!
                        defaultAddress.value =
                            convertDeliveryAddressDTOToAddress(defaultAddressDTO.value!!)
                    return@launch
                }
                if (defaultAddressRespone.code() == 401) {
                    val accessTokenResponse = authRepository.getAccessTokenFromRefreshToken(tokenDTO!!.refreshToken)
                    if (accessTokenResponse.code() == 200) {
                        tokenDTO!!.accessToken = accessTokenResponse.body()!!
                        AccountDatabase.getInstance(context).accountDao.updateAccessToken(tokenDTO!!.accessToken)
                        loadDefaultAddress()
                    }
                } else {
                    loadAddressStatus.value = LoadingStatus.Fail
                    Log.d("address", "fail")
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
            city = deliveryAddressDTO.province.name,
            district = deliveryAddressDTO.district.name,
            ward = deliveryAddressDTO.ward.name,
            isDefault = deliveryAddressDTO.isDefault,
            addressLane = deliveryAddressDTO.addressLane
        );
    }

    fun loadCartList() {
        coroutineScope.launch {
            if (tokenDTO != null) {
                loadingStatus.value = LoadingState.Loading
                val allCartItemsResponse =
                    cartRepository.getAllCart(tokenDTO!!.getFullAccessToken())
                when (allCartItemsResponse.code()) {
                    200 -> {
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
                    }
                    401 -> {
                        Log.d("cart", "Expire access token")
                        val accessTokenResponse =
                            authRepository.getAccessTokenFromRefreshToken(tokenDTO!!.refreshToken)
                        if (accessTokenResponse.code() == 200) {
                            tokenDTO!!.accessToken = accessTokenResponse.body()!!
                            AccountDatabase.getInstance(context).accountDao.updateAccessToken(
                                tokenDTO!!.accessToken
                            )
                            loadCartList()
                        }
                    }
                    else -> {
                        loadingStatus.value = LoadingState.Fail
                    }
                }
            }
        }
    }

    fun holdProduct(): Boolean{
        var result = false
        Log.d("checkoutBussiness", getChose().toString())
        if (tokenDTO != null) {
                loadingStatus.value = LoadingState.Loading
                val listOrderItemDTO = getChose().stream().map {
                    convertToOrderItemDTO(it)
                }.collect(Collectors.toList())
            Log.d("holding", listOrderItemDTO.toString())
                runBlocking {
                    val holdResponse = async {
                        orderRepository.orderService.holdProduct(
                            listOrderItemDTO,
                            tokenDTO!!.getFullAccessToken()
                        )
                    }
                    runBlocking {
                        when (holdResponse.await().code()) {
                            200 -> {
                                result = true
                                Log.d("checkoutBussiness", "success")
                            }
                            401 -> {
                                val accessTokenResponse =
                                    authRepository.getAccessTokenFromRefreshToken(tokenDTO!!.refreshToken)
                                if (accessTokenResponse.code() == 200) {
                                    tokenDTO!!.accessToken = accessTokenResponse.body()!!
                                    AccountDatabase.getInstance(context).accountDao.updateAccessToken(
                                        tokenDTO!!.accessToken
                                    )
                                    holdProduct()
                                }
                                Log.d("checkoutBussiness", "expired")
                            }
                            else -> {
                                result = false
                                Log.d("checkoutBussiness", "false")
                            }
                        }
                    }
                }
            }
        Log.d("checkoutBussiness", "last come")
        return result
    }

    fun reduceHold() {
        coroutineScope.launch {
            Log.d("checkoutBussiness", getChose().toString())
            if (tokenDTO != null) {
                loadingStatus.value = LoadingState.Loading
                val listOrderItemDTO = getChose().stream().map {
                    convertToOrderItemDTO(it)
                }.collect(Collectors.toList())
                val reduceHoldResponse =
                    orderRepository.orderService.reduceHold(
                        listOrderItemDTO,
                        tokenDTO!!.getFullAccessToken()
                    )
                when (reduceHoldResponse.code()) {
                    200 -> {
                        Log.d("checkoutBussiness", "success")
                    }
                    401 -> {
                        val accessTokenResponse =
                            authRepository.getAccessTokenFromRefreshToken(tokenDTO!!.refreshToken)
                        if (accessTokenResponse.code() == 200) {
                            tokenDTO!!.accessToken = accessTokenResponse.body()!!
                            AccountDatabase.getInstance(context).accountDao.updateAccessToken(
                                tokenDTO!!.accessToken
                            )
                            holdProduct()
                        }
                        Log.d("checkoutBussiness", "expired")
                    }
                    else -> {
                        Log.d("checkoutBussiness", "false")
                    }
                }
            }

        }
    }
}