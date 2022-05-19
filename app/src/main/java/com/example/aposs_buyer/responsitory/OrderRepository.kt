package com.example.aposs_buyer.responsitory

import com.example.aposs_buyer.model.Address
import com.example.aposs_buyer.model.dto.OrderDTO
import com.example.aposs_buyer.responsitory.webservice.OrderService
import com.example.aposs_buyer.responsitory.webservice.RetrofitInstance
import com.example.aposs_buyer.utils.OrderStatus
import com.squareup.moshi.JsonClass
import retrofit2.Response
import javax.inject.Inject

class OrderRepository @Inject constructor() {

    private val orderService: OrderService by lazy {
        RetrofitInstance.retrofit.create(OrderService::class.java)
    }

    suspend fun addNewOrder(orderDTO: OrderDTO, accessToken: String): Response<Unit> {
        return orderService.addNewOrder(orderDTO, accessToken)
    }

    suspend fun getAllOrdersByStatusAndUserToken(
        status: OrderStatus,
        accessToken: String
    ): Response<List<OrderDTO>> {
        return orderService.getAllOrderByStatus(status, accessToken)
    }

    suspend fun putOrderStatusToSuccess(orderId: Long, accessToken: String): Response<Unit> {
        return orderService.putOrderStatusToSuccess(orderId, accessToken)
    }

    suspend fun cancelOrder(orderId: Long,reason: String, accessToken: String): Response<Unit>{
        return orderService.cancelOrder(orderId, reason, accessToken)
    }
    suspend fun changeOrderAddress(orderId: Long, newAddress: String, accessToken: String): Response<Unit>{
        return orderService.changeOrderAddress(orderId,newAddress,accessToken)
    }

}