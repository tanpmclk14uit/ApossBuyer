package com.example.aposs_buyer.responsitory.webservice

import com.example.aposs_buyer.model.dto.CartDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT

interface CartService {

    @GET("cart")
    suspend fun getAllCart(
        @Header("Authorization") accessToken: String,
    ): Response<List<CartDTO>>

    @PUT("cart")
    suspend fun updateCart(
        @Header("Authorization") accessToken: String,
        @Body cartDTO: CartDTO
    ): Response<String>
}