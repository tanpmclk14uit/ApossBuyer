package com.example.aposs_buyer.responsitory.webservice

import com.example.aposs_buyer.model.dto.OrderDTO
import com.example.aposs_buyer.model.dto.OrderItemDTO
import com.example.aposs_buyer.utils.OrderStatus
import retrofit2.Response
import retrofit2.http.*

interface OrderService {

    @POST("order")
    suspend fun addNewOrder(
        @Body orderDTO: OrderDTO,
        @Header("Authorization") token: String,
    ): Response<Unit>

    @GET("order/all-order-by")
    suspend fun getAllOrderByStatus(
        @Query("status") orderStatus: OrderStatus,
        @Header("Authorization") token: String,
    ): Response<List<OrderDTO>>

    @GET("order/order-by-id/{id}")
    suspend fun getOrderById(
        @Header("Authorization") accessToken: String,
        @Path(value = "id") id: Long
    ): Response<OrderDTO>

    @PUT("order/{id}/cancel")
    suspend fun cancelOrder(
        @Path(value = "id") id: Long,
        @Body cancelReason: String,
        @Header("Authorization") accessToken: String,
    ): Response<Unit>

    @PUT("order/update-payment-status/{id}?status=Pending")
    suspend fun updatePaymentStatus(
        @Path(value = "id") id: Long,
        @Header("Authorization") accessToken: String
    ):Response<Unit>

    @PUT("order/success-order-customer/{id}")
    suspend fun putOrderStatusToSuccess(
        @Path(value = "id") id: Long,
        @Header("Authorization") accessToken: String,
    ): Response<Unit>

    @PUT("order/{id}/address")
    suspend fun changeOrderAddress(
        @Path(value = "id") id: Long,
        @Body newAddress: String,
        @Header("Authorization") accessToken: String,
    ): Response<Unit>
}