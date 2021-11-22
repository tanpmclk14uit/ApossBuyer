package com.example.aposs_buyer.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.aposs_buyer.model.Address

object BridgeObject {
    var addressListChange: MutableLiveData<Boolean> = MutableLiveData(false)
}