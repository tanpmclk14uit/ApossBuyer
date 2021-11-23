package com.example.aposs_buyer.responsitory

import com.example.aposs_buyer.responsitory.webservice.ProvinceService
import com.example.aposs_buyer.responsitory.webservice.RetrofitInstance
import javax.inject.Inject

class ProvinceRepository @Inject constructor() {

    val provinceService: ProvinceService by lazy {
        RetrofitInstance.retrofit.create(ProvinceService::class.java)
    }
}