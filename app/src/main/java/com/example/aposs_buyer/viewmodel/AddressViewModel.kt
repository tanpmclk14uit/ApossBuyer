package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aposs_buyer.model.Address
import com.example.aposs_buyer.model.dto.DeliveryAddressDTO
import com.example.aposs_buyer.model.entity.Account
import com.example.aposs_buyer.responsitory.AuthRepository
import com.example.aposs_buyer.responsitory.DeliveryAddressRepository
import com.example.aposs_buyer.utils.DeliveryAddressStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
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

    init {
        loadUserAddress()
    }

    fun loadUserAddress() {
        listAddress.value = mutableListOf()
        status.value = DeliveryAddressStatus.Loading
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
                        listAddress.value = listDeliveryAddressDTO!!.stream().map { it ->
                            convertDeliveryAddressDTOToAddress(it)
                        }.collect(Collectors.toList())
                        status.value = DeliveryAddressStatus.Success
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
                status.value = DeliveryAddressStatus.Fail
                Log.e("Exception", e.toString())
            }
        }
    }

    private suspend fun getNewAccessTokenFromRefreshToken(refreshToken: String) {
        viewModelScope.launch {
            val newAccessTokenResponse = authRepository.getAccessTokenFromRefreshToken(refreshToken)
            if(newAccessTokenResponse.isSuccessful){
                val newAccessToken =
                    authRepository.getAccessTokenFromRefreshToken(refreshToken).body()!!
                authRepository.updateAccessToken(newAccessToken)
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
        );
    }

    fun getAddress(id: Long): Address {
        for (i in 0 until listAddress.value!!.size) {
            if (listAddress.value!![i].id == id)
                return listAddress.value!![i]
        }
        return Address(0, "None", true, "none", "none", "none", "none", "none", false)
    }

    fun getCreateAddress(): Address {
        return Address(0, "", true, "", "", "", "", "", false)
    }

    fun getCurrentDefaultAddress(): Address {
        for (i in 0 until listAddress.value!!.size) {
            if (listAddress.value!![i].isDefault)
                return listAddress.value!![i]
        }
        return Address(0, "None", true, "none", "none", "none", "none", "none", false)
    }

}