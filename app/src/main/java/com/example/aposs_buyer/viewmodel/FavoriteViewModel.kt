package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.FavoriteProduct
import com.example.aposs_buyer.model.Image
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor() : ViewModel() {

    private val TAG = "FavoriteViewModel"
    private var _favoriteProducts = MutableLiveData<ArrayList<FavoriteProduct>>()
    val products: LiveData<ArrayList<FavoriteProduct>> get() = _favoriteProducts
    private var _availableProducts = MutableLiveData<ArrayList<FavoriteProduct>>()
    val availableProduct: LiveData<ArrayList<FavoriteProduct>> get() = _availableProducts

    init {
        if (_favoriteProducts.value == null) {
            _favoriteProducts.value = ArrayList()
        }
        _favoriteProducts.value = loadFavoriteProducts()
        setUpAvailableFavorite()
    }

    fun removeFromFavoriteProduct(product: FavoriteProduct) {
        _favoriteProducts.value!!.remove(product)
        _availableProducts.value!!.remove(product)
    }

    private fun setUpAvailableFavorite(){
        val sampleAvailableFavorite = ArrayList<FavoriteProduct>()
        if(_availableProducts.value == null){
            _availableProducts.value = ArrayList()
        }
        for(favorite in _favoriteProducts.value!!){
            if(favorite.isAvailable){
                sampleAvailableFavorite.add(favorite)
            }
        }
        _availableProducts.value = sampleAvailableFavorite
    }

    fun addAvailableProductToCart(product: FavoriteProduct) {
        Log.d(TAG, "Add available product id: ${product.id} to cart")
    }

    private fun loadFavoriteProducts(): ArrayList<FavoriteProduct> {
        val sampleProducts = ArrayList<FavoriteProduct>()
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
        for (i in 0..3) {
            sampleProducts.add(
                FavoriteProduct(
                    1,
                    imgProduct1,
                    "Wilson Mens Hurry Professional",
                    958000,
                    4f,
                    true
                )
            )
            sampleProducts.add(
                FavoriteProduct(
                    2,
                    imgProduct2,
                    "Wilson Mens Shirt",
                    582000,
                    4.5f,
                    false
                )
            )
            sampleProducts.add(FavoriteProduct(3, imgProduct3, "White broccoli", 46000, 4f, true))
            sampleProducts.add(
                FavoriteProduct(
                    4,
                    imgProduct4,
                    "Laptop asus Vivo Book",
                    1958000,
                    5f,
                    false
                )
            )
        }
        return sampleProducts
    }
}