package com.example.aposs_buyer.model.dto

import java.util.*

data class ProductRatingDTO(
    val content: String,
    val id: Long,
    val imageAvatarURl: String?,
    val listImageURL: List<String>,
    val name: String,
    val rating: Float,
    val time: String
)