package com.example.aposs_buyer.responsitory.webservice

import com.example.aposs_buyer.model.dto.DistrictDTO
import retrofit2.http.Body
import retrofit2.http.GET

interface DistrictService {

    @GET("district")
    suspend fun getAllDistrictById(@Body id: String): List<DistrictDTO>
}