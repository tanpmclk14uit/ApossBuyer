package com.example.aposs_buyer.responsitory

import android.util.Log
import com.example.aposs_buyer.model.dto.SignInDTO
import com.example.aposs_buyer.model.dto.TokenDTO
import com.example.aposs_buyer.responsitory.webservice.AuthService
import com.example.aposs_buyer.responsitory.webservice.RetrofitInstance
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor() {

    private val authService: AuthService by lazy {
        RetrofitInstance.retrofit.create(AuthService::class.java)
    }

    suspend fun signIn(email: String, password: String):Response<TokenDTO>{
        val signInDTO: SignInDTO = SignInDTO(email, password);
        return authService.signIn(signInDTO)
    }
}