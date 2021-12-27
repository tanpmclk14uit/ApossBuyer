package com.example.aposs_buyer.model.dto

data class SignInWithSocialDTO(
    val email: String?,
    val name: String?,
    val imageURL: String?,
    val secretKey: String
)