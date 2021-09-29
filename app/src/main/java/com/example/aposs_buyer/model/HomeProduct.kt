package com.example.aposs_buyer.model

import java.text.DecimalFormat
import java.text.NumberFormat

data class HomeProduct(
    val id: Long,
    val image: Image,
    val name: String,
    val price: Int,
    val rating: Float,
    var isFavorite: Boolean
){
    fun priceToString(): String{
        val formatter = DecimalFormat("#,###")
        val formattedNumber: String = formatter.format(price)
        return "$formattedNumber VNĐ"
    }
}