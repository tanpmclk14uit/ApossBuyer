package com.example.aposs_buyer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.Order
import com.example.aposs_buyer.model.OrderBillingItem
import com.example.aposs_buyer.model.dto.OrderDTO
import com.example.aposs_buyer.model.dto.OrderItemDTO
import com.example.aposs_buyer.responsitory.AuthRepository
import com.example.aposs_buyer.responsitory.OrderRepository
import com.example.aposs_buyer.utils.LoadingStatus
import com.example.aposs_buyer.utils.OrderStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.stream.Collectors
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private var _orders: MutableLiveData<MutableList<Order>> = MutableLiveData()
    val orders: LiveData<MutableList<Order>> get() = _orders

    private var _loadingStatus = MutableLiveData<LoadingStatus>()
    val loadingStatus: LiveData<LoadingStatus> get() = _loadingStatus

    init {
        _orders.value = mutableListOf()
    }


    fun getAllOrderByStatus(orderStatus: OrderStatus) {
        _orders.value!!.clear()
        viewModelScope.launch(Dispatchers.IO) {
            _loadingStatus.postValue(LoadingStatus.Loading)
            val token = authRepository.getCurrentAccessTokenFromRoom()
            if (!token.isNullOrBlank()) {
                val responseOrders =
                    orderRepository.getAllOrdersByStatusAndUserToken(orderStatus, token)
                if (responseOrders.isSuccessful) {
                    _orders.postValue(responseOrders.body()!!.stream().map { convertToOrder(it) }
                        .collect(Collectors.toList()))
                    _loadingStatus.postValue(LoadingStatus.Success)
                } else {
                    if (responseOrders.code() == 401) {
                        if (authRepository.loadNewAccessTokenSuccess()) {
                            getAllOrderByStatus(orderStatus)
                        } else {
                            _loadingStatus.postValue(LoadingStatus.Fail)
                        }
                    } else {
                        _loadingStatus.postValue(LoadingStatus.Fail)
                    }
                }
            } else {
                _loadingStatus.postValue(LoadingStatus.Fail)
            }
        }
    }


    private fun convertToOrderItem(orderItemDTO: OrderItemDTO): OrderBillingItem {
        return OrderBillingItem(
            id = orderItemDTO.id,
            setId = orderItemDTO.setId,
            price = orderItemDTO.price,
            property = orderItemDTO.property,
            image = Image(orderItemDTO.imageUrl),
            quantity = orderItemDTO.quantity,
            name = orderItemDTO.name
        )
    }

    private fun convertToOrder(orderDTO: OrderDTO): Order {
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
}