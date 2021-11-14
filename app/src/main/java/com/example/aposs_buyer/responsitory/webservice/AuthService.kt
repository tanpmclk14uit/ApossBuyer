package com.example.aposs_buyer.responsitory.webservice

import com.example.aposs_buyer.model.dto.SignInDTO
import com.example.aposs_buyer.model.dto.TokenDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("auth/sign-in")
    suspend fun signIn(
        @Body signInDTO: SignInDTO
    ): Response<TokenDTO>

}