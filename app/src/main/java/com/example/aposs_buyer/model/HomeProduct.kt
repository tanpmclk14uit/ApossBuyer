package com.example.aposs_buyer.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.DecimalFormat

@Parcelize
data class HomeProduct(
    val id: Long,
    val image: Image,
    val name: String,
    val price: Int,
    val purchased: Int,
) : Parcelable {
    fun priceToString(): String {
        val formatter = DecimalFormat("#,###")
        val formattedNumber: String = formatter.format(price)
        return "$formattedNumber VNĐ"
    }
}