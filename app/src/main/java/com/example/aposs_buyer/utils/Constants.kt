package com.example.aposs_buyer.utils

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Constants {
    const val BASE_URL = "https://fe28-2001-ee0-4f50-e870-6118-22fc-a383-75fb.ap.ngrok.io/api/v1/"
}