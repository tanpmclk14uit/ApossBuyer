package com.example.aposs_buyer.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.aposs_buyer.model.Address
import com.example.aposs_buyer.model.HomeProduct
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.dto.ProductDTO

object BridgeObject {
    var addressListChange: MutableLiveData<Boolean> = MutableLiveData(false)

}