package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.*
import com.example.aposs_buyer.model.dto.ProductDetailDTO
import com.example.aposs_buyer.model.dto.ProductImageDTO
import com.example.aposs_buyer.responsitory.ProductRepository
import com.example.aposs_buyer.responsitory.webservice.ProductAPIService
import com.example.aposs_buyer.utils.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.stream.Collectors
import javax.inject.Inject

@HiltViewModel
class DetailProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
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

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val TAG = "DetailProductViewModel"


    fun setSelectedProductId(id: Long) {
        selectedProductId = id
        if (selectedProductId != (-1).toLong()) {
            loadSelectedProductById(selectedProductId)
            loadListImageByID(selectedProductId)
            _selectedProductStringProperty.value =
                loadSelectedProductStringPropertyById(selectedProductId)
            _selectedProductColorProperty.value =
                loadSelectedProductColorPropertyById(selectedProductId)
            _selectedProductRating.value = loadProductRatingById(selectedProductId)
            Log.d(TAG, selectedProductId.toString())
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


    private fun loadProductRatingById(id: Long): ArrayList<ProductRating> {
        val sampleProductRating = ArrayList<ProductRating>()
        val imgURl1 =
            "https://www.tennisgearhub.com/wp-content/uploads/2020/09/Wilson-Mens-Hurry-Professional-25-Pickleball-Footwear-Racquetball-BlueWhitePurple-13.jpg"
        val imgURL2 =
            "https://th.bing.com/th/id/OIP.U6PJxFUyX6Nigx3Sv2ObpgHaHa?pid=ImgDet&w=2000&h=2000&rs=1"
        val imgURL3 =
            "https://leep.imgix.net/2021/01/bong-cai-trang-giup-giam-can_001.jpg?auto=compress&fm=pjpg&ixlib=php-1.2.1"
        val imgProduct1 = Image(imgURl1)
        val imgProduct2 = Image(imgURL2)
        val imgProduct3 = Image(imgURL3)
        val avatar =
            Image("https://www.logolynx.com/images/logolynx/6b/6b72dfcff9c3a70b632dd47c88e842d9.png")
        val sampleImageRating = ArrayList<Image>()
        val sampleImageRating1 = ArrayList<Image>()
        sampleImageRating.add(imgProduct1)
        sampleImageRating.add(imgProduct2)
        sampleImageRating.add(imgProduct3)
        sampleImageRating1.add(imgProduct1)
        sampleProductRating.add(
            ProductRating(
                1,
                "Peter Vu",
                4.1f,
                "13:12",
                "Good, fast delivery",
                sampleImageRating,
                avatar
            )
        )
        sampleProductRating.add(
            ProductRating(
                2,
                "Peter Pham",
                3f,
                "07:12",
                "Normal, good delivery",
                ArrayList(),
                avatar
            )
        )
        sampleProductRating.add(
            ProductRating(
                3,
                "Tomy Teo",
                5f,
                "15:12",
                "Wilson's Pro Staff Classic has been tearing up the courts since 1986. Its thickly cushioned upper and anti-shock pad-enhanced heel and forefoot bring the comfort while the midfoot wrap offers extra stability. An all-court Duralast Supreme outsole adds to the life of the shoe.",
                sampleImageRating1,
                avatar
            )
        )
        return sampleProductRating
    }

    private fun loadProductsByKind(kind: String): ArrayList<HomeProduct> {
        val sampleProducts = ArrayList<HomeProduct>()
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
        for (i in 0..10) {
            sampleProducts.add(
                HomeProduct(
                    1,
                    imgProduct1,
                    "Wilson Mens Hurry Professional",
                    958000,
                    4f,
                    1,
                    true,
                )
            )
            sampleProducts.add(
                HomeProduct(
                    2,
                    imgProduct2,
                    "Wilson Mens Shirt",
                    582000,
                    4.5f,
                    1,
                    false
                )
            )
            sampleProducts.add(HomeProduct(3, imgProduct3, "White broccoli", 46000, 4f, 1,false))
            sampleProducts.add(
                HomeProduct(
                    4,
                    imgProduct4,
                    "Laptop asus Vivo Book",
                    1958000,
                    5f,
                    1,
                    true
                )
            )
        }
        return sampleProducts
    }

    private fun loadSelectedProductColorPropertyById(id: Long): ArrayList<ProductDetailProperty> {
        val sampleProductProperty: ArrayList<ProductDetailProperty> = ArrayList()
        val sampleDetailPropertyValue1: ArrayList<PropertyValue> = ArrayList()
        val sampleDetailPropertyValue2: ArrayList<PropertyValue> = ArrayList()
        sampleDetailPropertyValue1.add(PropertyValue(1, "Yellow", 3, "#FFC420", 0, 2, false))
        sampleDetailPropertyValue1.add(PropertyValue(2, "Red blood", 3, "#F20850", 0, 9, false))
        sampleDetailPropertyValue1.add(PropertyValue(3, "Deep blue", 3, "#060DD9", 0, 0, false))
        sampleDetailPropertyValue2.add(PropertyValue(4, "White", 4, "#FFFFFF", 0, 0, false))
        sampleDetailPropertyValue2.add(PropertyValue(5, "Black", 4, "#262626", 0, 4, false))
        sampleDetailPropertyValue2.add(PropertyValue(6, "Milk", 4, "#F7E6AD", 0, 7, false))
        sampleProductProperty.add(
            ProductDetailProperty(
                3,
                "Color",
                sampleDetailPropertyValue1,
                11
            )
        )
        sampleProductProperty.add(
            ProductDetailProperty(
                4,
                "Boot Color",
                sampleDetailPropertyValue2,
                11
            )
        )
        return sampleProductProperty
    }

    private fun loadSelectedProductStringPropertyById(id: Long): ArrayList<ProductDetailProperty> {
        val sampleProductProperty: ArrayList<ProductDetailProperty> = ArrayList()
        val sampleDetailPropertyValue1: ArrayList<PropertyValue> = ArrayList()
        val sampleDetailPropertyValue2: ArrayList<PropertyValue> = ArrayList()
        sampleDetailPropertyValue1.add(PropertyValue(1, "38", 1, "38", 1000, 5, false))
        sampleDetailPropertyValue1.add(PropertyValue(2, "39", 1, "39", 2000, 6, false))
        sampleDetailPropertyValue1.add(PropertyValue(3, "40", 1, "40", 0, 0, false))
        sampleDetailPropertyValue2.add(PropertyValue(4, "1m", 2, "1m", 5000, 5, false))
        sampleDetailPropertyValue2.add(PropertyValue(5, "2m", 2, "2m", 0, 0, false))
        sampleDetailPropertyValue2.add(PropertyValue(6, "3m", 2, "3m", 0, 6, false))
        sampleProductProperty.add(
            ProductDetailProperty(
                1,
                "Size",
                sampleDetailPropertyValue1,
                11
            )
        )
        sampleProductProperty.add(
            ProductDetailProperty(
                2,
                "Shoestring",
                sampleDetailPropertyValue2,
                11
            )
        )
        return sampleProductProperty
    }

    private fun mapToProductDetail(productDetailDTO: ProductDetailDTO): ProductDetail{
        return ProductDetail(
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
    //Done
    private fun loadSelectedProductById(id: Long) {
        //Load basic information of product by id
        productDetailLoadingState.value = LoadingState.Loading
        coroutineScope.launch {
            val productResponse = productRepository.loadProductById(id)
            if(productResponse.isSuccessful){
                _selectedProduct.value = mapToProductDetail(productResponse.body()!!)
                _selectedProductQuantities.value = _selectedProduct.value!!.availableQuantities
                _sameKindProducts.value = loadProductsByKind(_selectedProduct.value!!.kind)
                productDetailLoadingState.value = LoadingState.Success
            }else{
                productDetailLoadingState.value = LoadingState.Fail
            }
        }
    }
    private fun mapToImage(productImageDTO: ProductImageDTO): Image{
        return Image(productImageDTO.imageUrl)
    }

    private fun loadListImageByID(id: Long) {
        coroutineScope.launch {
            val productImageResponse = productRepository.loadProductImageByProductId(id)
            if(productImageResponse.isSuccessful){
                _selectedProductImages.value = productImageResponse.body()!!.stream().map {
                    mapToImage(it)
                }.collect(Collectors.toList())
            }
        }
    }
}