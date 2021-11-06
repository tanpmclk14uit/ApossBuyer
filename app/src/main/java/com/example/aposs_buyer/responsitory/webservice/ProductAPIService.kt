package com.example.aposs_buyer.responsitory.webservice

import com.example.aposs_buyer.model.dto.ProductResponseDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.GET



interface ProductAPIService {

    @GET("products")
    fun getProductsAsync(): Deferred<ProductResponseDTO>

}