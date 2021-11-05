package com.example.aposs_buyer.responsitory

import com.example.aposs_buyer.responsitory.webservice.ProductAPIService
import com.example.aposs_buyer.utils.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject


class ProductRepository @Inject constructor(){

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit =
        Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).baseUrl(
                Constants.BASE_URL
            ).build()

    val productService: ProductAPIService by lazy {
        retrofit.create(ProductAPIService::class.java)
    }

}