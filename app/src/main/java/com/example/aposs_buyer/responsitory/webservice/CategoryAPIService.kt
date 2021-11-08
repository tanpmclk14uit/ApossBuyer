package com.example.aposs_buyer.responsitory.webservice

import com.example.aposs_buyer.model.dto.DetailCategoryDTO
import com.example.aposs_buyer.utils.Constants.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET



interface CategoryAPIService{

    @GET("industry/detail_category")
    suspend fun getAllCategory(): List<DetailCategoryDTO>
}

