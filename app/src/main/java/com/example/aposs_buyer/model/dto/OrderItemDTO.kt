package com.example.aposs_buyer.model.dto

data class OrderItemDTO(
    var id: Long=-1L,
    val cartId: Long,
    val setId: Long,
    var imageUrl: String="",
    var name: String="",
    var price: Int =0,
    var quantity: Int,
    var property: String="",
)
