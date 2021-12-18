package com.example.aposs_buyer.model.dto

data class OrderItemDTO(
    var id: Long,
    val product:Long,
    var imageUrl: String,
    var name: String,
    var price: Int,
    var quantity: Int,
    var property: String,
)
