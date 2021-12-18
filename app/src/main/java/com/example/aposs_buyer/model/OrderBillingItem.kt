package com.example.aposs_buyer.model

import java.text.DecimalFormat

data class OrderBillingItem(
    val id: Long,
    val product:Long,
    val image: Image,
    val name: String,
    val price: Int,
    var amount: Int,
    var property: String
){
    fun priceToString(): String{
        val formatter = DecimalFormat("#,###")
        val formattedNumber: String = formatter.format(price)
        return "$formattedNumber VNĐ"
    }
    fun amountToString(): String{
        return "$amount x "
    }
    private fun getTotalPrice(): Int{
        return amount*price;
    }
    fun getTotalPriceToString(): String{
        val formatter = DecimalFormat("#,###")
        val formattedNumber: String = formatter.format(getTotalPrice())
        return "$formattedNumber VNĐ"
    }
}
