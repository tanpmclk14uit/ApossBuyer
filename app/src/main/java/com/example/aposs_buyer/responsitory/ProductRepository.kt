package com.example.aposs_buyer.responsitory

import com.example.aposs_buyer.model.dto.ProductDetailDTO
import com.example.aposs_buyer.model.dto.ProductImageDTO
import com.example.aposs_buyer.responsitory.webservice.ProductAPIService
import com.example.aposs_buyer.responsitory.webservice.RetrofitInstance
import com.example.aposs_buyer.utils.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject


class ProductRepository @Inject constructor(){

    val productService: ProductAPIService by lazy {
        RetrofitInstance.retrofit.create(ProductAPIService::class.java)
    }
    suspend fun loadProductById(id: Long): Response<ProductDetailDTO>{
        return productService.getProductById(id)
    }

    suspend fun loadProductImageByProductId(id: Long): Response<List<ProductImageDTO>>{
        return productService.getProductImagesById(id)
    }
}