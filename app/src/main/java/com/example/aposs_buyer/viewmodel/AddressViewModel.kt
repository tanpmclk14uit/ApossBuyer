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
import com.example.aposs_buyer.responsitory.AuthRepository
import com.example.aposs_buyer.responsitory.DeliveryAddressRepository
import com.example.aposs_buyer.utils.LoadingStatus
import com.example.aposs_buyer.utils.StringValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.util.stream.Collectors
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val deliveryAddressRepository: DeliveryAddressRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    val listAddress = MutableLiveData<MutableList<Address>>()
    val status = MutableLiveData<LoadingStatus>()
    var newAddress = MutableLiveData<Address>()
    lateinit var currentAddress: Address
    var validInformation = MutableLiveData<Boolean>()
    val listProvince = MutableLiveData<MutableList<Province>>()
    val listDistrict = MutableLiveData<MutableList<District>>()
    val listWard = MutableLiveData<MutableList<Ward>>()
    val loadingStatus = MutableLiveData<LoadingStatus>()
    val checkChange = MutableLiveData<Boolean>()

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

    fun addNewAddress() {
        val deliveryAddressDTO = convertAddressToDeliveryAddressDTO(newAddress.value!!)
        loadingStatus.postValue(LoadingStatus.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val token = authRepository.getCurrentAccessTokenFromRoom()
                if (token != null) {
                    val response =
                        deliveryAddressRepository.addNewUserAddress(
                            token,
                            deliveryAddressDTO
                        )
                    if (response.isSuccessful) {
                        loadingStatus.postValue(LoadingStatus.Success)
                        loadUserAddress()
                    } else {
                        if (response.code() == 401) {
                            if (authRepository.loadNewAccessTokenSuccess()) {
                                addNewAddress()
                            } else {
                                loadingStatus.postValue(LoadingStatus.Fail)
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                loadingStatus.postValue(LoadingStatus.Fail)
                Log.e("Exception", e.toString())
            }
        }
    }

    fun onUpdateAddress() {
        val deliveryAddressDTO = convertAddressToDeliveryAddressDTO(newAddress.value!!)
        loadingStatus.postValue(LoadingStatus.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val token = authRepository.getCurrentAccessTokenFromRoom()
                if (token != null) {
                    val response =
                        deliveryAddressRepository.updateUserAddress(
                            token,
                            deliveryAddressDTO
                        )
                    if (response.isSuccessful) {
                        loadingStatus.postValue(LoadingStatus.Success)
                        loadUserAddress()
                    } else {
                        if (response.code() == 401 || response.code() == 502) {
                            if (authRepository.loadNewAccessTokenSuccess()) {
                                onUpdateAddress()
                            } else {
                                loadingStatus.postValue(LoadingStatus.Fail)
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                loadingStatus.postValue(LoadingStatus.Fail)
                Log.e("Exception", e.toString())
            }
        }
    }

    fun deleteDeliveryAddress() {
        loadingStatus.postValue(LoadingStatus.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val token = authRepository.getCurrentAccessTokenFromRoom()
                if (token != null) {
                    val response =
                        deliveryAddressRepository.deleteUserAddressById(
                            token,
                            currentAddress.id
                        )
                    if (response.isSuccessful) {
                        loadingStatus.postValue(LoadingStatus.Success)
                        loadUserAddress()
                    } else {
                        if (response.code() == 401 || response.code() == 502) {
                            if (authRepository.loadNewAccessTokenSuccess()) {
                                deleteDeliveryAddress()
                            } else {
                                loadingStatus.postValue(LoadingStatus.Fail)
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                loadingStatus.postValue(LoadingStatus.Fail)
                Log.e("Exception", e.toString())
            }
        }
    }

    fun getProvinceFromName(provinceName: String): Province {
        for (province in listProvince.value!!) {
            if (province.name == provinceName) {
                return province
            }
        }
        return Province()
    }

    fun getDistrictFromName(districtName: String): District {
        for (district in listDistrict.value!!) {
            if (district.name == districtName) {
                return district
            }
        }
        return District()
    }

    fun getWardFromName(wardName: String): Ward {
        for (ward in listWard.value!!) {
            if (ward.name == wardName) {
                return ward
            }
        }
        return Ward()
    }

    private fun loadProvince() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = deliveryAddressRepository.getAllProvince()
            try {
                listProvince.postValue(response.body()?.stream()?.map {
                    Province(it.id, it.name)
                }?.collect(Collectors.toList()) ?: mutableListOf())
            } catch (e: Exception) {
                Log.e("Address", e.message!!)
            }
        }
    }

    fun loadDistrictsByProvinceId(provinceId: Long) {
        listDistrict.value!!.clear()
        viewModelScope.launch(Dispatchers.IO) {
            val response = deliveryAddressRepository.getAllDistrictOfProvinceById(provinceId)
            try {
                listDistrict.postValue(response.body()?.stream()?.map {
                    District(it.id, it.name, it.province)
                }?.collect(Collectors.toList()) ?: mutableListOf())
            } catch (e: Exception) {
                Log.e("Exception", e.toString())
            }
        }
    }

    fun loadWardsByDistrictId(districtId: Long) {
        listWard.value!!.clear()
        viewModelScope.launch(Dispatchers.IO) {
            val response = deliveryAddressRepository.getAllWardsOfDistrictById(districtId)
            try {
                listWard.postValue(response.body()?.stream()?.map {
                    Ward(it.id, it.name, it.district)
                }?.collect(Collectors.toList()) ?: mutableListOf())
            } catch (e: Exception) {
                Log.e("Exception", e.message!!)
            }
        }
    }


    fun loadUserAddress() {
        listAddress.postValue(mutableListOf())
        status.postValue(LoadingStatus.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val token = authRepository.getCurrentAccessTokenFromRoom()
                if (token != null) {
                    val response =
                        deliveryAddressRepository.getAllUserAddressByAccessToken(
                            token
                        )
                    if (response.isSuccessful) {
                        val listDeliveryAddressDTO = response.body()
                        listAddress.postValue(listDeliveryAddressDTO?.stream()?.map {
                            convertDeliveryAddressDTOToAddress(it)
                        }?.collect(Collectors.toList()) ?: mutableListOf())
                        status.postValue(LoadingStatus.Success)
                    } else {
                        if (response.code() == 401) {
                            if (authRepository.loadNewAccessTokenSuccess()) {
                                loadUserAddress()
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                if (e is SocketTimeoutException) {
                    loadUserAddress()
                } else {
                    status.postValue(LoadingStatus.Fail)
                    Log.e("Exception", e.toString())
                }
            }
        }
    }


    fun trackingValidInformation() {
        val newAddress = newAddress.value!!
        validInformation.value = (StringValidator.getNameError(newAddress.name) == null
                && StringValidator.getPhoneNumberError(newAddress.phoneNumber) == null
                && newAddress.city.name.isNotBlank()
                && newAddress.district.name.isNotBlank()
                && newAddress.ward.name.isNotBlank()
                && newAddress.addressLane.isNotBlank())
                && !newAddress.equal(currentAddress)
    }

    private fun convertDeliveryAddressDTOToAddress(deliveryAddressDTO: DeliveryAddressDTO): Address {
        return Address(
            id = deliveryAddressDTO.id,
            name = deliveryAddressDTO.name,
            gender = deliveryAddressDTO.gender,
            phoneNumber = deliveryAddressDTO.phoneNumber,
            city = Province(deliveryAddressDTO.province.id, deliveryAddressDTO.province.name),
            district = District(
                deliveryAddressDTO.district.id,
                deliveryAddressDTO.district.name,
                deliveryAddressDTO.district.province
            ),
            ward = Ward(
                deliveryAddressDTO.ward.id,
                deliveryAddressDTO.ward.name,
                deliveryAddressDTO.ward.district
            ),
            isDefaultAddress = deliveryAddressDTO.isDefault,
            addressLane = deliveryAddressDTO.addressLane
        )
    }

    private fun convertAddressToDeliveryAddressDTO(address: Address): DeliveryAddressDTO {
        return DeliveryAddressDTO(
            id = address.id,
            name = address.name,
            gender = address.gender,
            addressLane = address.addressLane,
            phoneNumber = address.phoneNumber,
            isDefault = address.isDefaultAddress,
            ward = WardDTO(address.ward.id, address.ward.name, address.ward.district),
            district = DistrictDTO(
                address.district.id,
                address.district.name,
                address.district.province
            ),
            province = ProvinceDTO(address.city.id, address.city.name)
        )
    }
}