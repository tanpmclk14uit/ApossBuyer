package com.example.aposs_buyer.model

data class Category(
    val id: Long,
    val name: String,
    val totalPurchase: Int,
    val totalProduct: Int,
    val rating: Float,
    val mainImage: Image
)
