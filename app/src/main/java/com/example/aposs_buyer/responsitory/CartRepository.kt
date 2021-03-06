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
    suspend fun updateCart(accessToken: String, cartDTO: CartDTO): Response<String>{
        return cartService.updateCart(accessToken, cartDTO)
    }
    suspend fun deleteCart(accessToken: String, id: Long): Response<String>{
        return cartService.deleteCart(accessToken, id)
    }

    suspend fun addNewCart(accessToken: String, cartDTO: CartDTO): Response<String>{
        return cartService.addNewCart(accessToken,cartDTO)
    }
}