package com.example.aposs_buyer.viewmodel

import android.util.Log
import android.util.LogPrinter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.CartItem
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.dto.CartDTO
import com.example.aposs_buyer.model.dto.TokenDTO
import com.example.aposs_buyer.responsitory.AuthRepository
import com.example.aposs_buyer.responsitory.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.util.stream.Collectors
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val authRepository: AuthRepository
): ViewModel(){

    private val _lstCartItem = MutableLiveData<ArrayList<CartItem>>()
    val lstCartItem: LiveData<ArrayList<CartItem>> get() = _lstCartItem

    private val _choseList= MutableLiveData<ArrayList<CartItem>>()
    val choseList: LiveData<ArrayList<CartItem>> get() = _choseList

    val total = MutableLiveData<String>()
    val size = MutableLiveData<Int>()
    val choseSize = MutableLiveData<Int>()

    var tokenDTO: TokenDTO? = null
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    init {
        _lstCartItem.value = ArrayList()
        total.value = calculateTotal()
         size.value = _lstCartItem.value!!.size
        _choseList.value = getChose()
        choseSize.value = _choseList.value!!.size
    }

    private fun calculateTotal(): String
    {
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

    fun reCalculateTotal()
    {
        total.value = calculateTotal()
        size.value = _lstCartItem.value!!.size
    }

    fun setChoseSize()
    {
        choseSize.value = _choseList.value!!.size
    }

    fun removeItem(position: Int)
    {
        _lstCartItem.value!!.removeAt(position)
    }

    fun setNewChose()
    {
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
            id =cartDTO.id,
            image = image,
            name = cartDTO.name,
            price = cartDTO.price,
            amount = cartDTO.quantity,
            property = cartDTO.property,
            isChoose = true
        )
    }

    fun loadCartList()
    {
        coroutineScope.launch {
            if(tokenDTO != null){
                val allCartItemsResponse = cartRepository.getAllCart(tokenDTO!!.getFullAccessToken())
                when {
                    allCartItemsResponse.code() == 200 -> {
                        _lstCartItem.value = allCartItemsResponse.body()!!.stream().map{
                            toCartItem(it)
                        }.collect(Collectors.toList()).toCollection(ArrayList())
                        total.value = calculateTotal()
                        size.value = _lstCartItem.value!!.size
                        _choseList.value = getChose()
                        choseSize.value = _choseList.value!!.size
                        return@launch
                    }
                    allCartItemsResponse.code() == 401 -> {
                        Log.d("cart", "Expire access token")
                        val accessTokenResponse = authRepository.getAccessToken(tokenDTO!!.refreshToken)
                        if(accessTokenResponse.code() == 200){
                            tokenDTO!!.accessToken = accessTokenResponse.body()!!
                            loadCartList()
                        }
                    }
                    allCartItemsResponse.code()== 404 -> {
                        Log.d("cart", "Resource not found")
                    }
                }
            }
        }
    }
}