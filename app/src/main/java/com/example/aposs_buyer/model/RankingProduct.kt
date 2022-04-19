package com.example.aposs_buyer.model

import java.text.DecimalFormat
import java.text.NumberFormat

data class RankingProduct(
    val id: Long,
    val image: Image,
    val name: String,
    val price: Int,
    val rating: Float,
    val totalPurchase: Int,
){
    fun priceToString(): String{
        val formatter: NumberFormat = DecimalFormat("#,###")
        val formattedNumber: String = formatter.format(price)
        return "$formattedNumber VNĐ"
    }
    fun totalPurchaseToString(): String{
        return "$totalPurchase purchased"
    }
}
