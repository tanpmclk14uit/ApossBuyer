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
        this.gender = genderString == "Nam"
    }

    fun getNameString(): String {
        return "Họ và tên: $name"
    }

    fun getGenderString(): String {
        if (gender) return "Giới tính: Nam"
        return "Giới tính: Nữ"
    }

    fun getGenderSmallString(): String {
        if (gender) return "Nam"
        return "Nữ"
    }

    fun getPhoneNumberString(): String {
        return "Số điện thoại: $phoneNumber"
    }

    fun getCityString(): String {
        return "${city.name}"
    }

    fun getDistrictString(): String {
        return "${district.name}"
    }

    fun getWardString(): String {
        return "${ward.name}"
    }

    fun getAddressLaneString(): String {
        return "Chi tiết: $addressLane"
    }

    fun getFullAddress(): String {

        if(name.isEmpty()){
            return "Không có địa chỉ mặc định, tạo mới ngay thôi!"
        }
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
