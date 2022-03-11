package com.example.aposs_buyer.responsitory.webservice

import com.example.aposs_buyer.model.dto.KindDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryService {

    @GET("kind")
    suspend fun getAllKindOfCategoryByCategoryId(
        @Query("categoryId") categoryId: Long,
    ): Response<List<KindDTO>>
}