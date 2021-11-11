package com.example.aposs_buyer.responsitory.webservice

import com.example.aposs_buyer.model.dto.ProductResponseDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ProductAPIService {

    @GET("products")
    fun getProductsAsync(
        @Query("pageNo") pageNo: Int = 1
    ): Deferred<ProductResponseDTO>

    @GET("products/search")
    fun getProductsAsyncByKeyword(
        @Query("keyword") keyword: String = "",
        @Query("pageNo") pageNo: Int = 1
    ): Deferred<ProductResponseDTO>
}