package com.example.aposs_buyer.responsitory.webservice

import com.example.aposs_buyer.model.dto.OrderDTO
import com.example.aposs_buyer.model.dto.OrderItemDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface OrderService {

    @POST("order")
    suspend fun addNewOrder(
        @Body orderDTO: OrderDTO,
        @Header("Authorization") token: String,
    ): Response<String>

    @PUT("order/hold")
    suspend fun holdProduct(
        @Body listOrderItemDTO: List<OrderItemDTO>,
        @Header("Authorization") token: String,
    ): Response<String>

    @PUT("order/reduceHold")
    suspend fun reduceHold(
        @Body listOrderItemDTO: List<OrderItemDTO>,
        @Header("Authorization") token: String,
    ): Response<String>
}