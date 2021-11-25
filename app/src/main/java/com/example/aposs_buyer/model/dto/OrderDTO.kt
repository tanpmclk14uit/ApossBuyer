package com.example.aposs_buyer.model.dto

import com.example.aposs_buyer.utils.OrderStatus
import com.squareup.moshi.JsonClass
import java.util.*
import javax.annotation.Nullable


data class OrderDTO(
    var id: Long,
    var orderTime: Date,
    var orderStatus: OrderStatus,
    var address: String,
    var totalPrice: Int,
    var cancelReason: String?,
    var orderItemDTOList: List<OrderItemDTO>,
)
