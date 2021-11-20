package com.example.aposs_buyer.responsitory.webservice

import android.util.Log
import com.example.aposs_buyer.utils.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object RetrofitInstance {

    lateinit var retrofit: Retrofit;

    init {
        try {
            val client = OkHttpClient.Builder().apply {
                addInterceptor(MyInterceptor())
            }.build()

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
                .addCallAdapterFactory(CoroutineCallAdapterFactory()).baseUrl(
                    Constants.BASE_URL
                )
                .client(client)
                .build()
        } catch (e: Exception) {
            Log.d("Retrofit", "Server error!")
        }
    }


}