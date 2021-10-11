package com.example.aposs_buyer.model

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
)
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

    fun getPhoneNumberString(): String
    {
        return "Phone: $phoneNumber"
    }

    fun getCityString(): String
    {
        return "District: $city"
    }

    fun getDistrictString(): String
    {
        return "District: $district"
    }

    fun getWardString(): String
    {
        return "District: $ward"
    }

    fun getAddressLaneString(): String
    {
        return "District: $addressLane"
    }
}
