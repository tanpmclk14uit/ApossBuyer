package com.example.aposs_buyer.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Address(
    val id: Long,
    var name: String = "",
    var gender: Boolean = true,
    var phoneNumber: String = "",
    var city: String = "",
    var district: String = "",
    var ward: String = "",
    var addressLane: String = "",
    var isDefaultAddress: Boolean = false
) : Parcelable {
    fun setGenderFromString(genderString: String) {
        this.gender = genderString == "Male"
    }

    fun getNameString(): String {
        return "Name: $name"
    }

    fun getGenderString(): String {
        if (gender) return "Gender: Male"
        return "Gender: Female"
    }

    fun getGenderSmallString(): String {
        if (gender) return "Male"
        return "Female"
    }

    fun getPhoneNumberString(): String {
        return "Phone: $phoneNumber"
    }

    fun getCityString(): String {
        return "City: $city"
    }

    fun getDistrictString(): String {
        return "District: $district"
    }

    fun getWardString(): String {
        return "Ward: $ward"
    }

    fun getAddressLaneString(): String {
        return "Address lane: $addressLane"
    }

    fun getFullAddress(): String {
        return "$addressLane, $ward, $district, $city"
    }

}
