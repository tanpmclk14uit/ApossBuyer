package com.example.aposs_buyer.responsitory

import com.example.aposs_buyer.responsitory.webservice.CategoryAPIService
import com.example.aposs_buyer.responsitory.webservice.RetrofitInstance
import com.example.aposs_buyer.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class CategoryRepository @Inject constructor() {

    val categoryService: CategoryAPIService by lazy {
        RetrofitInstance.retrofit.create(CategoryAPIService::class.java)
    }
}