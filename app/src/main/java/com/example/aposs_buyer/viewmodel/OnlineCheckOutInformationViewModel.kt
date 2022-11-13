package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aposs_buyer.model.dto.BankingInformationDTO
import com.example.aposs_buyer.responsitory.AuthRepository
import com.example.aposs_buyer.responsitory.BankingInformationRepository
import com.example.aposs_buyer.responsitory.OrderRepository
import com.example.aposs_buyer.utils.LoadingStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.net.SocketTimeoutException
import java.util.stream.Collectors
import javax.inject.Inject



@HiltViewModel
class OnlineCheckOutInformationViewModel  @Inject constructor(
    private val bankingInformationRepository: BankingInformationRepository,
    private val orderRepository: OrderRepository,
    private val authRepository: AuthRepository
): ViewModel() {
    val bankingInformationDTO: MutableLiveData<BankingInformationDTO> = MutableLiveData()
    val status = MutableLiveData<LoadingStatus>()
    val updateStatus = MutableLiveData<LoadingStatus>()

    init {
        loadBankingInformation()
    }

    fun updatePaymentStatus(orderId: Long){
        updateStatus.value = LoadingStatus.Loading
        runBlocking {
            viewModelScope.launch {
                val accessToken = authRepository.getCurrentAccessTokenFromRoom()
                if (!accessToken.isNullOrBlank()) {
                    try {
                        val response =
                            orderRepository.updatePaymentStatus(orderId , accessToken)
                        if (response.isSuccessful) {
                            updateStatus.value = LoadingStatus.Success
                        } else {
                            if (response.code() == 401) {
                                if (authRepository.loadNewAccessTokenSuccess()) {
                                    updatePaymentStatus(orderId)
                                } else {
                                    updateStatus.value = LoadingStatus.Fail
                                }
                            } else {
                                updateStatus.value = LoadingStatus.Fail
                            }
                        }
                    }catch (e: Exception){
                        if (e is SocketTimeoutException) {
                            updatePaymentStatus(orderId)
                        } else {
                            updateStatus.value = LoadingStatus.Fail
                            Log.e("Exception", e.toString())
                        }
                    }
                } else {
                    updateStatus.value = LoadingStatus.Fail
                }
            }
        }
    }

    private fun loadBankingInformation() {
        status.postValue(LoadingStatus.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                    val response =
                        bankingInformationRepository.getBankingInformationData()
                    if (response.isSuccessful) {
                        bankingInformationDTO.postValue(response.body())
                        status.postValue(LoadingStatus.Success)
                    } else {
                        status.postValue(LoadingStatus.Fail)
                    }
            } catch (e: Exception) {
                if (e is SocketTimeoutException) {
                    loadBankingInformation()
                } else {
                    status.postValue(LoadingStatus.Fail)
                    Log.e("Exception", e.toString())
                }
            }
        }
    }
}