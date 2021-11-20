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
        @Path(value = "id") id: Long,
        @Header("Authorization") token: String,
    )

    @POST("delivery_address")
    suspend fun addDeliveryAddressService(
        @Body addressDTO: DeliveryAddressDTO,
        @Header("Authorization") token: String,
    )

    @PUT("delivery_address")
    suspend fun updateDeliveryAddressService(
        @Body addressDTO: DeliveryAddressDTO,
        @Header("Authorization") token: String,
    )
}