package com.example.aposs_buyer.responsitory

import com.example.aposs_buyer.model.dto.CartDTO
import com.example.aposs_buyer.responsitory.webservice.CartService
import com.example.aposs_buyer.responsitory.webservice.RetrofitInstance
import retrofit2.Response
import javax.inject.Inject

class CartRepository @Inject constructor() {
    private val cartService: CartService by lazy {
        RetrofitInstance.retrofit.create(CartService::class.java)
    }
    suspend fun getAllCart(accessToken: String): Response<List<CartDTO>> {
        return cartService.getAllCart(accessToken)
    }
}