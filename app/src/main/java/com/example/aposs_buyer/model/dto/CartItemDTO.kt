package com.example.aposs_buyer.model.dto

import com.example.aposs_buyer.model.Image

data class CartItemDTO(
    val imageURL: String,
    val name: String,
    val price: Int,
    var amount: Int,
    var property: String
)
