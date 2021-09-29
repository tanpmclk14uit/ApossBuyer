package com.example.aposs_buyer.model

data class RankingProduct(
    val id: Long,
    val image: Image,
    val name: String,
    val price: Int,
    val rating: Float,
    var isFavorite: Boolean,
    val totalPurchase: Int,
    var kind: String
){
    fun priceToString(): String{
        return "$price VNĐ"
    }
    fun totalPurchaseToString(): String{
        return "$totalPurchase purchased"
    }
}
