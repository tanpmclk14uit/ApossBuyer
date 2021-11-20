package com.example.aposs_buyer.responsitory.webservice

import com.example.aposs_buyer.model.dto.WardDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WardService {
    @POST("ward")
    suspend fun getAllWardById(@Body id: Long): Response<List<WardDTO>>
}