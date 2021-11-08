package com.example.aposs_buyer.responsitory.webservice

import com.example.aposs_buyer.model.dto.KindDTO
import retrofit2.http.GET

interface KindAPIService {

    @GET("kind/kind_dto")
    suspend fun getAllKind(): List<KindDTO>
}