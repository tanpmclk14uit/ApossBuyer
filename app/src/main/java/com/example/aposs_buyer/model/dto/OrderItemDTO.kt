package com.example.aposs_buyer.model.dto

data class OrderItemDTO(
    var id: Long,
    var imageUrl: String,
    var name: String,
    var price: Int,
    var amount: Int,
    var property: String,
)
