package com.example.aposs_buyer.responsitory.webservice

import com.example.aposs_buyer.model.dto.CustomerDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface CustomerService {

    @GET("customer/{token}")
    fun getCustomerByToken(
        @Header("Authorization") tokenHeader: String,
        @Path (value = "token") token: String
    ): Response<CustomerDTO>

}