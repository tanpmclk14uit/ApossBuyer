package com.example.aposs_buyer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.Order
import com.example.aposs_buyer.model.OrderBillingItem
import com.example.aposs_buyer.model.OrderDeliveringState
import com.example.aposs_buyer.model.dto.OrderDTO
import com.example.aposs_buyer.model.dto.OrderItemDTO
import com.example.aposs_buyer.responsitory.AuthRepository
import com.example.aposs_buyer.responsitory.OrderRepository
import com.example.aposs_buyer.utils.LoadingStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import java.util.stream.Collectors
import javax.inject.Inject
import kotlin.collections.ArrayList


@HiltViewModel
class OrderDetailViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {

    private var _detailOrder: MutableLiveData<Order> = MutableLiveData()
    val detailOrder: LiveData<Order> get() = _detailOrder

    private var _orderDeliveringState: MutableLiveData<ArrayList<OrderDeliveringState>> =
        MutableLiveData()
    val orderDeliveringState: LiveData<ArrayList<OrderDeliveringState>> get() = _orderDeliveringState

    private var _loadStatus = MutableLiveData<LoadingStatus>()
    val loadStatus: LiveData<LoadingStatus> get() = _loadStatus

    var changeAddressStatus = MutableLiveData<LoadingStatus>()

    fun setCurrentOrder(order: Order) {
        _detailOrder.value = order
        _orderDeliveringState.value = loadOrderDeliveringState(order.id)
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

    fun changeAddress(newAddress: String) {
            viewModelScope.launch(Dispatchers.IO) {
                val accessToken = authRepository.getCurrentAccessTokenFromRoom()
                changeAddressStatus.postValue(LoadingStatus.Loading)
                if (!accessToken.isNullOrBlank()) {
                    val response = orderRepository.changeOrderAddress(
                        _detailOrder.value!!.id,
                        newAddress,
                        accessToken
                    )
                    if (response.isSuccessful) {
                        changeAddressStatus.postValue(LoadingStatus.Success)
                        _detailOrder.value!!.address = newAddress
                    } else {
                        if (response.code() == 401) {
                            if (authRepository.loadNewAccessTokenSuccess()) {
                                changeAddress(newAddress)
                            } else {
                                changeAddressStatus.postValue(LoadingStatus.Fail)
                            }
                        } else {
                            changeAddressStatus.postValue(LoadingStatus.Fail)
                        }
                    }
                } else {
                    changeAddressStatus.postValue(LoadingStatus.Fail)
                }
            }
    }

    private fun loadOrderDeliveringState(id: Long): ArrayList<OrderDeliveringState> {
        val sample: ArrayList<OrderDeliveringState> = ArrayList()
        sample.add(OrderDeliveringState(1, "Placed order", Date()))
        sample.add(OrderDeliveringState(2, "Delivery to shipping units", Date()))
        sample.add(OrderDeliveringState(3, "Orders are being shipped", Date()))
        sample.add(OrderDeliveringState(4, "Orders are being shipped", Date()))
        return sample
    }

    fun receivedOrder() {
        _loadStatus.value = LoadingStatus.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val token = authRepository.getCurrentAccessTokenFromRoom()
            if (!token.isNullOrBlank()) {
                val response =
                    orderRepository.putOrderStatusToSuccess(detailOrder.value!!.id, token)
                if (response.isSuccessful) {
                    _loadStatus.postValue(LoadingStatus.Success)
                } else {
                    if (response.code() == 401) {
                        if (authRepository.loadNewAccessTokenSuccess()) {
                            receivedOrder()
                        } else {
                            _loadStatus.postValue(LoadingStatus.Fail)
                        }
                    } else {
                        _loadStatus.postValue(LoadingStatus.Fail)
                    }
                }
            } else {
                _loadStatus.postValue(LoadingStatus.Fail)
            }
        }
    }
}