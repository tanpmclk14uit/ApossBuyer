package com.example.aposs_buyer.model

data class PropertyValue(
    val id: Long,
    val propertyId: Long,
    val value: String,
    val plusPrice: Int,
    val count: Int,
    var isChosen: Boolean
)
