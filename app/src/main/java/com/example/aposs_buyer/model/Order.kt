package com.example.aposs_buyer.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.example.aposs_buyer.utils.OrderStatus
import kotlinx.parcelize.Parcelize
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class Order(
    val id: Long =-1,
    val orderTime: Date = Date(),
    val status: OrderStatus = OrderStatus.Pending,
    val address: String = "",
    var billingItems: List<OrderBillingItem> = mutableListOf(),
    val totalPrice: Int = 0,
    var cancelReason: String? = null
) : Parcelable {

    fun totalPriceToString(): String {
        val formatter = DecimalFormat("#,###")
        val formattedNumber: String = formatter.format(totalPrice)
        return "$formattedNumber VNĐ"
    }

    @SuppressLint("SimpleDateFormat")
    fun getTimeString(): String {
        val simpleDate = SimpleDateFormat("HH:mm dd/MM/yyyy")
        return simpleDate.format(orderTime)
    }

    fun getStatusString(): String {
        return status.toString()
    }
}
