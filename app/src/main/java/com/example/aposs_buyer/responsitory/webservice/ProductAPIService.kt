package com.example.aposs_buyer.responsitory.webservice

import com.example.aposs_buyer.model.dto.*
import dagger.hilt.internal.GeneratedEntryPoint
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

    @GET("products/kind/{id}")
    suspend fun getProductByKindId(
        @Path(value = "id") id: Long
    ): Response<ProductResponseDTO>

    @GET("products/{id}/properties")
    suspend fun getProductPropertiesById(
        @Path(value = "id") id: Long,
        @Query("isColor") isColor: Boolean,
    ): Response<List<ProductPropertyDTO>>

    @GET("products/{id}/ratings")
    suspend fun getProductRatingsById(
        @Path(value = "id") id: Long,
    ): Response<List<ProductRatingDTO>>

    @GET("products/{id}/property-values/quantity")
    suspend fun getProductQuantityByProductIdAndPropertyValue(
        @Path("id") id: Long,
        @Query("valueIds") valueIds: List<Long>
    ): Response<Int>

    @GET("products/{id}/property-values/additional-price")
    suspend fun getAdditionalPriceOfPropertyValuesAndProductId(
        @Path("id") id: Long,
        @Query("valueIds") valueIds: List<Long>
    ): Response<Int>
}