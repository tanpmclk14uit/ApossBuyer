package com.example.aposs_buyer.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.Address
import com.example.aposs_buyer.model.District
import com.example.aposs_buyer.model.Province
import com.example.aposs_buyer.model.Ward
import com.example.aposs_buyer.model.dto.DeliveryAddressDTO
import com.example.aposs_buyer.model.dto.TokenDTO
import com.example.aposs_buyer.model.entity.Account
import com.example.aposs_buyer.responsitory.AuthRepository
import com.example.aposs_buyer.responsitory.DeliveryAddressRepository
import com.example.aposs_buyer.responsitory.database.AccountDatabase
import com.example.aposs_buyer.utils.DeliveryAddressStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.Request
import java.util.stream.Collectors
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(private  val deliveryAddressRepository: DeliveryAddressRepository,
                                           @ApplicationContext private  val appContext: Context,
                                            private val authRepository: AuthRepository): ViewModel() {

    private val _listAddress = MutableLiveData<MutableList<Address>>()
    val listAddress : LiveData<MutableList<Address>> get() = _listAddress

    private val status  = MutableLiveData<DeliveryAddressStatus>()
    private val statusDelete  = MutableLiveData<DeliveryAddressStatus>()

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)



    init {
        _listAddress.value = mutableListOf()
        loadUserAddress()
    }

     fun loadUserAddress()
    {
        status.value = DeliveryAddressStatus.Loading
        coroutineScope.launch {
            var account = AccountDatabase.getInstance(appContext).accountDao.getAccount()[0]
            var token = account.tokenType + " " + account.accessToken
                var response = deliveryAddressRepository.deliveryAddressService.getAllDeliveryAddressService(token)
            if (response.code() == 401)
            {
                getNewAccessToken(account)
                account = AccountDatabase.getInstance(appContext).accountDao.getAccount()[0]
                token = account.tokenType + " " + account.accessToken
                response = deliveryAddressRepository.deliveryAddressService.getAllDeliveryAddressService(token)
            }
            val listDeliveryAddressDTO = response.body()
            try {
                _listAddress.value = listDeliveryAddressDTO!!.stream().map{
                    it -> convertDeliveryAddressDTOToAddress(it)
                }.collect(Collectors.toList())
                status.value = DeliveryAddressStatus.Success
            }
            catch (e:Exception)
            {
                status.value = DeliveryAddressStatus.Fail
                Log.e("Exception", e.toString())
            }
        }
    }

    private suspend fun getNewAccessToken(account: Account)
    {
        val newAccessToken = authRepository.getNewAccessToken(account.refreshToken).body()!!
        val accountNew: Account =
            Account(
                account.userName,
                account.password,
                newAccessToken,
                account.tokenType,
                account.refreshToken
            )
        AccountDatabase.getInstance(appContext).accountDao.deleteAccount(account)
        AccountDatabase.getInstance(appContext).accountDao.insertAccount(accountNew)
    }

    private fun convertDeliveryAddressDTOToAddress(deliveryAddressDTO: DeliveryAddressDTO): Address
    {
        return Address(
            id =  deliveryAddressDTO.id,
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

    fun onChangeDefault(position: Int) {
        for(i in 0 until _listAddress.value!!.size)
        {
            if (_listAddress.value!![i].isDefault)
                listAddress.value!![i].isDefault = false
        }
        _listAddress.value!![position].isDefault = true
    }

    fun getCurrentDefaultAddress(id: Long): Address {
        for (i in 0 until _listAddress.value!!.size)
        {
            if (_listAddress.value!![i].id == id)
                return _listAddress.value!![i]
        }
        return Address(0, "None", true, "none", "none", "none", "none", "none", false)
    }

    fun getCreateAddress():Address{
        return Address(0, "", true, "", "", "", "", "", false)
    }



}