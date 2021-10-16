package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.CartItem
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.RateNowItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RateNowViewModel @Inject constructor(): ViewModel() {

    private val _listRateNow = MutableLiveData<ArrayList<RateNowItem>>()
    val listRateNow : LiveData<ArrayList<RateNowItem>> get() = _listRateNow

    init {
        _listRateNow.value = loadRateNowList()
    }
    private fun loadRateNowList():  ArrayList<RateNowItem>
    {
        val sampleProducts = ArrayList<RateNowItem>()
        val imgURl1 =
            "https://www.tennisgearhub.com/wp-content/uploads/2020/09/Wilson-Mens-Hurry-Professional-25-Pickleball-Footwear-Racquetball-BlueWhitePurple-13.jpg"
        val imgURL2 =
            "https://th.bing.com/th/id/OIP.U6PJxFUyX6Nigx3Sv2ObpgHaHa?pid=ImgDet&w=2000&h=2000&rs=1"
        val imgURL4 = "https://api.duniagames.co.id/api/content/upload/file/9607962621588584775.JPG"
        val imgProduct1 = Image(imgURl1)
        val imgProduct2 = Image(imgURL2)
        val imgProduct4 = Image(imgURL4)
        sampleProducts.add(
            RateNowItem(
                1,
                imgProduct1,
                "Wilson Mens Hurry Professional",
                1,
                1,
                "Color: red, size: 30",
            )
        )
        sampleProducts.add(
            RateNowItem(
                2,
                imgProduct2,
                "Wilson Mens Shirt",
                2,
                1,
                "Color: red, size: 30",
            )
        )
        sampleProducts.add(
            RateNowItem(
                4,
                imgProduct4,
                "Laptop asus Vivo Book",
                2,
                1,
                "Color: red, size: 30",
            )
        )
        return sampleProducts
    }
}