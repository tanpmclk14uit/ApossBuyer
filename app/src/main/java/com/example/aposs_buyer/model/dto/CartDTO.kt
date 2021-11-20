package com.example.aposs_buyer.model.dto

data class CartDTO(
    val id: Int,
    val productId: Int,
    val property: String,
    val quantity: Int,
    val imageUrl: String,
    val name: String,
    val price: Int
)