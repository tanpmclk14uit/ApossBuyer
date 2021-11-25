package com.example.aposs_buyer.responsitory

import com.example.aposs_buyer.responsitory.webservice.OrderService
import com.example.aposs_buyer.responsitory.webservice.RetrofitInstance
import com.squareup.moshi.JsonClass
import javax.inject.Inject

class OrderRepository @Inject constructor() {

    val orderService: OrderService by lazy {
        RetrofitInstance.retrofit.create(OrderService::class.java)
    }
}