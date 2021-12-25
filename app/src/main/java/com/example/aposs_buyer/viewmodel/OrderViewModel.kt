package com.example.aposs_buyer.viewmodel

import android.content.Context
import android.util.Log
import androidx.collection.arrayMapOf
import androidx.collection.arraySetOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.Order
import com.example.aposs_buyer.model.OrderBillingItem
import com.example.aposs_buyer.model.dto.OrderDTO
import com.example.aposs_buyer.model.dto.OrderItemDTO
import com.example.aposs_buyer.model.dto.TokenDTO
import com.example.aposs_buyer.responsitory.AuthRepository
import com.example.aposs_buyer.responsitory.DeliveryAddressRepository
import com.example.aposs_buyer.responsitory.OrderRepository
import com.example.aposs_buyer.responsitory.database.AccountDatabase
import com.example.aposs_buyer.utils.LoadingStatus
import com.example.aposs_buyer.utils.OrderStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import java.util.stream.Collectors
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class OrderViewModel @Inject constructor(
    val orderRepository: OrderRepository,
    val authRepository: AuthRepository,
    @ApplicationContext val context: Context
) : ViewModel() {

    private var _currentListOrder: MutableLiveData<MutableList<Order>> = MutableLiveData()
    val currentListOrder: LiveData<MutableList<Order>> get() = _currentListOrder

    private var _currentOrder: Order? = null
    val currentOrder: Order? get() = _currentOrder

    private var _displayListOrder: MutableLiveData<MutableList<Order>> = MutableLiveData()
    val displayListOrder: LiveData<MutableList<Order>> get() = _displayListOrder

    private var _loadStatus = MutableLiveData<LoadingStatus>()
    val loadStatus: LiveData<LoadingStatus> get() = _loadStatus

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        loadPendingOrder()
    }

    fun setCurrentOrder(order: Order){
        for (i in 0 until currentListOrder.value!!.size) {
            if (order.id == currentListOrder.value!![i].id)
            {
                _currentOrder = currentListOrder.value!![i]
                return
            }
        }
    }


    fun setCurrentOrders(currentOrder: ArrayList<Order>){
        _currentListOrder.value = currentOrder
    }

    private fun clearList()
    {
        _currentListOrder.value = mutableListOf()
    }

    private fun loadListDisplay()
    {
        for(i in 0 until _displayListOrder.value!!.size)
        {
            if (_displayListOrder.value!![i].billingItems.size >= 2) {
                _displayListOrder.value!![i].billingItems =
                    ArrayList(_displayListOrder.value!![i].billingItems.subList(0, 2))
            }
            else {
                _displayListOrder.value!![i].billingItems =
                    ArrayList(_displayListOrder.value!![i].billingItems.subList(0, 1))
            }
        }
    }

    fun loadPendingOrder() {
        clearList()
        val account = AccountDatabase.getInstance(context).accountDao.getAccount()
        val token  = TokenDTO(accessToken = account!!.accessToken, account.tokenType, account.refreshToken)
        _loadStatus.value = LoadingStatus.Loading
        coroutineScope.launch {
            var respond = orderRepository.orderService.getAllOrderByStatus(OrderStatus.Pending, token.getFullAccessToken())
            if (respond.code() == 200)
            {
                val listOrderDTO = respond.body()
                 _currentListOrder.value = listOrderDTO!!.stream().map {
                    convertToOrder(it)
                }.collect(Collectors.toList())
                _displayListOrder.value = listOrderDTO!!.stream().map {
                    convertToOrder(it)
                }.collect(Collectors.toList())
                loadListDisplay()
                _loadStatus.value = LoadingStatus.Success
            }
            if (respond.code() == 401)
            {
                val accessTokenResponse =
                    authRepository.getAccessToken(token.refreshToken)
                if (accessTokenResponse.code() == 200) {
                    token.accessToken = accessTokenResponse.body()!!
                    AccountDatabase.getInstance(context).accountDao.updateAccessToken(
                        token.accessToken
                    )
                    loadPendingOrder()
                }
            }
            else {
                _loadStatus.value = LoadingStatus.Fail
            }
        }
    }

    private fun convertToOrderItem(orderItemDTO: OrderItemDTO): OrderBillingItem
    {
        return OrderBillingItem(
            id = orderItemDTO.id,
            product = orderItemDTO.product,
            price = orderItemDTO.price,
            property = orderItemDTO.property,
            image = Image(orderItemDTO.imageUrl),
            amount = orderItemDTO.quantity,
            name = orderItemDTO.name
        )
    }

    private fun convertToOrder(orderDTO: OrderDTO): Order
    {
        return Order(
            id = orderDTO.id,
            orderTime = orderDTO.orderTime,
            billingItems = ArrayList(orderDTO.orderItemDTOList.stream().map {
                convertToOrderItem(it)
            }.collect(Collectors.toList())),
            totalPrice = orderDTO.totalPrice,
            status = orderDTO.orderStatus,
            address = orderDTO.address
        )
    }


    fun loadConfirmedOrder() {
        clearList()
        val account = AccountDatabase.getInstance(context).accountDao.getAccount()
        val token  = TokenDTO(accessToken = account!!.accessToken, account.tokenType, account.refreshToken)
        _loadStatus.value = LoadingStatus.Loading
        coroutineScope.launch {
            var respond = orderRepository.orderService.getAllOrderByStatus(OrderStatus.Confirmed, token.getFullAccessToken())
            if (respond.code() == 200)
            {
                val listOrderDTO = respond.body()
                _currentListOrder.value = listOrderDTO!!.stream().map {
                    convertToOrder(it)
                }.collect(Collectors.toList())
                _displayListOrder.value = listOrderDTO!!.stream().map {
                    convertToOrder(it)
                }.collect(Collectors.toList())
                loadListDisplay()
                _loadStatus.value = LoadingStatus.Success
            }
            if (respond.code() == 401)
            {
                val accessTokenResponse =
                    authRepository.getAccessToken(token.refreshToken)
                if (accessTokenResponse.code() == 200) {
                    token.accessToken = accessTokenResponse.body()!!
                    AccountDatabase.getInstance(context).accountDao.updateAccessToken(
                        token.accessToken
                    )
                    loadConfirmedOrder()
                }
            }
            else {
                _loadStatus.value = LoadingStatus.Fail
            }
        }
    }
    fun loadDeliveringOrder(){
        clearList()
        val account = AccountDatabase.getInstance(context).accountDao.getAccount()
        val token  = TokenDTO(accessToken = account!!.accessToken, account.tokenType, account.refreshToken)
        _loadStatus.value = LoadingStatus.Loading
        coroutineScope.launch {
            var respond = orderRepository.orderService.getAllOrderByStatus(OrderStatus.Delivering, token.getFullAccessToken())
            if (respond.code() == 200)
            {
                val listOrderDTO = respond.body()
                _currentListOrder.value = listOrderDTO!!.stream().map {
                    convertToOrder(it)
                }.collect(Collectors.toList())
                _displayListOrder.value = listOrderDTO!!.stream().map {
                    convertToOrder(it)
                }.collect(Collectors.toList())
                loadListDisplay()
                _loadStatus.value = LoadingStatus.Success
            }
            if (respond.code() == 401)
            {
                val accessTokenResponse =
                    authRepository.getAccessToken(token.refreshToken)
                if (accessTokenResponse.code() == 200) {
                    token.accessToken = accessTokenResponse.body()!!
                    AccountDatabase.getInstance(context).accountDao.updateAccessToken(
                        token.accessToken
                    )
                    loadDeliveringOrder()
                }
            }
            else {
                _loadStatus.value = LoadingStatus.Fail
            }
        }
    }
    fun loadSuccessOrder() {
        clearList()
        val account = AccountDatabase.getInstance(context).accountDao.getAccount()
        val token  = TokenDTO(accessToken = account!!.accessToken, account.tokenType, account.refreshToken)
        _loadStatus.value = LoadingStatus.Loading
        coroutineScope.launch {
            var respond = orderRepository.orderService.getAllOrderByStatus(OrderStatus.Success, token.getFullAccessToken())
            if (respond.code() == 200)
            {
                val listOrderDTO = respond.body()
                _currentListOrder.value = listOrderDTO!!.stream().map {
                    convertToOrder(it)
                }.collect(Collectors.toList())
                _displayListOrder.value = listOrderDTO!!.stream().map {
                    convertToOrder(it)
                }.collect(Collectors.toList())
                loadListDisplay()
                _loadStatus.value = LoadingStatus.Success
            }
            if (respond.code() == 401)
            {
                val accessTokenResponse =
                    authRepository.getAccessToken(token.refreshToken)
                if (accessTokenResponse.code() == 200) {
                    token.accessToken = accessTokenResponse.body()!!
                    AccountDatabase.getInstance(context).accountDao.updateAccessToken(
                        token.accessToken
                    )
                    loadSuccessOrder()
                }
            }
            else {
                _loadStatus.value = LoadingStatus.Fail
            }
        }
    }
    fun loadCancelOrder() {
        clearList()
        val account = AccountDatabase.getInstance(context).accountDao.getAccount()
        val token  = TokenDTO(accessToken = account!!.accessToken, account.tokenType, account.refreshToken)
        _loadStatus.value = LoadingStatus.Loading
        coroutineScope.launch {
            var respond = orderRepository.orderService.getAllOrderByStatus(OrderStatus.Cancel, token.getFullAccessToken())
            if (respond.code() == 200)
            {
                val listOrderDTO = respond.body()
                _currentListOrder.value = listOrderDTO!!.stream().map {
                    convertToOrder(it)
                }.collect(Collectors.toList())
                _displayListOrder.value = listOrderDTO!!.stream().map {
                    convertToOrder(it)
                }.collect(Collectors.toList())
                loadListDisplay()
                _loadStatus.value = LoadingStatus.Success
            }
            if (respond.code() == 401)
            {
                val accessTokenResponse =
                    authRepository.getAccessToken(token.refreshToken)
                if (accessTokenResponse.code() == 200) {
                    token.accessToken = accessTokenResponse.body()!!
                    AccountDatabase.getInstance(context).accountDao.updateAccessToken(
                        token.accessToken
                    )
                    loadCancelOrder()
                }
            }
            else {
                _loadStatus.value = LoadingStatus.Fail
            }
        }
    }
}