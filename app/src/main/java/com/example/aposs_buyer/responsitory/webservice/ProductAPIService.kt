package com.example.aposs_buyer.responsitory.webservice

import android.os.Parcelable
import com.example.aposs_buyer.model.HomeProduct
import com.example.aposs_buyer.model.dto.ProductDTO
import com.example.aposs_buyer.utils.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET



interface ProductAPIService {

    @GET("products")
    fun getProductsAsync(): Deferred<ProductDTO>

}