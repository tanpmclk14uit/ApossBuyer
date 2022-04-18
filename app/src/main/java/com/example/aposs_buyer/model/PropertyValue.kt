package com.example.aposs_buyer.model

data class PropertyValue(
    val id: Long,
    val name: String,
    val propertyId: Long,
    val value: String,
    var isChosen: Boolean
)
