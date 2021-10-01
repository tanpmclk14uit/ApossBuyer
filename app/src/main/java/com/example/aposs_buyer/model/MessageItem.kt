package com.example.aposs_buyer.model

import android.os.Parcel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


data class MessageItem(
    val id: Long,
    val massage: String,
    val time: LocalDateTime
){
  fun getTimeString() : String
  {
      return time.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
  }
}
