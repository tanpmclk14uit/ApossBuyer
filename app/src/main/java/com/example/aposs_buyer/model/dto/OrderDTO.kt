package com.example.aposs_buyer.model.dto

import com.example.aposs_buyer.utils.OrderStatus
import com.example.aposs_buyer.utils.PaymentStatus
import com.squareup.moshi.JsonClass
import java.util.*
import javax.annotation.Nullable


data class OrderDTO(
    var id: Long=-1,
    var orderTime: Date= Date(),
    var orderStatus: OrderStatus = OrderStatus.Pending,
    var address: String,
    var totalPrice: Int =0,
    var cancelReason: String?=null,
    var isOnlinePayment: Boolean = false,
    var paymentStatus: PaymentStatus = PaymentStatus.Waiting,
    var orderItemDTOList: List<OrderItemDTO>,
)
