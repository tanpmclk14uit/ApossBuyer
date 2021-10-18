package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.Address
import com.example.aposs_buyer.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class AddressDialogViewModel @Inject constructor(): ViewModel() {

    val address = MutableLiveData<Address> ()
    var nameErrorMessage: String? = ""
    var cellNumberErrorMessage: String? = ""
    var name: MutableLiveData<String> = MutableLiveData()
    var cellNumber: MutableLiveData<String> = MutableLiveData()

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

    private fun isNameContainNumberOrSpecialCharacter(name: String): Boolean {
        val hasNumber: Boolean = Pattern.compile(
            "[0-9]"
        ).matcher(name).find()
        val hasSpecialCharacter: Boolean = Pattern.compile(
            "[!@#$%&.,\"':;?*()_+=|<>?{}\\[\\]~-]"
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
        val regex = "(84|0[3|5|7|8|9])+([0-9]{8})\\b".toRegex()
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
}