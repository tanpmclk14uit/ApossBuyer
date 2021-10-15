package com.example.aposs_buyer.model

import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Address(
    val id: Long,
    val name: String,
    val gender: Boolean,
    val phoneNumber: String,
    val city: String,
    val district: String,
    val ward: String,
    val addressLane: String,
    var isDefault: Boolean
): Parcelable
{
    fun getNameString(): String
    {
        return "Name: $name"
    }

    fun getGenderString():String
    {
        if (gender) return "Gender: Male"
        return "Gender: Female"
    }

    fun getGenderSmallString(): String
    {
        if (gender) return "Male"
        return "Female"
    }

    fun getPhoneNumberString(): String
    {
        return "Phone: $phoneNumber"
    }

    fun getCityString(): String
    {
        return "City: $city"
    }

    fun getDistrictString(): String
    {
        return "District: $district"
    }

    fun getWardString(): String
    {
        return "Ward: $ward"
    }

    fun getAddressLaneString(): String
    {
        return "Address lane: $addressLane"
    }

}
