package com.example.aposs_buyer.responsitory

import com.example.aposs_buyer.responsitory.webservice.CategoryAPIService
import com.example.aposs_buyer.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class CategoryRepository @Inject constructor() {

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val retrofit = Retrofit.Builder()
                                   .addConverterFactory(MoshiConverterFactory.create(moshi))
                                   .baseUrl(Constants.BASE_URL)
                                    .build()

    val categoryService: CategoryAPIService by lazy {
        retrofit.create(CategoryAPIService::class.java)
    }
}