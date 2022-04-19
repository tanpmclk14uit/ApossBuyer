package com.example.aposs_buyer.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.DecimalFormat

@Parcelize
data class CartItem(
    val id: Long,
    val product: Long,
    val setId: Long,
    val image: Image,
    val name: String,
    val price: Int,
    var amount: Int,
    val property: String,
    var isChoose: Boolean
):Parcelable
{
    fun getPriceFormat(): String{
        val formatter = DecimalFormat("#,###")
        val formattedNumber: String = formatter.format(price)
        return "$formattedNumber VNĐ"
    }

    fun getAmountFormat():String{
        return "Amount: $amount"
    }

    fun getAmountShortFormat():String{
        return "x$amount"
    }

}
