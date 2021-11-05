package com.example.aposs_buyer.model.dto

import com.example.aposs_buyer.model.HomeProduct


data class ProductDTO (
    //val content: List<HomeProduct>,
    val pageNo: Int = 0,
    val pageSize: Int = 0,
    val totalElements: Long = 0,
    val totalPages: Int = 0,
    val last: Boolean = false,
)