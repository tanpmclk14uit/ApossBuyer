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
import com.example.aposs_buyer.responsitory.AuthRepository
import com.example.aposs_buyer.responsitory.DeliveryAddressRepository
import com.example.aposs_buyer.utils.DeliveryAddressStatus
import com.example.aposs_buyer.utils.StringValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.stream.Collectors
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val deliveryAddressRepository: DeliveryAddressRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    val listAddress = MutableLiveData<MutableList<Address>>()
    val status = MutableLiveData<DeliveryAddressStatus>()
    var currentAddress = MutableLiveData<Address>()
    var validInformation = MutableLiveData<Boolean>()
    val listProvince = MutableLiveData<MutableList<Province>>()
    val listDistrict = MutableLiveData<MutableList<District>>()
    val listWard = MutableLiveData<MutableList<Ward>>()

    init {
        loadUserAddress()
        //set up default value
        validInformation.value = false
        listProvince.value = mutableListOf()
        listDistrict.value = mutableListOf()
        listWard.value = mutableListOf()
        //
        loadProvince()
    }

    fun getIdOfProvince(provinceName: String): Long {
        for (province in listProvince.value!!) {
            if (province.name == provinceName) {
                return province.id
            }
        }
        return -1
    }

    fun getIdOfDistrict(districtName: String): Long {
        for (district in listDistrict.value!!) {
            if (district.name == districtName) {
                return district.id
            }
        }
        return -1
    }

    private fun loadProvince() {
        viewModelScope.launch {
            val response = deliveryAddressRepository.getAllProvince()
            try {
                listProvince.value = response.body()!!.stream().map {
                    Province(it.id, it.name)
                }.collect(Collectors.toList())
            } catch (e: Exception) {
                Log.e("Address", e.message!!)
            }
        }
    }

    fun loadDistrictsByProvinceId(provinceId: Long) {
        listDistrict.value!!.clear()
        viewModelScope.launch {
            val response = deliveryAddressRepository.getAllDistrictOfProvinceById(provinceId)
            try {
                listDistrict.value = response.body()!!.stream().map {
                    District(it.id, it.name, it.province)
                }.collect(Collectors.toList())
            } catch (e: Exception) {
                Log.e("Exception", e.toString())
            }
        }
    }

    fun loadWardsByDistrictId(districtId: Long) {
        listWard.value!!.clear()
        viewModelScope.launch {
            val response = deliveryAddressRepository.getAllWardsOfDistrictById(districtId)
            try {
                listWard.value = response.body()!!.stream().map {
                    Ward(it.id, it.name, it.district)
                }.collect(Collectors.toList())
            } catch (e: Exception) {
                Log.e("Exception", e.message!!)
            }
        }
    }


    private fun loadUserAddress() {
        listAddress.value = mutableListOf()
        status.value = DeliveryAddressStatus.Loading
        viewModelScope.launch {
            try {
                val token = authRepository.getCurrentAccessTokenFromRoom()
                if (token != null) {
                    val response =
                        deliveryAddressRepository.getAllUserAddressByAccessToken(
                            token
                        )
                    if (response.isSuccessful) {
                        val listDeliveryAddressDTO = response.body()
                        listAddress.value = listDeliveryAddressDTO!!.stream().map {
                            convertDeliveryAddressDTOToAddress(it)
                        }.collect(Collectors.toList())
                        status.value = DeliveryAddressStatus.Success
                    } else {
                        if (response.code() == 401) {
                            if (authRepository.loadNewAccessTokenSuccess()) {
                                loadUserAddress()
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                status.value = DeliveryAddressStatus.Fail
                Log.e("Exception", e.toString())
            }
        }
    }

    fun trackingValidInformation() {
        val currentAddress = currentAddress.value!!
        validInformation.value = (StringValidator.getNameError(currentAddress.name) == null
                && StringValidator.getPhoneNumberError(currentAddress.phoneNumber) == null
                && currentAddress.city.isNotBlank()
                && currentAddress.district.isNotBlank()
                && currentAddress.ward.isNotBlank()
                && currentAddress.addressLane.isNotBlank())
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

}