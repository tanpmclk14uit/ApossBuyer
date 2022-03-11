package com.example.aposs_buyer.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: Long,
    val name: String = "",
    val totalPurchase: Int =0,
    val totalProduct: Int=0,
    val rating: Float = 0f,
    val mainImage: Image = Image("")
) : Parcelable
