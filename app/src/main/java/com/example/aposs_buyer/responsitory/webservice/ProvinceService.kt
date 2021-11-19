package com.example.aposs_buyer.responsitory.webservice

import com.example.aposs_buyer.model.dto.ProvinceDTO
import retrofit2.http.GET

interface ProvinceService {
    @GET("province")
    suspend fun getAllProvince():List<ProvinceDTO>
}