package com.example.aposs_buyer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.ProductDetail
import com.example.aposs_buyer.model.ProductDetailProperty
import com.example.aposs_buyer.model.dto.CartDTO
import com.example.aposs_buyer.responsitory.AuthRepository
import com.example.aposs_buyer.responsitory.CartRepository
import com.example.aposs_buyer.utils.LoadingStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailProductDiaLogViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {

    val productTypeCart = MutableLiveData<CartDTO>()
    val productTypeCartAmount = MutableLiveData<Int>()

    private var _selectedProductImages = MutableLiveData<Image>()
    val selectedProductImages: LiveData<Image> get() = _selectedProductImages

    private var _selectedProductStringPropertyDiaLog =
        MutableLiveData<ArrayList<ProductDetailProperty>>()
    val selectedProductStringPropertyDiaLog: LiveData<ArrayList<ProductDetailProperty>> get() = _selectedProductStringPropertyDiaLog

    private var _selectedProductColorPropertyDiaLog =
        MutableLiveData<ArrayList<ProductDetailProperty>>()
    val selectedProductColorPropertyDiaLog: LiveData<ArrayList<ProductDetailProperty>> get() = _selectedProductColorPropertyDiaLog

    private val _selectedProductQuantitiesDiaLog = MutableLiveData<Int>()
    val selectedProductQuantitiesDiaLog: MutableLiveData<Int> get() = _selectedProductQuantitiesDiaLog

    private var _selectedProduct = MutableLiveData<ProductDetail>()
    val selectedProduct: LiveData<ProductDetail> get() = _selectedProduct

    var propertyValue = MutableLiveData<String>()
    var isPropertyValueError = MutableLiveData<Boolean>()

//    private fun makeTheProductTypeCart(
//        selectedProduct: ProductDetail,
//        selectedProductImages: Image
//    ) {
//        val imageURL: String = selectedProductImages.imgURL
//        val cartDto = CartDTO(
//            id = -1,
//            imageUrl = imageURL,
//            name = selectedProduct.name,
//            price = selectedProduct.price,
//            quantity = 1,
//            property = "",
//            productId = selectedProduct.id,
//            select = true
//        )
//        productTypeCartAmount.value = 1
//        productTypeCart.value = cartDto
//        _selectedProduct.value = selectedProduct
//        _selectedProductImages.value = selectedProductImages
//        _selectedProductQuantitiesDiaLog.value = selectedProduct.availableQuantities
//    }

}