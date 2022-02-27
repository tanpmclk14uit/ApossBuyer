package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aposs_buyer.model.Address
import com.example.aposs_buyer.model.District
import com.example.aposs_buyer.model.Province
import com.example.aposs_buyer.model.Ward
import com.example.aposs_buyer.model.dto.DeliveryAddressDTO
import com.example.aposs_buyer.model.dto.DistrictDTO
import com.example.aposs_buyer.model.dto.ProvinceDTO
import com.example.aposs_buyer.model.dto.WardDTO
import com.example.aposs_buyer.responsitory.*
import com.example.aposs_buyer.utils.AddingStatus
import com.example.aposs_buyer.utils.BridgeObject
import com.example.aposs_buyer.utils.LoadingStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import java.util.stream.Collectors
import javax.inject.Inject

@HiltViewModel
class AddressDialogViewModel @Inject constructor(
    private val provinceRepository: ProvinceRepository,
    private val districtRepository: DistrictRepository,
    private val wardRepository: WardRepository,
    private val authRepository: AuthRepository,
    private val deliveryAddressRepository: DeliveryAddressRepository
) : ViewModel() {

    val address = MutableLiveData<Address>()
    var nameErrorMessage: String? = ""
    var cellNumberErrorMessage: String? = ""
    var name: MutableLiveData<String> = MutableLiveData()
    var cellNumber: MutableLiveData<String> = MutableLiveData()
    var isDefault: MutableLiveData<Boolean> = MutableLiveData(false)
    val listProvince = MutableLiveData<MutableList<Province>>()

    val listDistrict = MutableLiveData<MutableList<District>>()

    val listWard = MutableLiveData<MutableList<Ward>>()

    val listAddress = MutableLiveData<MutableList<Address>>()

    private val status = MutableLiveData<LoadingStatus>()
    val addingStatus = MutableLiveData<AddingStatus>()


    init {
        loadProvince()
    }

    fun isChangeName(newName: String): Boolean {
        return address.value!!.name != newName
    }

    fun isChangeGender(newGender: String): Boolean {
        val newGenderBool: Boolean = newGender == "Male"
        return address.value!!.gender == newGenderBool
    }

    fun isChangeCity(newCity: String): Boolean {
        return address.value!!.city == newCity
    }

    fun isChangeDistrict(newDistrict: String): Boolean {
        return address.value!!.district == newDistrict
    }

    fun isChangeWard(newWard: String): Boolean {
        return address.value!!.ward == newWard
    }

    fun isChangeAddressLane(newAddressLane: String): Boolean {
        return address.value!!.addressLane == newAddressLane
    }

    fun isChangePhone(newPhone: String): Boolean {
        return address.value!!.phoneNumber == newPhone
    }

    private fun loadUserAddress() {
        listAddress.value = mutableListOf()
        status.value = LoadingStatus.Loading
        viewModelScope.launch {
            try {
                val token = authRepository.getCurrentAccessTokenFromRoom()
                if (token != null) {
                    val response =
                        deliveryAddressRepository.deliveryAddressService.getAllDeliveryAddressService(
                            token
                        )
                    if (response.isSuccessful) {
                        val listDeliveryAddressDTO = response.body()
                        listAddress.value = listDeliveryAddressDTO!!.stream().map {
                            convertDeliveryAddressDTOToAddress(it)
                        }.collect(Collectors.toList())
                        status.value = LoadingStatus.Success
                    } else {
                        if (response.code() == 401) {
                            val refreshToken = authRepository.getCurrentRefreshTokenFromRoom()
                            if (refreshToken != null) {
                                getNewAccessTokenFromRefreshToken(refreshToken)
                                loadUserAddress()
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                status.value = LoadingStatus.Fail
                Log.e("Exception", e.toString())
            }
        }
    }

    private fun convertDeliveryAddressDTOToAddress(deliveryAddressDTO: DeliveryAddressDTO): Address {
        return Address(
            id = deliveryAddressDTO.id,
            name = deliveryAddressDTO.name,
            gender = deliveryAddressDTO.gender,
            phoneNumber = deliveryAddressDTO.phoneNumber,
            city = deliveryAddressDTO.province.name,
            district = deliveryAddressDTO.district.name,
            ward = deliveryAddressDTO.ward.name,
            isDefault = deliveryAddressDTO.isDefault,
            addressLane = deliveryAddressDTO.addressLane
        )
    }

    fun onAddNewAddress(address: Address) {
        val deliveryAddressDTO = convertAddressToDeliveryAddressDTO(address)
        addingStatus.value = AddingStatus.Loading
        viewModelScope.launch {
            try {
                val token = authRepository.getCurrentAccessTokenFromRoom()
                if (token != null) {
                    val response =
                        deliveryAddressRepository.deliveryAddressService.addDeliveryAddressService(
                            token,
                            deliveryAddressDTO
                        )
                    if (response.isSuccessful) {
                        addingStatus.value = AddingStatus.Success
                        BridgeObject.addressListChange.value =
                            !BridgeObject.addressListChange.value!!
                        loadUserAddress()
                    } else {
                        if (response.code() == 401) {
                            val refreshToken = authRepository.getCurrentRefreshTokenFromRoom()
                            if (refreshToken != null) {
                                getNewAccessTokenFromRefreshToken(refreshToken)
                                onAddNewAddress(address)
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                addingStatus.value = AddingStatus.Fail
                Log.e("Exception", e.toString())
            }
        }
    }

    private suspend fun getNewAccessTokenFromRefreshToken(refreshToken: String) {
        viewModelScope.launch {
            val newAccessTokenResponse = authRepository.getAccessTokenFromRefreshToken(refreshToken)
            if (newAccessTokenResponse.isSuccessful) {
                val newAccessToken =
                    authRepository.getAccessTokenFromRefreshToken(refreshToken).body()!!
                authRepository.updateAccessToken(newAccessToken)
            }
        }
    }

    private fun convertAddressToDeliveryAddressDTO(address: Address): DeliveryAddressDTO {
        return DeliveryAddressDTO(
            id = address.id,
            name = address.name,
            gender = address.gender,
            addressLane = address.addressLane,
            phoneNumber = address.phoneNumber,
            isDefault = address.isDefault,
            ward = convertCurrentNameToWardDTO(address.ward),
            district = convertCurrentNameToDistrictDTO(address.district),
            province = convertCurrentNameToProvinceDTO(address.city)
        )
    }

    private fun convertCurrentNameToDistrictDTO(name: String): DistrictDTO {
        if (listDistrict.value != null) {
            for (i in 0 until listDistrict.value!!.size) {
                val district = listDistrict.value!![i]
                if (name == district.name) {
                    return convertFromDistrictToDistrictDTO(district)
                }
            }
        }
        return DistrictDTO(1, "Quận Ba Đình", 1)
    }

    private fun convertCurrentNameToProvinceDTO(name: String): ProvinceDTO {
        if (listProvince.value != null) {
            for (i in 0 until listProvince.value!!.size) {
                val province = listProvince.value!![i]
                if (name == province.name) {
                    return convertFromProvinceToProvinceDTO(province)
                }
            }
        }
        return ProvinceDTO(1, "Thành phố Hà Nội")
    }

    private fun convertCurrentNameToWardDTO(name: String): WardDTO {
        if (listWard.value != null) {
            for (i in 0 until listWard.value!!.size) {
                val ward = listWard.value!![i]
                if (name == ward.name) {
                    return convertFromWardToWardDTO(ward)
                }
            }
        }
        return WardDTO(1, "Phường Phúc Xá", 1)
    }

    fun onUpdateAddress(address: Address) {
        val deliveryAddressDTO = convertAddressToDeliveryAddressDTO(address)
        addingStatus.value = AddingStatus.Loading
        viewModelScope.launch {
            try {
                val token = authRepository.getCurrentAccessTokenFromRoom()
                if (token != null) {
                    val response =
                        deliveryAddressRepository.deliveryAddressService.updateDeliveryAddressService(
                            token,
                            deliveryAddressDTO
                        )
                    if (response.isSuccessful) {
                        addingStatus.value = AddingStatus.Success
                        BridgeObject.addressListChange.value =
                            !BridgeObject.addressListChange.value!!
                    } else {
                        if (response.code() == 401) {
                            val refreshToken = authRepository.getCurrentRefreshTokenFromRoom()
                            if (refreshToken != null) {
                                getNewAccessTokenFromRefreshToken(refreshToken)
                                onUpdateAddress(address)
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                addingStatus.value = AddingStatus.Fail
                Log.e("Exception", e.toString())
            }
        }
    }

    private fun isNameContainNumberOrSpecialCharacter(name: String): Boolean {
        val hasNumber: Boolean = Pattern.compile(
            "[0-9]"
        ).matcher(name).find()
        val hasSpecialCharacter: Boolean = Pattern.compile(
            "[!@#$%&.,\"':;?*()_+=|<>{}\\[\\]~-]"
        ).matcher(name).find()
        return hasNumber || hasSpecialCharacter
    }

    fun isValidName(): Boolean {
        return if (name.value!!.isBlank()) {
            nameErrorMessage = "Name can not empty"
            false
        } else {
            return if (isNameContainNumberOrSpecialCharacter(name.value!!)) {
                nameErrorMessage = "Name can't contain special character"
                false
            } else {
                nameErrorMessage = null
                true
            }
        }
    }

    private fun isPhoneNumberRightFormat(str: String): Boolean {
        val regex = "(84|0[35789])+([0-9]{8})\\b".toRegex()
        return str.matches(regex)
    }

    fun isValidPhoneNumber(): Boolean {
        return if (cellNumber.value!!.isBlank()) {
            cellNumberErrorMessage = "Phone number is require!"
            false
        } else {
            return if (!isPhoneNumberRightFormat(cellNumber.value!!)) {
                cellNumberErrorMessage = "Phone number in wrong format"
                false
            } else {
                cellNumberErrorMessage = null
                true
            }
        }
    }

    fun loadProvince() {
        listProvince.value = mutableListOf()
        status.value = LoadingStatus.Loading
        viewModelScope.launch {
            val response = provinceRepository.provinceService.getAllProvince()
            try {
                listProvince.value = response.body()!!.stream().map {
                    convertFromProvinceDTOToProvince(it)
                }.collect(Collectors.toList())
                status.value = LoadingStatus.Success
            } catch (e: Exception) {
                Log.e("Exception", e.message!!)
                status.value = LoadingStatus.Fail
            }
        }
    }

    fun loadDistrictByProvince(choseProvince: Long) {
        listDistrict.value = mutableListOf()
        status.value = LoadingStatus.Loading
        viewModelScope.launch {
            val response = districtRepository.districtService.getAllDistrictById(choseProvince)
            try {
                listDistrict.value = response.body()!!.stream().map {
                    convertFromDistrictDTOToDistrict(it)
                }.collect(Collectors.toList())
                status.value = LoadingStatus.Success
            } catch (e: Exception) {
                Log.e("Exception", e.toString())
                status.value = LoadingStatus.Fail
            }
        }
    }

    fun loadWardByDistrict(choseDistrict: Long) {
        listWard.value = mutableListOf()
        status.value = LoadingStatus.Loading
        viewModelScope.launch {
            val response = wardRepository.wardService.getAllWardById(choseDistrict)
            try {
                listWard.value = response.body()!!.stream().map {
                    convertFromWardDTOToWard(it)
                }.collect(Collectors.toList())
                status.value = LoadingStatus.Success
            } catch (e: Exception) {
                Log.e("Exception", e.message!!)
                status.value = LoadingStatus.Fail
            }
        }
    }

    private fun convertFromProvinceDTOToProvince(provinceDTO: ProvinceDTO): Province {
        return Province(provinceDTO.id, provinceDTO.name)
    }

    private fun convertFromDistrictDTOToDistrict(districtDTO: DistrictDTO): District {
        return District(districtDTO.id, districtDTO.name, districtDTO.province)
    }

    private fun convertFromWardDTOToWard(wardDTO: WardDTO): Ward {
        return Ward(wardDTO.id, wardDTO.name, wardDTO.district)
    }

    private fun convertFromProvinceToProvinceDTO(province: Province): ProvinceDTO {
        return ProvinceDTO(province.id, province.name)
    }

    private fun convertFromDistrictToDistrictDTO(district: District): DistrictDTO {
        return DistrictDTO(district.id, district.name, district.province)
    }

    private fun convertFromWardToWardDTO(ward: Ward): WardDTO {
        return WardDTO(ward.id, ward.name, ward.district)
    }

    fun deleteDeliveryAddress(id: Long) {
        addingStatus.value = AddingStatus.Loading
        viewModelScope.launch {
            try {
                val token = authRepository.getCurrentAccessTokenFromRoom()
                if (token != null) {
                    val response =
                        deliveryAddressRepository.deliveryAddressService.deleteDeliveryAddressService(
                            token,
                            id
                        )
                    if (response.isSuccessful) {
                        addingStatus.value = AddingStatus.Success
                        BridgeObject.addressListChange.value =
                            !BridgeObject.addressListChange.value!!
                        loadUserAddress()
                    } else {
                        if (response.code() == 401) {
                            val refreshToken = authRepository.getCurrentRefreshTokenFromRoom()
                            if (refreshToken != null) {
                                getNewAccessTokenFromRefreshToken(refreshToken)
                                deleteDeliveryAddress(id)
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                addingStatus.value = AddingStatus.Fail
                Log.e("Exception", e.toString())
            }
        }
    }

    fun positionOfProvince(name: String): Int {
        var position = 0
        if (listProvince.value != null) {
            for (i in 0 until listProvince.value!!.size) {
                if (name == listProvince.value!![i].name) {
                    position = i
                    return position
                }
            }
        }
        return position
    }

    fun positionOfDistrict(name: String): Int {
        var position = 0
        if (listDistrict.value != null) {
            for (i in 0 until listDistrict.value!!.size) {
                if (name == listDistrict.value!![i].name) {
                    position = i
                    return position
                }
            }
        }
        return position
    }

    fun positionOfWard(name: String): Int {
        var position = 0
        if (listWard.value != null) {
            for (i in 0 until listWard.value!!.size) {
                if (name == listWard.value!![i].name) {
                    position = i
                    return position
                }
            }
        }
        return position
    }
}