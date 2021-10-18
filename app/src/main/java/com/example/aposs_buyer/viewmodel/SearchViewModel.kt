package com.example.aposs_buyer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ListAdapter
import com.example.aposs_buyer.model.HomeProduct
import com.example.aposs_buyer.model.Image
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(): ViewModel() {

    private val _listForTestSearch = MutableLiveData<MutableList<HomeProduct>>()

    val listForDisplay =  MutableLiveData<MutableList<HomeProduct>>()

    val curentKeyWord = MutableLiveData<String>()

    init {
        _listForTestSearch.value = loadListTestSearch()
        listForDisplay.value = _listForTestSearch.value
    }

    fun addToFavorite(product: HomeProduct) {
        // add to favorite in db
    }

    fun removeFromFavorite(product: HomeProduct) {
        //remove from favorite in db
    }

    fun changeListDisplay()
    {
        listForDisplay.value = mutableListOf<HomeProduct>()
        for (i in 0 until _listForTestSearch.value!!.size)
        {
            if (_listForTestSearch.value!![i].name.contains(curentKeyWord.value.toString()))
            {
                listForDisplay.value!!.add(_listForTestSearch.value!![i])
            }
        }
    }


    private fun loadListTestSearch(): MutableList<HomeProduct>
    {
        val imgURl1 =
            "https://www.tennisgearhub.com/wp-content/uploads/2020/09/Wilson-Mens-Hurry-Professional-25-Pickleball-Footwear-Racquetball-BlueWhitePurple-13.jpg"
        val imgURL2 =
            "https://th.bing.com/th/id/OIP.U6PJxFUyX6Nigx3Sv2ObpgHaHa?pid=ImgDet&w=2000&h=2000&rs=1"
        val imgURL3 =
            "https://leep.imgix.net/2021/01/bong-cai-trang-giup-giam-can_001.jpg?auto=compress&fm=pjpg&ixlib=php-1.2.1"
        val imgURL4 =
            "https://api.duniagames.co.id/api/content/upload/file/9607962621588584775.JPG"
        val imgProduct1 = Image(imgURl1)
        val imgProduct2 = Image(imgURL2)
        val imgProduct3 = Image(imgURL3)
        val imgProduct4 = Image(imgURL4)
        val sampleProducts: MutableList<HomeProduct> = mutableListOf()
        sampleProducts.add(
            HomeProduct(
                1,
                imgProduct1,
                "Wilson Mens Hurry Professional",
                958000,
                4f,
                true
            )
        )
        sampleProducts.add(
            HomeProduct(
                2,
                imgProduct2,
                "Wilson Mens Shirt",
                582000,
                4.5f,
                false
            )
        )
        sampleProducts.add(HomeProduct(3, imgProduct3, "White broccoli", 46000, 4f, false))
        sampleProducts.add(
            HomeProduct(
                4,
                imgProduct4,
                "Laptop asus Vivo Book",
                1958000,
                5f,
                true
            )
        )
        sampleProducts.add(
            HomeProduct(
                5,
                imgProduct4,
                "Laptop asus Vivo Book",
                1958000,
                5f,
                true
            )
        )
        sampleProducts.add(
            HomeProduct(
                6,
                imgProduct4,
                "Laptop asus Vivo Book",
                1958000,
                5f,
                true
            )
        )
        sampleProducts.add(
            HomeProduct(
                7,
                imgProduct4,
                "Laptop asus Vivo Book",
                1958000,
                5f,
                true
            )
        )
        sampleProducts.add(
            HomeProduct(
                8,
                imgProduct4,
                "Laptop asus Vivo Book",
                1958000,
                5f,
                true
            )
        )
        sampleProducts.add(
            HomeProduct(
                9,
                imgProduct4,
                "Laptop asus Vivo Book",
                1958000,
                5f,
                true
            )
        )
        sampleProducts.add(
            HomeProduct(
                10,
                imgProduct4,
                "Laptop asus Vivo Book",
                1958000,
                5f,
                true
            )
        )
        sampleProducts.add(
            HomeProduct(
                11,
                imgProduct4,
                "Laptop asus Vivo Book",
                1958000,
                5f,
                true
            )
        )
        sampleProducts.add(
            HomeProduct(
                12,
                imgProduct4,
                "Laptop asus Vivo Book",
                1958000,
                5f,
                true
            )
        )
        sampleProducts.add(
            HomeProduct(
                13,
                imgProduct4,
                "Laptop asus Vivo Book",
                1958000,
                5f,
                true
            )
        )
        return sampleProducts
    }
}