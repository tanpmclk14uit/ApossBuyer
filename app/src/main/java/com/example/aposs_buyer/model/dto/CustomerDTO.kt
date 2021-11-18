package com.example.aposs_buyer.model.dto

import java.util.*

data class CustomerDTO(
    val birthDate: Date,
    val email: String,
    val gender: Boolean,
    val image: String,
    val name: String,
    val phoneNumber: String,
)
