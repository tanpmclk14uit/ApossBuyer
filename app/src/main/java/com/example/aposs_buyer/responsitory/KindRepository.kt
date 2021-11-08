package com.example.aposs_buyer.responsitory

import com.example.aposs_buyer.responsitory.webservice.KindAPIService
import com.example.aposs_buyer.utils.Constants.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class KindRepository @Inject constructor(){

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build()

    val kindService: KindAPIService by lazy {
        retrofit.create(KindAPIService::class.java)
    }
}