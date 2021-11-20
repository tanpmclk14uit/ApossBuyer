package com.example.aposs_buyer.responsitory.webservice

import com.example.aposs_buyer.model.dto.DistrictDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DistrictService {

    @POST("district")
    suspend fun getAllDistrictById(@Query("id") id: Long): Response<List<DistrictDTO>>
}