package com.example.aposs_buyer.model.dto

import com.example.aposs_buyer.model.CartItem
import com.example.aposs_buyer.model.Image

data class CartItemDTO(
    val imageURL: String,
    val name: String,
    val price: Int,
    var amount: Int,
    var property: String
) {
    fun convertToCart(): CartItem{
        return CartItem(-1, Image(imageURL), name, price, amount, property, true)
    }
}
