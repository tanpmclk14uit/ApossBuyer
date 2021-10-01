package com.example.aposs_buyer.model

import java.text.DecimalFormat

data class FavoriteProduct(
    val id: Long,
    val image: Image,
    val name: String,

    val price: Int,
    val rating: Float,
    val isAvailable: Boolean
){
    fun priceToString(): String{
        val formatter = DecimalFormat("#,###")
        val formattedNumber: String = formatter.format(price)
        return "$formattedNumber VNĐ"
    }
}