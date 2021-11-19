package com.example.aposs_buyer.responsitory.webservice

import com.example.aposs_buyer.model.dto.WardDTO
import retrofit2.http.Body
import retrofit2.http.GET

interface WardService {
    @GET("ward")
    suspend fun getAllWardById(@Body id: String): List<WardDTO>
}