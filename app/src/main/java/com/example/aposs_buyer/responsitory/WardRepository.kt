package com.example.aposs_buyer.responsitory

import com.example.aposs_buyer.responsitory.webservice.RetrofitInstance
import com.example.aposs_buyer.responsitory.webservice.WardService
import javax.inject.Inject

class WardRepository @Inject constructor() {

    val wardService: WardService by lazy {
        RetrofitInstance.retrofit.create(WardService::class.java)
    }
}