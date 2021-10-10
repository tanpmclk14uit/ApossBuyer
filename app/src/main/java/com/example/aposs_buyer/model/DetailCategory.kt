package com.example.aposs_buyer.model

data class DetailCategory(val id: Long,
                          val name: String,
                          val totalPurchase: Int,
                          val totalProduct: Int,
                          val rating: Float,
                          val images: MutableList<Image>)
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