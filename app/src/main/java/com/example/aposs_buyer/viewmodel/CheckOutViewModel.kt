package com.example.aposs_buyer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aposs_buyer.model.Address
import com.example.aposs_buyer.model.Order
import com.example.aposs_buyer.model.OrderBillingItem
import com.example.aposs_buyer.model.dto.OrderDTO
import com.example.aposs_buyer.model.dto.OrderItemDTO
import com.example.aposs_buyer.responsitory.AuthRepository
import com.example.aposs_buyer.responsitory.OrderRepository
import com.example.aposs_buyer.utils.LoadingStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import kotlin.streams.toList

@HiltViewModel
class CheckOutViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val authRepository: AuthRepository
) : ViewModel() {
    val currentOrder = MutableLiveData<Order>()
    val checkOutStatus = MutableLiveData<LoadingStatus>()
    fun setCurrentOrder(order: Order) {
        currentOrder.value = order
    }

    fun setNewAddress(address: Address){
        currentOrder.value!!.address = address.getFullAddress()
    }

    fun addNewOrder() {
            viewModelScope.launch(Dispatchers.IO) {
                checkOutStatus.postValue(LoadingStatus.Loading)
                val accessToken = authRepository.getCurrentAccessTokenFromRoom()
                if (!accessToken.isNullOrBlank()) {
                    val addNewOrderResponse = orderRepository.addNewOrder(
                        convertOrderToOrderDTO(currentOrder.value!!),
                        accessToken
                    )
                    if (addNewOrderResponse.isSuccessful) {
                        checkOutStatus.postValue(LoadingStatus.Success)
                    } else {
                        if (addNewOrderResponse.code() == 401) {
                            if (authRepository.loadNewAccessTokenSuccess()) {
                                addNewOrder()
                            } else {
                                checkOutStatus.postValue(LoadingStatus.Fail)
                            }
                        } else {
                            checkOutStatus.postValue(LoadingStatus.Fail)
                        }
                    }
                } else {
                    checkOutStatus.postValue(LoadingStatus.Fail)
                }
            }
    }

    private fun convertBillingItemToOrderItemDTO(billingItem: OrderBillingItem): OrderItemDTO {
        return OrderItemDTO(
            cartId = billingItem.cartId,
            setId = billingItem.setId,
            quantity = billingItem.quantity
        )
    }

    private fun convertOrderToOrderDTO(order: Order): OrderDTO {
        val orderItemDTOs =
            order.billingItems.stream().map { convertBillingItemToOrderItemDTO(it) }.toList()
        return OrderDTO(
            address = order.address,
            orderItemDTOList = orderItemDTOs
        )

    }
}