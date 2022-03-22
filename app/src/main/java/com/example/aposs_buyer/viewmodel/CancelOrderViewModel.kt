package com.example.aposs_buyer.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.dto.TokenDTO
import com.example.aposs_buyer.model.entity.Account
import com.example.aposs_buyer.responsitory.AuthRepository
import com.example.aposs_buyer.responsitory.OrderRepository
import com.example.aposs_buyer.responsitory.database.AccountDatabase
import com.example.aposs_buyer.utils.LoadingStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CancelOrderViewModel @Inject constructor(
    val orderRepository: OrderRepository,
    val authRepository: AuthRepository,
    @ApplicationContext val context: Context
): ViewModel(){
    private var orderId: Long = -1
    var cancelReason: MutableLiveData<String> = MutableLiveData()

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _loadStatus = MutableLiveData<LoadingStatus>()
    val loadStatus: LiveData<LoadingStatus> get() = _loadStatus

    init {
        cancelReason.value = ""
    }

    fun setOrderId(id: Long){
        orderId =id
    }
//    fun cancelOrder(){
//        _loadStatus.value = LoadingStatus.Loading
//        val account: Account = AccountDatabase.getInstance(context).accountDao.getAccount()!!
//        var tokenDTO = TokenDTO(accessToken = account.accessToken, tokenType = account.tokenType, refreshToken = account.refreshToken)
//        coroutineScope.launch {
////            val response = orderRepository.orderService.cancelOrder(orderId, cancelReason.value!!, tokenDTO.getFullAccessToken())
//            if (response.code() == 200)
//            {
//                _loadStatus.value = LoadingStatus.Success
//            }
//            else if (response.code() == 401){
//                val accessTokenResponse =
//                    authRepository.getAccessTokenFromRefreshToken(tokenDTO.refreshToken)
//                if (accessTokenResponse.code() == 200) {
//                    tokenDTO.accessToken = accessTokenResponse.body()!!
//                    AccountDatabase.getInstance(context).accountDao.updateAccessToken(
//                        tokenDTO.accessToken
//                    )
//                    cancelOrder()
//                }
//            }
//            else {
//                _loadStatus.value = LoadingStatus.Fail
//            }
//        }
//    }
}
