package com.example.aposs_buyer.model


data class Address(
    val id: Long,
    var name: String = "",
    var gender: Boolean = true,
    var phoneNumber: String = "",
    var city: Province = Province(),
    var district: District = District(),
    var ward: Ward = Ward(),
    var addressLane: String = "",
    var isDefaultAddress: Boolean = false
) {
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
        return "City: ${city.name}"
    }

    fun getDistrictString(): String {
        return "District: ${district.name}"
    }

    fun getWardString(): String {
        return "Ward: ${ward.name}"
    }

    fun getAddressLaneString(): String {
        return "Address lane: $addressLane"
    }

    fun getFullAddress(): String {
        val genderCall: String = if (gender) {
            "Anh"
        } else {
            "Chị"
        }
        return "$genderCall $name, sđt: $phoneNumber, $addressLane, ${ward.name}, ${district.name}, ${city.name}"
    }

    fun equal(address: Address): Boolean {
        return this.name == address.name
                && this.gender == address.gender
                && this.phoneNumber == address.phoneNumber
                && this.city.name == address.city.name
                && this.district.name == address.district.name
                && this.ward.name == address.ward.name
                && this.addressLane == address.addressLane
                && this.isDefaultAddress == address.isDefaultAddress
    }

}
