package com.example.aposs_buyer.model.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class DistrictDTO(
    val id: Long,
    val name: String,
    val province: Long,
)
