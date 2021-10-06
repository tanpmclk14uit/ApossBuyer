package com.example.aposs_buyer.model

data class ProductDetailProperty(
    val id: Long,
    val name: String,
    val values: List<PropertyValue>,
    var valueCountSummarize: Int,
)
