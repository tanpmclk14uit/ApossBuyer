package com.example.aposs_buyer.responsitory.webservice

import com.example.aposs_buyer.model.dto.CartDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface CartService {

    @GET("cart")
    suspend fun getAllCart(
        @Header("Authorization") accessToken: String,
    ): Response<List<CartDTO>>
}