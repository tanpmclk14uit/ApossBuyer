package com.example.aposs_buyer.responsitory

import android.content.Context
import com.example.aposs_buyer.model.dto.SignInDTO
import com.example.aposs_buyer.model.dto.SignInWithSocialDTO
import com.example.aposs_buyer.model.dto.SignUpDTO
import com.example.aposs_buyer.model.dto.TokenDTO
import com.example.aposs_buyer.responsitory.database.AccountDatabase
import com.example.aposs_buyer.responsitory.webservice.AuthService
import com.example.aposs_buyer.responsitory.webservice.RetrofitInstance
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(
    @ApplicationContext val context: Context
) {

    private val authService: AuthService by lazy {
        RetrofitInstance.retrofit.create(AuthService::class.java)
    }

    suspend fun signIn(email: String, password: String): Response<TokenDTO> {
        val signInDTO = SignInDTO(email, password)
        return authService.signIn(signInDTO)
    }

    suspend fun getAccessTokenFromRefreshToken(refreshToken: String): Response<String> {
        return authService.getAccessToken(refreshToken)
    }

    suspend fun signUp(signUpDTO: SignUpDTO): Response<String> {
        return authService.signUp(signUpDTO)
    }

    suspend fun resentConfirmEmail(email: String): Response<String> {
        return authService.resentConfirmEmail(email)
    }

    suspend fun signInWithSocialAccount(signInWithSocialDTO: SignInWithSocialDTO): Response<TokenDTO> {
        return authService.signInWithSocialAccount(signInWithSocialDTO)
    }

    //Account with room database
    fun updateAccessToken(newAccessToken: String) {
        AccountDatabase.getInstance(context).accountDao.updateAccessToken(newAccessToken)
    }

    fun getAccount(): com.example.aposs_buyer.model.entity.Account? {
        return AccountDatabase.getInstance(context).accountDao.getAccount()
    }

    fun getCurrentAccessTokenFromRoom(): String? {
        val currentAccount = AccountDatabase.getInstance(context).accountDao.getAccount()
        return if (currentAccount != null) {
            "${currentAccount.tokenType} ${currentAccount.accessToken}"
        } else {
            null;
        }
    }
    fun getCurrentRefreshTokenFromRoom(): String? {
        return AccountDatabase.getInstance(context).accountDao.getAccount()?.refreshToken
    }

}