package com.example.aposs_buyer.model

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

data class OrderDeliveringState(
    val id: Long,
    val title: String,
    val time: Date
){
    @SuppressLint("SimpleDateFormat")
    fun getTimeString(): String{
        val simpleDate = SimpleDateFormat("HH:mm - dd/MM/yyyy")
        return simpleDate.format(time)
    }
}
