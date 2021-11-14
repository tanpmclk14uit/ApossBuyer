package com.example.aposs_buyer.model.dto

data class TokenDTO(
    val accessToken: String,
    val tokenType: String,
    val refreshToken: String,
){
    fun getFullToken(): String{
        return "$tokenType $accessToken"
    }
}