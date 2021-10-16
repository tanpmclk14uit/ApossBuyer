package com.example.aposs_buyer.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RateNowItem (
    val id: Int,
    val image: Image,
    val name: String,
    val price: Int,
    var amount: Int,
    val property: String,
): Parcelable
{
    fun getPriceFormat(): String{
        return "$$price"
    }

    fun getAmountFormat():String{
        return "Amount: $amount"
    }
}