package com.example.aposs_buyer.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.DecimalFormat

@Parcelize
data class OrderBillingItem(
    val id: Long=-1,
    val cartId: Long =-1,
    val setId: Long,
    val image: Image = Image(""),
    val name: String ="",
    val price: Int =0,
    var quantity: Int,
    var property: String ="",
) : Parcelable {
    fun priceToString(): String {
        val formatter = DecimalFormat("#,###")
        val formattedNumber: String = formatter.format(price)
        return "$formattedNumber VNĐ"
    }

    fun amountToString(): String {
        return "$quantity x "
    }

    private fun getTotalPrice(): Int {
        return quantity * price
    }

    fun getTotalPriceToString(): String {
        val formatter = DecimalFormat("#,###")
        val formattedNumber: String = formatter.format(getTotalPrice())
        return "$formattedNumber VNĐ"
    }
}
