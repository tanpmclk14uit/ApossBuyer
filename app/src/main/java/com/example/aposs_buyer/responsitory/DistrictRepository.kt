package com.example.aposs_buyer.responsitory

import com.example.aposs_buyer.responsitory.webservice.DistrictService
import com.example.aposs_buyer.responsitory.webservice.RetrofitInstance
import javax.inject.Inject

class DistrictRepository @Inject constructor() {

    val districtService: DistrictService by lazy {
        RetrofitInstance.retrofit.create(DistrictService::class.java)
    }
}