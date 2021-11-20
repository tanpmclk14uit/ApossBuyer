package com.example.aposs_buyer.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.CartItem
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.dto.CartDTO
import com.example.aposs_buyer.model.dto.TokenDTO
import com.example.aposs_buyer.responsitory.AuthRepository
import com.example.aposs_buyer.responsitory.CartRepository
import com.example.aposs_buyer.responsitory.database.AccountDatabase
import com.example.aposs_buyer.utils.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.util.stream.Collectors
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val authRepository: AuthRepository,
    @ApplicationContext val context: Context
) : ViewModel() {

    private val _lstCartItem = MutableLiveData<ArrayList<CartItem>>()
    val lstCartItem: LiveData<ArrayList<CartItem>> get() = _lstCartItem

    private val _choseList = MutableLiveData<ArrayList<CartItem>>()
    val choseList: LiveData<ArrayList<CartItem>> get() = _choseList

    val total = MutableLiveData<String>()
    val size = MutableLiveData<Int>()
    val choseSize = MutableLiveData<Int>()

    var tokenDTO: TokenDTO? = null
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    var loadingStatus = MutableLiveData<LoadingState>()

    init {
        _lstCartItem.value = ArrayList()
    }

    private fun calculateTotal(): String {
        return if (_lstCartItem.value != null) {
            var sum = 0
            for (i in 0 until _lstCartItem.value!!.size) {
                if (_lstCartItem.value!![i].isChoose) {
                    sum += _lstCartItem.value!![i].price * _lstCartItem.value!![i].amount
                }
            }
            val formatter = DecimalFormat("#,###")
            val formattedNumber: String = formatter.format(sum)
            "$formattedNumber VNĐ"
        } else "0 VNĐ"
    }

    fun reCalculateTotal() {
        total.value = calculateTotal()
        size.value = _lstCartItem.value!!.size
    }

    fun setChoseSize() {
        choseSize.value = _choseList.value!!.size
    }

    fun removeItem(position: Int) {
        _lstCartItem.value!!.removeAt(position)
    }

    fun setNewChose() {
        _choseList.value = getChose()
    }

    private fun getChose(): ArrayList<CartItem> {
        val choseList: ArrayList<CartItem> = arrayListOf()
        for (i in 0 until _lstCartItem.value!!.size) {
            if (_lstCartItem.value!![i].isChoose)
                choseList.add(_lstCartItem.value!![i])
        }
        return choseList
    }

    private fun toCartItem(cartDTO: CartDTO): CartItem {
        val image = Image(cartDTO.imageUrl)
        return CartItem(
            id = cartDTO.id,
            image = image,
            name = cartDTO.name,
            price = cartDTO.price,
            amount = cartDTO.quantity,
            property = cartDTO.property,
            isChoose = cartDTO.select
        )
    }

    private var cartItemsDTO: List<CartDTO> = ArrayList()

    private fun updateCartItem(cartDTO: CartDTO) {
        if (tokenDTO != null) {
            coroutineScope.launch {
                val updateResponse =
                    cartRepository.updateCart(
                        tokenDTO!!.getFullAccessToken(),
                        cartDTO
                    )
                if (updateResponse.code() == 200) {
                    Log.d("cart", updateResponse.body().toString())
                    return@launch
                } else {
                    if (updateResponse.code() == 401) {
                        Log.d("cart", "Expire access token")
                        val accessTokenResponse =
                            authRepository.getAccessToken(tokenDTO!!.refreshToken)
                        if (accessTokenResponse.code() == 200) {
                            tokenDTO!!.accessToken = accessTokenResponse.body()!!
                            AccountDatabase.getInstance(context).accountDao.updateAccessToken(tokenDTO!!.accessToken)
                            updateCartItem(cartDTO)
                        }
                    }
                }
            }
        }
    }

    fun updateCart() {
        for (cartItem: CartItem in _lstCartItem.value!!) {
            for (cartItemDTO: CartDTO in cartItemsDTO) {
                if (cartItemDTO.id == cartItem.id && (cartItem.isChoose != cartItemDTO.select || cartItem.amount != cartItemDTO.quantity)) {
                    cartItemDTO.select = cartItem.isChoose
                    cartItemDTO.quantity = cartItem.amount
                    updateCartItem(cartItemDTO)
                }
            }
        }
    }

    fun loadCartList() {
        coroutineScope.launch {
            if (tokenDTO != null) {
                loadingStatus.value = LoadingState.Loading
                val allCartItemsResponse =
                    cartRepository.getAllCart(tokenDTO!!.getFullAccessToken())
                when (allCartItemsResponse.code()) {
                    200 -> {
                        cartItemsDTO = allCartItemsResponse.body()!!
                        _lstCartItem.value = cartItemsDTO.stream().map {
                            toCartItem(it)
                        }.collect(Collectors.toList()).toCollection(ArrayList())
                        loadingStatus.value = LoadingState.Success
                        total.value = calculateTotal()
                        size.value = _lstCartItem.value!!.size
                        _choseList.value = getChose()
                        choseSize.value = _choseList.value!!.size
                        return@launch
                    }
                    401 -> {
                        Log.d("cart", "Expire access token")
                        val accessTokenResponse =
                            authRepository.getAccessToken(tokenDTO!!.refreshToken)
                        if (accessTokenResponse.code() == 200) {
                            tokenDTO!!.accessToken = accessTokenResponse.body()!!
                            AccountDatabase.getInstance(context).accountDao.updateAccessToken(tokenDTO!!.accessToken)
                            loadCartList()
                        }
                    }
                    else -> {
                        loadingStatus.value = LoadingState.Fail
                    }
                }
            }
        }
    }
}