package com.example.aposs_buyer.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartItem(
    val id: Int,
    val image: Image,
    val name: String,
    val price: Int,
    var amount: Int,
    val property: String,
    var isChoose: Boolean
):Parcelable
{
    fun getPriceFormat(): String{
        return "$$price"
    }

    fun getAmountFormat():String{
        return "Amount: $amount"
    }

    fun getAmountShortFormat():String{
        return "x$amount"
    }

}
