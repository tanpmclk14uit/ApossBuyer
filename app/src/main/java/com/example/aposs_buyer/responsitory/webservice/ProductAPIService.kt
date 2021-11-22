package com.example.aposs_buyer.responsitory.webservice

import com.example.aposs_buyer.model.dto.ProductDetailDTO
import com.example.aposs_buyer.model.dto.ProductImageDTO
import com.example.aposs_buyer.model.dto.ProductResponseDTO
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ProductAPIService {
    @GET("products")
    fun getProductsAsync(
        @Query("pageNo") pageNo: Int = 1
    ): Deferred<ProductResponseDTO>

    @GET("products/search")
    fun getProductsByKeywordAsync(
        @Query("keyword") keyword: String = "",
        @Query("pageNo") pageNo: Int = 1,
        @Query("sortBy") sortBy: String = "id",
        @Query("sortDir") sortDir: String = "asc",
    ): Deferred<ProductResponseDTO>

    @GET("products/{id}")
    suspend fun getProductById(
        @Path(value = "id") id: Long
    ): Response<ProductDetailDTO>

    @GET("products/{id}/images")
    suspend fun getProductImagesById(
        @Path(value = "id") id: Long
    ): Response<List<ProductImageDTO>>

}