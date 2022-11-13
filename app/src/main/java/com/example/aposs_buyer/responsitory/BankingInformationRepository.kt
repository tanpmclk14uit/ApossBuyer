package com.example.aposs_buyer.responsitory

import com.example.aposs_buyer.model.dto.BankingInformationDTO
import com.example.aposs_buyer.responsitory.webservice.BankingInformationService
import com.example.aposs_buyer.responsitory.webservice.RetrofitInstance
import retrofit2.Response
import javax.inject.Inject

class BankingInformationRepository @Inject constructor() {
    private val bankingInformationService: BankingInformationService by lazy {
        RetrofitInstance.retrofit.create(BankingInformationService::class.java)
    }

    suspend fun getBankingInformationData(): Response<BankingInformationDTO>{
        return bankingInformationService.getInformationService()
    }
}