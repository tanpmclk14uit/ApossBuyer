package com.example.aposs_buyer.responsitory.webservice

import com.example.aposs_buyer.model.dto.BankingInformationDTO
import retrofit2.Response
import retrofit2.http.GET

interface BankingInformationService {

    @GET("banking-information")
    suspend fun getInformationService(): Response<BankingInformationDTO>
}