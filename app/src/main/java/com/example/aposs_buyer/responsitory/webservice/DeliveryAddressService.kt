package com.example.aposs_buyer.responsitory.webservice

import com.example.aposs_buyer.model.dto.DeliveryAddressDTO
import com.example.aposs_buyer.model.dto.TokenDTO
import okhttp3.Request
import retrofit2.Response
import retrofit2.http.*

interface DeliveryAddressService {

    @GET("delivery_address")
    suspend fun getAllDeliveryAddressService(
        @Header("Authorization") token: String,
    ): Response<List<DeliveryAddressDTO>>

    @DELETE("delivery_address/{id}")
    suspend fun deleteDeliveryAddressService(
        @Header("Authorization") token: String,
        @Path(value = "id") id: Long,
    ): Response<String>

    @POST("delivery_address")
    suspend fun addDeliveryAddressService(
        @Header("Authorization") token: String,
        @Body addressDTO: DeliveryAddressDTO,
    ): Response<String>

    @PUT("delivery_address")
    suspend fun updateDeliveryAddressService(
        @Header("Authorization") token: String,
        @Body addressDTO: DeliveryAddressDTO,
    ): Response<String>
}