package com.example.aposs_buyer.model.dto

data class CartDTO(
    var id: Long = -1,
    var setId: Long,
    var property: String ="",
    var quantity: Int,
    var imageUrl: String ="",
    var name: String ="",
    var price: Int =0,
    var select: Boolean
)
