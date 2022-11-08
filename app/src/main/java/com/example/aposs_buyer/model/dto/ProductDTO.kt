package com.example.aposs_buyer.model.dto

import com.example.aposs_buyer.model.HomeProduct
import com.squareup.moshi.JsonClass

data class ProductDTO (
    val id: Long,
    val image: String,
    val name: String,
    val price: Int,
    val purchased: Int,
)