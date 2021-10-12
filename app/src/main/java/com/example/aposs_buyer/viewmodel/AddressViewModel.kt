package com.example.aposs_buyer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.Address
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(): ViewModel() {

    private val _listAddress = MutableLiveData<MutableList<Address>>()
    val listAddress : LiveData<MutableList<Address>> get() = _listAddress

    init {
        _listAddress.value = loadUserAddress()
    }

    fun loadUserAddress(): MutableList<Address>
    {
        val sampleList: MutableList<Address> = mutableListOf()
        sampleList.add(Address(1, "khang", true, "0933171801", "Long An", "Vĩnh Hưng", "thị trấn Vĩnh Hưng", "Đốc binh kiều", true ))
        sampleList.add(Address(2, "Bùi Dương Duy Khang", true, "0933171802", "TP.Hồ Chí Minh", "Thủ Đức", "Linh trung", "KTX Khu B DHQG", false))
        sampleList.add(Address(3, "Bùi Khang", true, "0933171803", "TP.Hồ Chí Minh", "Thủ Đức", "Linh trung", "KTX Khu B DHQG", false))
        return sampleList
    }

    fun onChangeDefault(position: Int) {
        for(i in 0 until _listAddress.value!!.size)
        {
            if (_listAddress.value!![i].isDefault)
                listAddress.value!![i].isDefault = false
        }
        _listAddress.value!![position].isDefault = true
    }

    fun getCurrentDefaultAddress(): Address {
        for (i in 0 until _listAddress.value!!.size)
        {
            if (_listAddress.value!![i].isDefault)
                return _listAddress.value!![i]
        }
        return Address(0, "None", true, "none", "none", "none", "none", "none", false)
    }

    fun getCreateAddress():Address{
        return Address(0, "", true, "", "", "", "", "", false)
    }

    fun deleteDefaultAddress() {
        // delete default and choose newest to default
    }

}