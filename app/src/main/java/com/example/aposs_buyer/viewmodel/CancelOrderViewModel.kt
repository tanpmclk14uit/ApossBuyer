package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CancelOrderViewModel @Inject constructor(): ViewModel(){
    private var orderId: Long = -1
    var cancelReason: MutableLiveData<String> = MutableLiveData()

    init {
        cancelReason.value = ""
    }

    fun setOrderId(id: Long){
        orderId =id
    }
    fun cancelOrder(){
        Log.d("CancelOrderViewModel", "Cancel $orderId with ${cancelReason.value!!}")
    }
}
