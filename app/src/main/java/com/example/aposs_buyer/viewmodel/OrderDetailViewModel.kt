package com.example.aposs_buyer.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.Order
import com.example.aposs_buyer.model.OrderBillingItem
import com.example.aposs_buyer.model.OrderDeliveringState
import com.example.aposs_buyer.model.dto.OrderDTO
import com.example.aposs_buyer.model.dto.OrderItemDTO
import com.example.aposs_buyer.model.dto.TokenDTO
import com.example.aposs_buyer.model.entity.Account
import com.example.aposs_buyer.responsitory.AuthRepository
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
class OrderDetailViewModel @Inject constructor(
    val orderRepository: OrderRepository,
    val authRepository: AuthRepository,
    @ApplicationContext val context: Context
) : ViewModel() {

    private var _detailOrder: MutableLiveData<Order> = MutableLiveData()
    val detailOrder: LiveData<Order> get() = _detailOrder

    private var _orderDeliveringState: MutableLiveData<ArrayList<OrderDeliveringState>> =
        MutableLiveData()
    val orderDeliveringState: LiveData<ArrayList<OrderDeliveringState>> get() = _orderDeliveringState

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _loadStatus = MutableLiveData<LoadingStatus>()
    val loadStatus: LiveData<LoadingStatus> get() = _loadStatus

    fun setCurrentOrder(order: Order) {
        _detailOrder.value = order
        _orderDeliveringState.value = loadOrderDeliveringState(order.id)

    }

    fun setDetailOrderById(id: Long) {
        loadDetailOrderById(id)
        _orderDeliveringState.value = loadOrderDeliveringState(id)
        Log.d("OrderDetailViewModel", "Order: $id")
    }

    private fun loadDetailOrderById(id: Long) {
        val account = AccountDatabase.getInstance(context).accountDao.getAccount()
        val token =
            TokenDTO(accessToken = account!!.accessToken, account.tokenType, account.refreshToken)
        _loadStatus.value = LoadingStatus.Loading
        coroutineScope.launch {
            var respond =
                orderRepository.orderService.getOrderById(token.getFullAccessToken(), id)
            if (respond.code() == 200) {
                val orderDTO = respond.body()
                _detailOrder.value = convertToOrder(orderDTO!!)
                Log.d("DetailOrder", _detailOrder.value.toString())
                _loadStatus.value = LoadingStatus.Success
            }
            if (respond.code() == 401) {
                val accessTokenResponse =
                    authRepository.getAccessTokenFromRefreshToken(token.refreshToken)
                if (accessTokenResponse.code() == 200) {
                    token.accessToken = accessTokenResponse.body()!!
                    AccountDatabase.getInstance(context).accountDao.updateAccessToken(
                        token.accessToken
                    )
                    loadDetailOrderById(id)
                }
            } else {
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
    private fun loadOrderDeliveringState(id: Long): ArrayList<OrderDeliveringState> {
        val sample: ArrayList<OrderDeliveringState> = ArrayList()
        sample.add(OrderDeliveringState(1, "Placed order", Date()))
        sample.add(OrderDeliveringState(2, "Delivery to shipping units", Date()))
        sample.add(OrderDeliveringState(3, "Orders are being shipped", Date()))
        sample.add(OrderDeliveringState(4, "Orders are being shipped", Date()))
        return sample
    }

    fun successOrder(id: Long){
        _loadStatus.value = LoadingStatus.Loading
        val account: Account = AccountDatabase.getInstance(context).accountDao.getAccount()!!
        var tokenDTO = TokenDTO(accessToken = account.accessToken, tokenType = account.tokenType, refreshToken = account.refreshToken)
        coroutineScope.launch {
            val response = orderRepository.orderService.successOrder(id, tokenDTO.getFullAccessToken())
            if (response.code() == 200)
            {
                _loadStatus.value = LoadingStatus.Success
            }
            else if (response.code() == 401){
                val accessTokenResponse =
                    authRepository.getAccessTokenFromRefreshToken(tokenDTO.refreshToken)
                if (accessTokenResponse.code() == 200) {
                    tokenDTO.accessToken = accessTokenResponse.body()!!
                    AccountDatabase.getInstance(context).accountDao.updateAccessToken(
                        tokenDTO.accessToken
                    )
                    successOrder(id)
                }
            }
            else {
                _loadStatus.value = LoadingStatus.Fail
            }
        }
    }
}