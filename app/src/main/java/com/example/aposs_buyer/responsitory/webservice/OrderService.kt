package com.example.aposs_buyer.responsitory.webservice

import com.example.aposs_buyer.model.dto.OrderDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OrderService {

    @POST("order")
    suspend fun addNewOrder(
        @Body orderDTO: OrderDTO,
        @Header("Authorization") token: String,
    ): Response<String>
}