package com.example.aposs_buyer.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.*
import com.example.aposs_buyer.model.dto.DistrictDTO
import com.example.aposs_buyer.model.dto.ProvinceDTO
import com.example.aposs_buyer.model.dto.WardDTO
import com.example.aposs_buyer.responsitory.DeliveryAddressRepository
import com.example.aposs_buyer.responsitory.DistrictRepository
import com.example.aposs_buyer.responsitory.ProvinceRepository
import com.example.aposs_buyer.responsitory.WardRepository
import com.example.aposs_buyer.responsitory.database.AccountDatabase
import com.example.aposs_buyer.utils.LoadingStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import java.util.stream.Collectors
import javax.inject.Inject

@HiltViewModel
class AddressDialogViewModel @Inject constructor(private val provinceRepository: ProvinceRepository,
                                                 private val districtRepository: DistrictRepository,
                                                 private val wardRepository: WardRepository,
                                                 private val deliveryAddressRepository: DeliveryAddressRepository,
                                                 @ApplicationContext private val context: Context): ViewModel() {

    val address = MutableLiveData<Address> ()
    var nameErrorMessage: String? = ""
    var cellNumberErrorMessage: String? = ""
    var name: MutableLiveData<String> = MutableLiveData()
    var cellNumber: MutableLiveData<String> = MutableLiveData()

    val listProvince = MutableLiveData<MutableList<Province>>()

    val listDistrict = MutableLiveData<MutableList<District>>()

    private val _listWard = MutableLiveData<MutableList<Ward>>()
    val listWard : LiveData<MutableList<Ward>> get() = _listWard

    private val status = MutableLiveData<LoadingStatus>()

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        loadProvince()
    }

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

    fun  addNewDeliveryAddress(address: Address)
    {

    }

    fun loadProvince()
    {
        status.value = LoadingStatus.Loading
        coroutineScope.launch {
            var response = provinceRepository.provinceService.getAllProvince()
            try {
                listProvince.value = response.body()!!.stream().map { it ->
                    convertFromProvinceDTOToProvince(it)
                }.collect(Collectors.toList())
                status.value = LoadingStatus.Success
            }
            catch (e: Exception)
            {
                Log.e("Exception", e.message!!)
                status.value = LoadingStatus.Fail
            }
        }
    }

    fun loadDistrictByProvince(choseProvince: Long)
    {
        status.value = LoadingStatus.Loading
        coroutineScope.launch {
            var response = districtRepository.districtService.getAllDistrictById(choseProvince)
            try {
                listDistrict.value = response.body()!!.stream().map { it ->
                    convertFromDistrictDTOToDistrict(it)
                }.collect(Collectors.toList())
                status.value = LoadingStatus.Success
            }
            catch (e: Exception)
            {
                Log.e("Exception", e.toString())
                status.value = LoadingStatus.Fail
            }
        }
    }

    fun loadWardByDistrict(choseDistrict: Long)
    {
        status.value = LoadingStatus.Loading
        coroutineScope.launch {
            var response = wardRepository.wardService.getAllWardById(choseDistrict)
            try {
                _listWard.value = response.body()!!.stream().map { it ->
                    convertFromWardDTOToWard(it)
                }.collect(Collectors.toList())
                status.value = LoadingStatus.Success
            }
            catch (e: Exception)
            {
                Log.e("Exception", e.message!!)
                status.value = LoadingStatus.Fail
            }
        }
    }

    private fun convertFromProvinceDTOToProvince(provinceDTO: ProvinceDTO): Province
    {
        return Province(provinceDTO.id, provinceDTO.name)
    }

    private fun convertFromDistrictDTOToDistrict(districtDTO: DistrictDTO): District
    {
        return District(districtDTO.id, districtDTO.name, districtDTO.province)
    }

    private fun convertFromWardDTOToWard(wardDTO: WardDTO): Ward
    {
        return Ward(wardDTO.id, wardDTO.name, wardDTO.district)
    }
}