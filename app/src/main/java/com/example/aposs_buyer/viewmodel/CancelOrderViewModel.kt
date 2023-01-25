package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aposs_buyer.responsitory.AuthRepository
import com.example.aposs_buyer.responsitory.OrderRepository
import com.example.aposs_buyer.utils.LoadingStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class CancelOrderViewModel @Inject constructor(
    val orderRepository: OrderRepository,
    val authRepository: AuthRepository,
) : ViewModel() {
    private var orderId: Long = -1
    var cancelReason: MutableLiveData<String> = MutableLiveData()

    val loadStatus = MutableLiveData<LoadingStatus>()

    init {
        cancelReason.value = ""
    }

    fun setOrderId(id: Long) {
        orderId = id
    }

    fun cancelOrder() {
        runBlocking {
            viewModelScope.launch {
                loadStatus.value = LoadingStatus.Loading
                val accessToken = authRepository.getCurrentAccessTokenFromRoom()
                if (!accessToken.isNullOrBlank()) {
                    val response =
                        orderRepository.cancelOrder(orderId, cancelReason.value!!, accessToken)
                    if (response.isSuccessful) {
                        loadStatus.value = LoadingStatus.Success
                    } else {
                        if (response.code() == 401) {
                            if (authRepository.loadNewAccessTokenSuccess()) {
                                cancelOrder()
                            } else {
                                loadStatus.value = LoadingStatus.Fail
                            }
                        } else {
                            loadStatus.value = LoadingStatus.Fail
                        }
                    }
                } else {
                    loadStatus.value = LoadingStatus.Fail
                }
            }
        }
    }

}
