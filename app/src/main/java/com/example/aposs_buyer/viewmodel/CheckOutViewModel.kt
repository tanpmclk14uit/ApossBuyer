package com.example.aposs_buyer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CheckOutViewModel @Inject constructor() : ViewModel() {
    val currentOrder = MutableLiveData<Order>()
    fun setCurrentOrder(order: Order) {
        currentOrder.value = order
    }
}