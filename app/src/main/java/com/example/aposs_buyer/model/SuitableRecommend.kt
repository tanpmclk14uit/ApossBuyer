package com.example.aposs_buyer.model

import com.example.aposs_buyer.utils.SuitableLever

data class SuitableRecommend(
    val suitableLever: SuitableLever,
    val explain: String,
    val extraRecommend: String
)