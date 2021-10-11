package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.Address
import com.example.aposs_buyer.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddressDialogViewModel @Inject constructor(): ViewModel() {

    val address = MutableLiveData<Address> ()

    fun updateAddress(newAddress: Address)
    {
        address.value = newAddress
    }

    fun isChangeName(newName: String): Boolean
    {
        return address.value!!.name != newName
    }

    fun isChangeGender(newGender: String): Boolean
    {
        val newGenderBool: Boolean = newGender == "Male"
        return address.value!!.gender == newGenderBool
    }

    fun isChangeCity(newCity: String): Boolean
    {
        return address.value!!.city == newCity
    }

    fun isChangeDistrict(newDistrict: String): Boolean
    {
        return address.value!!.district == newDistrict
    }

    fun isChangeWard(newWard: String): Boolean
    {
        return address.value!!.ward == newWard
    }

    fun isChangeAddressLane(newAddressLane: String): Boolean
    {
        return address.value!!.addressLane == newAddressLane
    }

    fun isChangePhone (newPhone: String): Boolean
    {
        return address.value!!.phoneNumber == newPhone
    }

    fun onAddNewAddress(address: Address) {
        
    }

    fun onUpdateAddress(address: Address) {

    }
}