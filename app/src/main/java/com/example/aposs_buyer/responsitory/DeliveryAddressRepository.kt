package com.example.aposs_buyer.responsitory

import com.example.aposs_buyer.responsitory.webservice.DeliveryAddressService
import com.example.aposs_buyer.responsitory.webservice.RetrofitInstance
import javax.inject.Inject

class DeliveryAddressRepository @Inject constructor() {

    val deliveryAddressService : DeliveryAddressService by lazy {
        RetrofitInstance.retrofit.create(DeliveryAddressService::class.java)
    }
}