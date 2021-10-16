package com.example.aposs_buyer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.Order
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class OrderDetailViewModel @Inject constructor(): ViewModel() {

    private var _detailOrder: MutableLiveData<Order> = MutableLiveData()
    val detailOrder: LiveData<Order> get() = _detailOrder

    fun setCurrentOrder(order: Order){
        _detailOrder.value = order
    }
}