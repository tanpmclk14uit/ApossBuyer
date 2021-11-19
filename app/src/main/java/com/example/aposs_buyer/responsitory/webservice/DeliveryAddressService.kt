package com.example.aposs_buyer.responsitory.webservice

import com.example.aposs_buyer.model.dto.DeliveryAddressDTO
import com.example.aposs_buyer.model.dto.TokenDTO
import okhttp3.Request
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface DeliveryAddressService {

    @GET("delivery_address")
    suspend fun getAllDeliveryAddressService(
        @Header("Authorization") token: String,
    ): Response<List<DeliveryAddressDTO>>
}