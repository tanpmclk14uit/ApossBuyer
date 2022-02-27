package com.example.aposs_buyer.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.*
import com.example.aposs_buyer.model.dto.*
import com.example.aposs_buyer.responsitory.AuthRepository
import com.example.aposs_buyer.responsitory.OrderRepository
import com.example.aposs_buyer.responsitory.ProductRepository
import com.example.aposs_buyer.responsitory.database.AccountDatabase
import com.example.aposs_buyer.utils.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.stream.Collectors
import javax.inject.Inject

@HiltViewModel
class DetailProductViewModel @Inject constructor(
    private val productRepository: ProductRepository,

) : ViewModel() {

    private var selectedProductId: Long = 0

    private var _selectedProduct = MutableLiveData<ProductDetail>()
    val selectedProduct: LiveData<ProductDetail> get() = _selectedProduct

    private var _selectedProductImages = MutableLiveData<List<Image>>()
    val selectedProductImages: LiveData<List<Image>> get() = _selectedProductImages

    private var _selectedProductStringProperty = MutableLiveData<ArrayList<ProductDetailProperty>>()
    val selectedProductStringProperty: LiveData<ArrayList<ProductDetailProperty>> get() = _selectedProductStringProperty

    private var _selectedProductColorProperty = MutableLiveData<ArrayList<ProductDetailProperty>>()
    val selectedProductColorProperty: LiveData<ArrayList<ProductDetailProperty>> get() = _selectedProductColorProperty

    private val _selectedProductQuantities = MutableLiveData<Int>()
    val selectedProductQuantities: MutableLiveData<Int> get() = _selectedProductQuantities

    private val _selectedProductRating = MutableLiveData<ArrayList<ProductRating>>()
    val selectedProductRating: MutableLiveData<ArrayList<ProductRating>> get() = _selectedProductRating

    private var _sameKindProducts = MutableLiveData<ArrayList<HomeProduct>>()
    val sameKindProducts: LiveData<ArrayList<HomeProduct>> get() = _sameKindProducts

    // USE IN RATING
    val selectedProductRatingFilter = MutableLiveData<ArrayList<ProductRating>>()
    val selectedProductTotalReviewFilter = MutableLiveData<String>()

    val productDetailLoadingState = MutableLiveData<LoadingState>()
    val productRatingLoadingState = MutableLiveData<LoadingState>()

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    fun setSelectedProductId(id: Long) {
        selectedProductId = id
        if (selectedProductId != (-1).toLong()) {
            loadSelectedProductById(selectedProductId)
            loadListImageByID(selectedProductId)
        }
    }

    private fun setSelectedProductMinValue() {
        var minSelect = selectedProductStringProperty.value!![0].valueCountSummarize
        for (property in _selectedProductStringProperty.value!!) {
            if (property.valueCountSummarize < minSelect) {
                minSelect = property.valueCountSummarize
            }
        }
        for (property in _selectedProductColorProperty.value!!) {
            if (property.valueCountSummarize < minSelect) {
                minSelect = property.valueCountSummarize
            }
        }

        _selectedProductQuantities.value = minSelect
    }

    fun notifySelectedStringPropertyChange(propertyId: Long) {
        var newQuantities = 0
        for (property in _selectedProductStringProperty.value!!) {
            if (property.id == propertyId) {
                for (value in property.values) {
                    if (value.isChosen) {
                        newQuantities += value.count
                    }
                }
                if (newQuantities == 0) {
                    property.valueCountSummarize = 11
                } else {
                    property.valueCountSummarize = newQuantities
                }
            }
        }
        setSelectedProductMinValue()
    }

    fun notifySelectedColorPropertyChange(propertyId: Long) {
        var newQuantities = 0
        for (property in _selectedProductColorProperty.value!!) {
            if (property.id == propertyId) {
                for (value in property.values) {
                    if (value.isChosen) {
                        newQuantities += value.count
                    }
                }
                if (newQuantities == 0) {
                    property.valueCountSummarize = 11
                } else {
                    property.valueCountSummarize = newQuantities
                }
            }
        }
        setSelectedProductMinValue()
    }

    fun loadFilterProductRating(filter: String) {
        //fake load case "All" to see data
        selectedProductRatingFilter.value = selectedProductRating.value
        Log.d("ViewModel", "Filter case $filter")
    }

    fun loadFilterProductTotalReviews(filter: String) {
        //fake load case "All" to see data
        selectedProductTotalReviewFilter.value =
            "Total: " + selectedProduct.value!!.totalReview + " reviews"
        Log.d("ViewModel", "Filter total case $filter")
    }


    @SuppressLint("SimpleDateFormat")
    private fun mapToProductRating(productRatingDTO: ProductRatingDTO): ProductRating {
        var listImage: List<Image> = listOf()
        if (productRatingDTO.listImageURL.isNotEmpty()) {
            listImage = productRatingDTO.listImageURL.stream().map {
                Image(it!!)
            }.collect(Collectors.toList())
        }
        var avatar: Image? = null
        if (productRatingDTO.imageAvatarURl != null) {
            avatar = Image(productRatingDTO.imageAvatarURl)
        }
        return ProductRating(
            id = productRatingDTO.id,
            name = productRatingDTO.name,
            rate = productRatingDTO.rating,
            content = productRatingDTO.content,
            time = productRatingDTO.time,
            images = listImage,
            avatar = avatar
        )
    }

    private fun loadProductRatingById(id: Long) {
        productRatingLoadingState.value = LoadingState.Loading
        coroutineScope.launch {
            val ratingResponseDTO = productRepository.loadProductRatingById(id)
            if (ratingResponseDTO.isSuccessful) {
                _selectedProductRating.value = ratingResponseDTO.body()!!.stream().map {
                    mapToProductRating(it!!)
                }.collect(Collectors.toList()).toCollection(ArrayList())
                productRatingLoadingState.value = LoadingState.Success
            }else{
                productRatingLoadingState.value = LoadingState.Fail
            }
        }
    }

    private fun loadProductsByKind(kindId: Long) {
        coroutineScope.launch {
            val productByKindIdResponseDTO = productRepository.loadProductByKindId(kindId)
            if(productByKindIdResponseDTO.isSuccessful){
                if(productByKindIdResponseDTO.body()!= null){
                    _sameKindProducts.value = productByKindIdResponseDTO.body()!!.content.stream().map {
                        mapToHomeProduct(it!!)
                    }.collect(Collectors.toList()).toCollection(ArrayList())
                }
            }
        }
    }
    private fun mapToHomeProduct(productDTO: ProductDTO): HomeProduct {
        return HomeProduct(
            id = productDTO.id,
            image = Image(productDTO.image),
            name = productDTO.name,
            price = productDTO.price,
            rating = productDTO.rating.toFloat(),
            purchased = productDTO.purchased
        )
    }
    private fun loadSelectedProductColorPropertyById(id: Long, productQuantity: Int)  {
        coroutineScope.launch {
            val productStringResponseDTO = productRepository.loadProductColorPropertyById(id)
            if(productStringResponseDTO.isSuccessful){
                _selectedProductColorProperty.value =  productStringResponseDTO.body()!!.stream().map {
                    mapToProperty(it!!, productQuantity)
                }.collect(Collectors.toList()).toCollection(ArrayList())
            }
        }
    }
    private fun mapToPropertyValue(productPropertyValueDTO: ProductPropertyValueDTO, propertyId: Long): PropertyValue{
        return PropertyValue(
            productPropertyValueDTO.id,
            productPropertyValueDTO.name,
            propertyId,
            productPropertyValueDTO.value,
            productPropertyValueDTO.additionalPrice,
            productPropertyValueDTO.quantity,
            false
        )
    }
    private fun mapToProperty(productPropertyDTO: ProductPropertyDTO, productQuantity: Int): ProductDetailProperty{
        val productPropertyValues: List<PropertyValue> = productPropertyDTO.valueDTOS.stream().map {
            mapToPropertyValue(it, productPropertyDTO.id)
        }.collect(Collectors.toList())
        return ProductDetailProperty(
            productPropertyDTO.id,
            productPropertyDTO.name,
            productPropertyValues,
            productQuantity
        )
    }

    private fun loadSelectedProductStringPropertyById(id: Long, productQuantity: Int) {
        coroutineScope.launch {
            val productStringResponseDTO = productRepository.loadProductStringPropertyById(id)
            if(productStringResponseDTO.isSuccessful){
                _selectedProductStringProperty.value =  productStringResponseDTO.body()!!.stream().map {
                    mapToProperty(it!!, productQuantity)
                }.collect(Collectors.toList()).toCollection(ArrayList())
            }
        }
    }

    private fun mapToProductDetail(productDetailDTO: ProductDetailDTO, id: Long): ProductDetail {
        return ProductDetail(
            id,
            productDetailDTO.name,
            productDetailDTO.price,
            productDetailDTO.purchase,
            productDetailDTO.rating,
            true,
            productDetailDTO.description,
            productDetailDTO.quantity,
            productDetailDTO.kindName,
            productDetailDTO.totalReview
        )
    }

    private fun loadSelectedProductById(id: Long) {
        productDetailLoadingState.value = LoadingState.Loading
        coroutineScope.launch {
            val productResponse = productRepository.loadProductById(id)
            if (productResponse.isSuccessful) {
                _selectedProduct.value = mapToProductDetail(productResponse.body()!!, id)
                _selectedProductQuantities.value = _selectedProduct.value!!.availableQuantities
               loadProductsByKind(productResponse.body()!!.kindId)
                if(_selectedProduct.value!!.totalReview != 0){
                    loadProductRatingById(selectedProductId)
                }else{
                    _selectedProductRating.value = ArrayList()
                    productRatingLoadingState.value = LoadingState.Success
                }
                loadSelectedProductStringPropertyById(selectedProductId, _selectedProduct.value!!.availableQuantities)
                loadSelectedProductColorPropertyById(selectedProductId, _selectedProduct.value!!.availableQuantities)
                productDetailLoadingState.value = LoadingState.Success
            } else {
                productDetailLoadingState.value = LoadingState.Fail
            }
        }
    }

    private fun mapToImage(productImageDTO: ProductImageDTO): Image {
        return Image(productImageDTO.imageUrl)
    }

    private fun loadListImageByID(id: Long) {
        coroutineScope.launch {
            val productImageResponse = productRepository.loadProductImageByProductId(id)
            if (productImageResponse.isSuccessful) {
                _selectedProductImages.value = productImageResponse.body()!!.stream().map {
                    mapToImage(it)
                }.collect(Collectors.toList())
            }
        }
    }


}