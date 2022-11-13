package com.example.aposs_buyer.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.example.aposs_buyer.utils.OrderStatus
import com.example.aposs_buyer.utils.PaymentStatus
import kotlinx.parcelize.Parcelize
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class Order(
    val id: Long =-1,
    val orderTime: Date = Date(),
    val status: OrderStatus = OrderStatus.Pending,
    var address: String = "",
    var billingItems: List<OrderBillingItem> = mutableListOf(),
    val totalPrice: Int = 0,
    var cancelReason: String? = null,
    var paymentStatus: PaymentStatus = PaymentStatus.Waiting,
    var isOnlinePayment: Boolean = false,
    var isValidAddress: Boolean = false
) : Parcelable {

    fun totalPriceToString(): String {
        val formatter = DecimalFormat("#,###")
        val formattedNumber: String = formatter.format(totalPrice)
        return "$formattedNumber VNĐ"
    }

    @SuppressLint("SimpleDateFormat")
    fun getTimeString(): String {
        val simpleDate = SimpleDateFormat("HH:mm dd/MM/yyyy")
        return simpleDate.format(orderTime)
    }

    fun getStatusString(): String {
        return status.toShowString()
    }

    fun getPaymentStatusString(): String{
        return  paymentStatus.toShowString()
    }

    fun getPaymentMethodString(): String{
        return if (isOnlinePayment){
            "Trực tuyến"
        }else{
            "Tiền mặt"
        }
    }
}
