package com.example.aposs_buyer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.HomeProduct
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.Kind
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class KindViewModel @Inject constructor(): ViewModel() {

    private val selectedCategoryId = MutableLiveData<Long>()

    private val _listKind = MutableLiveData<MutableList<Kind>>()
    val listKind : LiveData<MutableList<Kind>> get() = _listKind

    fun setSelectedCategory()
    {
        _listKind.value = loadProduct(selectedCategoryId.value!!)
    }

    fun getSelectedCategory(): MutableList<Kind>
    {
        return _listKind.value!!
    }

    fun setSelectedKindID(selectedKindId: Long)
    {
        selectedCategoryId.value = selectedKindId
    }

    private fun loadProduct(selectedKindId: Long): MutableList<Kind>
    {
        //declare call db to get all product having selected kind id here
        return when (selectedKindId) {
            1L -> {
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
                val sampleKinds: MutableList<Kind> = mutableListOf()
                sampleKinds.add(Kind(1L, "Soccer shose", 329, 25, 3.4F, sampleProducts))
                sampleKinds.add(Kind(1L, "Sandal", 329, 25, 3.4F, sampleProducts))
                sampleKinds.add(Kind(1L, "Bitis hunter", 329, 25, 3.4F, sampleProducts))
                sampleKinds.add(Kind(1L, "cothurnus", 329, 25, 3.4F, sampleProducts))
                sampleKinds
            }
            else ->{
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
                val sampleKinds: MutableList<Kind> = mutableListOf()
                sampleKinds.add(Kind(1L, "Vegetable", 23424, 75, 3.4F, sampleProducts))
                sampleKinds.add(Kind(1L, "Meat", 1244, 253, 3.8F, sampleProducts))
                sampleKinds.add(Kind(1L, "Chicken", 14124, 23, 4.4F, sampleProducts))
                sampleKinds.add(Kind(1L, "Fish", 25452, 34, 2.4F, sampleProducts))
                sampleKinds
            }
        }
    }
}