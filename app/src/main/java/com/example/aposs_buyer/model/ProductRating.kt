package com.example.aposs_buyer.model

data class ProductRating(
    val name: String,
    val rate: Float,
    val time: String,
    val content: String,
    val images: List<Image>,
    val avatar: Image?
)
