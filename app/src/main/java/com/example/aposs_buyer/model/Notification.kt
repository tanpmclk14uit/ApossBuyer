package com.example.aposs_buyer.model

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

data class Notification(
    val title:String,
    val content: String,
    val time: Date,
){
    @SuppressLint("SimpleDateFormat")
    fun getTimeString(): String{
        val simpleDate = SimpleDateFormat("HH:mm dd/MM/yyyy")
        return simpleDate.format(time)
    }
}
