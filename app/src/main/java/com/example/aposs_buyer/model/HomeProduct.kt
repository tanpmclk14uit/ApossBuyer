package com.example.aposs_buyer.model

data class HomeProduct(
    val image: Image,
    val name: String,
    val price: Int,
    val rating: Float,
    var isFavorite: Boolean
)