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
        //loadProvince()
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
            isDefaultAddress = deliveryAddressDTO.isDefault,
            addressLane = deliveryAddressDTO.addressLane
        )
    }

//    fun onAddNewAddress(address: Address) {
//        val deliveryAddressDTO = convertAddressToDeliveryAddressDTO(address)
//        addingStatus.value = AddingStatus.Loading
//        viewModelScope.launch {
//            try {
//                val token = authRepository.getCurrentAccessTokenFromRoom()
//                if (token != null) {
//                    val response =
//                        deliveryAddressRepository.deliveryAddressService.addDeliveryAddressService(
//                            token,
//                            deliveryAddressDTO
//                        )
//                    if (response.isSuccessful) {
//                        addingStatus.value = AddingStatus.Success
//                        BridgeObject.addressListChange.value =
//                            !BridgeObject.addressListChange.value!!
//                        loadUserAddress()
//                    } else {
//                        if (response.code() == 401) {
//                            val refreshToken = authRepository.getCurrentRefreshTokenFromRoom()
//                            if (refreshToken != null) {
//                                getNewAccessTokenFromRefreshToken(refreshToken)
//                                onAddNewAddress(address)
//                            }
//                        }
//                    }
//                }
//            } catch (e: Exception) {
//                addingStatus.value = AddingStatus.Fail
//                Log.e("Exception", e.toString())
//            }
//        }
//    }


    private fun convertAddressToDeliveryAddressDTO(address: Address): DeliveryAddressDTO {
        return DeliveryAddressDTO(
            id = address.id,
            name = address.name,
            gender = address.gender,
            addressLane = address.addressLane,
            phoneNumber = address.phoneNumber,
            isDefault = address.isDefaultAddress,
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

//    fun onUpdateAddress(address: Address) {
//        val deliveryAddressDTO = convertAddressToDeliveryAddressDTO(address)
//        addingStatus.value = AddingStatus.Loading
//        viewModelScope.launch {
//            try {
//                val token = authRepository.getCurrentAccessTokenFromRoom()
//                if (token != null) {
//                    val response =
//                        deliveryAddressRepository.deliveryAddressService.updateDeliveryAddressService(
//                            token,
//                            deliveryAddressDTO
//                        )
//                    if (response.isSuccessful) {
//                        addingStatus.value = AddingStatus.Success
//                        BridgeObject.addressListChange.value =
//                            !BridgeObject.addressListChange.value!!
//                    } else {
//                        if (response.code() == 401) {
//                            val refreshToken = authRepository.getCurrentRefreshTokenFromRoom()
//                            if (refreshToken != null) {
//                                getNewAccessTokenFromRefreshToken(refreshToken)
//                                onUpdateAddress(address)
//                            }
//                        }
//                    }
//                }
//            } catch (e: Exception) {
//                addingStatus.value = AddingStatus.Fail
//                Log.e("Exception", e.toString())
//            }
//        }
//    }



//    fun loadProvince() {
//        listProvince.value = mutableListOf()
//        status.value = LoadingStatus.Loading
//        viewModelScope.launch {
//            val response = provinceRepository.provinceService.getAllProvince()
//            try {
//                listProvince.value = response.body()!!.stream().map {
//                    convertFromProvinceDTOToProvince(it)
//                }.collect(Collectors.toList())
//                status.value = LoadingStatus.Success
//            } catch (e: Exception) {
//                Log.e("Exception", e.message!!)
//                status.value = LoadingStatus.Fail
//            }
//        }
//    }





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

//    fun deleteDeliveryAddress(id: Long) {
//        addingStatus.value = AddingStatus.Loading
//        viewModelScope.launch {
//            try {
//                val token = authRepository.getCurrentAccessTokenFromRoom()
//                if (token != null) {
//                    val response =
//                        deliveryAddressRepository.deliveryAddressService.deleteDeliveryAddressService(
//                            token,
//                            id
//                        )
//                    if (response.isSuccessful) {
//                        addingStatus.value = AddingStatus.Success
//                        BridgeObject.addressListChange.value =
//                            !BridgeObject.addressListChange.value!!
//                        loadUserAddress()
//                    } else {
//                        if (response.code() == 401) {
//                            val refreshToken = authRepository.getCurrentRefreshTokenFromRoom()
//                            if (refreshToken != null) {
//                                getNewAccessTokenFromRefreshToken(refreshToken)
//                                deleteDeliveryAddress(id)
//                            }
//                        }
//                    }
//                }
//            } catch (e: Exception) {
//                addingStatus.value = AddingStatus.Fail
//                Log.e("Exception", e.toString())
//            }
//        }
//    }

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