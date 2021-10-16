package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.CartItem
import com.example.aposs_buyer.model.Image
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(): ViewModel(){

    private val _lstCartItem = MutableLiveData<ArrayList<CartItem>>()
    val lstCartItem: LiveData<ArrayList<CartItem>> get() = _lstCartItem

    private val _choseList= MutableLiveData<ArrayList<CartItem>>()
    val choseList: LiveData<ArrayList<CartItem>> get() = _choseList

    val total = MutableLiveData<String>()
    val size = MutableLiveData<Int>()
    val choseSize = MutableLiveData<Int>()

    init {
        _lstCartItem.value = loadCartList()
        total.value = calculateTotal()
        if (_lstCartItem.value == null)
        {
            size.value = 0
        }
        else size.value = _lstCartItem.value!!.size
        _choseList.value = getChose()
        choseSize.value = _choseList.value!!.size
        Log.d("CartViewModel" ,_lstCartItem.value!![0].amount.toString())
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
            "$$sum"
        } else "$0"
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

    private fun loadCartList(): ArrayList<CartItem>
    {
        val sampleProducts = ArrayList<CartItem>()
        val imgURl1 =
            "https://www.tennisgearhub.com/wp-content/uploads/2020/09/Wilson-Mens-Hurry-Professional-25-Pickleball-Footwear-Racquetball-BlueWhitePurple-13.jpg"
        val imgURL2 =
            "https://th.bing.com/th/id/OIP.U6PJxFUyX6Nigx3Sv2ObpgHaHa?pid=ImgDet&w=2000&h=2000&rs=1"
        val imgURL4 = "https://api.duniagames.co.id/api/content/upload/file/9607962621588584775.JPG"
        val imgProduct1 = Image(imgURl1)
        val imgProduct2 = Image(imgURL2)
        val imgProduct4 = Image(imgURL4)
            sampleProducts.add(
                CartItem(
                    1,
                    imgProduct1,
                    "Wilson Mens Hurry Professional",
                    1,
                    1,
                    "Color: red, size: 30",
                    false
                )
            )
            sampleProducts.add(
                CartItem(
                    2,
                    imgProduct2,
                    "Wilson Mens Shirt",
                    2,
                    1,
                    "Color: red, size: 30",
                    false
                )
            )
            sampleProducts.add(
                CartItem(
                    4,
                    imgProduct4,
                    "Laptop asus Vivo Book",
                    2,
                    1,
                    "Color: red, size: 30",
                    false
                )
            )
        return sampleProducts
    }
}