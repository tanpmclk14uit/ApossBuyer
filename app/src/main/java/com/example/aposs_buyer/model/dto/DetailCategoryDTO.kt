package com.example.aposs_buyer.model.dto

data class DetailCategoryDTO(
    val id: Long,
    val name: String,
    val totalPurchases: Int,
    val totalProducts: Int,
    val rating: Float,
    val images: List<String>
)
