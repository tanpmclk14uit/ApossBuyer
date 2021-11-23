package com.example.aposs_buyer.model.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class DeliveryAddressDTO(
    val id: Long,
    val name: String,
    val gender: Boolean,
    val phoneNumber: String,
    val province: ProvinceDTO,
    val district: DistrictDTO,
    val ward: WardDTO,
    val addressLane: String,
    val isDefault: Boolean
)