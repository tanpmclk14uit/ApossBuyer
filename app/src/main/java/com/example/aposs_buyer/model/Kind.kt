package com.example.aposs_buyer.model

data class Kind(
    var id: Long,
    val name: String,
    val totalPurchase: Int,
    val totalProduct: Int,
    val rating: Float,
    val Products: List<HomeProduct>
)
{
    fun displayToTalPurchase(): String
    {
        return "$totalPurchase purchased"
    }

    fun displayToTalProduct(): String
    {
        return "$totalProduct products"
    }
}
