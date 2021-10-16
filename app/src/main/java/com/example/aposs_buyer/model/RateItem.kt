package com.example.aposs_buyer.model

data class RateItem(
    val id: Long,
    val name: String,
    val property: String,
    val amount: Int,
    val price: Int,
    val rate: Float,
    val content: String,
    val productImage: Image,
    val images: List<RateImage>,
)
{
    fun getPriceFormat(): String{
        return "$$price"
    }

    fun getAmountFormat():String{
        return "Amount: $amount"
    }
}