package com.example.aposs_buyer.responsitory

import com.example.aposs_buyer.responsitory.webservice.KindAPIService
import com.example.aposs_buyer.responsitory.webservice.RetrofitInstance
import com.example.aposs_buyer.utils.Constants.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class KindRepository @Inject constructor(){

    val kindService: KindAPIService by lazy {
        RetrofitInstance.retrofit.create(KindAPIService::class.java)
    }
}