package com.example.aposs_buyer.model.dto

data class BankingInformationDTO(
    val bankName: String,
    val branch: String,
    val receiverName: String,
    val accountNumber: String
)
