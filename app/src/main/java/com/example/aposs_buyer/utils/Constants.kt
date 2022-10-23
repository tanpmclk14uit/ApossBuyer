package com.example.aposs_buyer.utils

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Constants {
    const val BASE_URL = "https://aposs-backend-rkd5z6hxoa-as.a.run.app/api/v1/"
}