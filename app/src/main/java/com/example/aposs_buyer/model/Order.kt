package com.example.aposs_buyer.model

import android.annotation.SuppressLint
import com.example.aposs_buyer.utils.OrderStatus
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

data class Order(
    val id: Long,
    val orderTime: Date,
    val status: OrderStatus,
    val address: String,
    val billingItems: ArrayList<OrderBillingItem>,
    val totalPrice: Int
){
    fun totalPriceToString(): String{
        val formatter = DecimalFormat("#,###")
        val formattedNumber: String = formatter.format(totalPrice)
        return "$formattedNumber VNĐ"
    }
    @SuppressLint("SimpleDateFormat")
    fun getTimeString(): String{
        val simpleDate = SimpleDateFormat("HH:mm dd/MM/yyyy")
        return simpleDate.format(orderTime)
    }

    fun getStatusString():String{
        return status.toString()
    }
}
