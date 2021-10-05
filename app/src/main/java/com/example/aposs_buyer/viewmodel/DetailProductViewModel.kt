package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.HomeProduct
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.ProductDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailProductViewModel @Inject constructor(
) : ViewModel() {
    private var selectedProductId: Long = 0
    private var _selectedProduct = MutableLiveData<ProductDetail>()
    val selectedProduct: LiveData<ProductDetail> get() = _selectedProduct
    private var _selectedProductImages = MutableLiveData<List<Image>>()
    val selectedProductImages: LiveData<List<Image>> get() = _selectedProductImages
    private val TAG = "DetailProductViewModel"

    fun setSelectedProductId(id: Long) {
        selectedProductId = id
        if (selectedProductId != (-1).toLong()) {
            _selectedProduct.value = loadSelectedProductById(selectedProductId)
            _selectedProductImages.value = loadListImageByID(selectedProductId)
            Log.d(TAG, selectedProductId.toString())
        }
    }

    private fun loadSelectedProductById(id: Long): ProductDetail {
        //Load basic information of product by id
        return ProductDetail(
            "Wilson Mens Hurry Professional",
            958000,
            480,
            4f,
            true,
            "Wilson's Pro Staff Classic has been tearing up the courts since 1986. Its thickly cushioned upper and anti-shock pad-enhanced heel and forefoot bring the comfort while the midfoot wrap offers extra stability. An all-court Duralast Supreme outsole adds to the life of the shoe.\n" +
                    "\n" +
                    "Wilson tennis footwear is designed to meet the demands of different athletes and player profiles. The line is divided into collections that serve specific purposes. The Tour Collection is super lightweight, breathable and fits like a glove. The Trance Collection is renowned for supreme durability and unrivaled stability. Finally, the Pro Staff Collection provides all-around comfort and all-court performance.",
            245,
            "shoe"
        )
    }
    private fun loadListImageByID(id: Long): List<Image>{
        val sampleProductsImage = ArrayList<Image>()
        val imgURl1 =
            "https://www.tennisgearhub.com/wp-content/uploads/2020/09/Wilson-Mens-Hurry-Professional-25-Pickleball-Footwear-Racquetball-BlueWhitePurple-13.jpg"
        val imgURL2 =
            "https://th.bing.com/th/id/OIP.U6PJxFUyX6Nigx3Sv2ObpgHaHa?pid=ImgDet&w=2000&h=2000&rs=1"
        val imgURL3 =
            "https://leep.imgix.net/2021/01/bong-cai-trang-giup-giam-can_001.jpg?auto=compress&fm=pjpg&ixlib=php-1.2.1"
        val imgURL4 = "https://api.duniagames.co.id/api/content/upload/file/9607962621588584775.JPG"
        val imgProduct1 = Image(imgURl1)
        val imgProduct2 = Image(imgURL2)
        val imgProduct3 = Image(imgURL3)
        val imgProduct4 = Image(imgURL4)
        sampleProductsImage.add(imgProduct1)
        sampleProductsImage.add(imgProduct2)
        sampleProductsImage.add(imgProduct3)
        sampleProductsImage.add(imgProduct4)
        return sampleProductsImage
    }
}