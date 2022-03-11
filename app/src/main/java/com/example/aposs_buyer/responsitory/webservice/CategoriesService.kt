package com.example.aposs_buyer.responsitory.webservice

import com.example.aposs_buyer.model.dto.DetailCategoryDTO
import retrofit2.Response
import retrofit2.http.GET


interface CategoriesService {

    @GET("industry/detail_category")
    suspend fun getAllCategory(): Response<List<DetailCategoryDTO>>
}

