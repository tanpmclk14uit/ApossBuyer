package com.example.aposs_buyer.responsitory

import com.example.aposs_buyer.model.dto.OrderDTO
import com.example.aposs_buyer.responsitory.webservice.OrderService
import com.example.aposs_buyer.responsitory.webservice.RetrofitInstance
import com.squareup.moshi.JsonClass
import retrofit2.Response
import javax.inject.Inject

class OrderRepository @Inject constructor() {

    val orderService: OrderService by lazy {
        RetrofitInstance.retrofit.create(OrderService::class.java)
    }

    suspend fun addNewOrder(orderDTO: OrderDTO, accessToken: String): Response<Unit> {
        return orderService.addNewOrder(orderDTO, accessToken)
    }

}