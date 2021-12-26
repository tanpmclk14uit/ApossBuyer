package com.example.aposs_buyer.responsitory.webservice

import com.example.aposs_buyer.model.dto.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface AuthService {

    @POST("auth/sign-in")
    suspend fun signIn(
        @Body signInDTO: SignInDTO
    ): Response<TokenDTO>

    @POST("auth/sign-in-with-social-account")
    suspend fun signInWithSocialAccount(
        @Body signInWithSocialDTO: SignInWithSocialDTO
    ): Response<TokenDTO>

    @POST("auth/access-token")
    suspend fun getAccessToken(
        @Body refreshToken: String
    ): Response<String>

    @POST("auth/sign-up")
    suspend fun signUp(
        @Body signUpDTO: SignUpDTO
    ):Response<String>

    @GET("auth/resent-confirm")
    suspend fun resentConfirmEmail (
        @Query("email") email: String
    ):Response<String>


}