package com.example.aposs_buyer.model.dto

data class KindDTO(
    val id: Long,
    val name:String,
    val totalPurchases: Int,
    val totalProducts: Int,
    val rating: Float,
    val image: String,
    val products: List<ProductDTO>,
    val category: Long,
)
