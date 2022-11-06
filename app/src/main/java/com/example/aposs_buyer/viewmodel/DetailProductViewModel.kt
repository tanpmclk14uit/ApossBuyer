package com.example.aposs_buyer.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aposs_buyer.model.*
import com.example.aposs_buyer.model.dto.*
import com.example.aposs_buyer.responsitory.AuthRepository
import com.example.aposs_buyer.responsitory.CartRepository
import com.example.aposs_buyer.responsitory.DeliveryAddressRepository
import com.example.aposs_buyer.responsitory.ProductRepository
import com.example.aposs_buyer.utils.Converter
import com.example.aposs_buyer.utils.LoadingStatus
import com.example.aposs_buyer.utils.OrderStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.net.SocketTimeoutException
import java.util.*
import java.util.stream.Collectors
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.streams.toList

@HiltViewModel
class DetailProductViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository,
    private val authRepository: AuthRepository,
    private val deliveryAddressRepository: DeliveryAddressRepository
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

    val selectedProductPrice = MutableLiveData<String>()

    private var _sameKindProducts = MutableLiveData<ArrayList<HomeProduct>>()
    val sameKindProducts: LiveData<ArrayList<HomeProduct>> get() = _sameKindProducts

    // USE IN RATING
    val selectedProductRatingFilter = MutableLiveData<ArrayList<ProductRating>>()
    val selectedProductTotalReviewFilter = MutableLiveData<String>()

    val productDetailLoadingState = MutableLiveData<LoadingStatus>()
    val productRatingLoadingState = MutableLiveData<LoadingStatus>()

    private val currentSelectedValues = ArrayList<PropertyValue>()

    var propertyValue = MutableLiveData<String>()
    var isPropertyValueError = MutableLiveData<Boolean>()
    var cartAmount = MutableLiveData<Int>()
    private var currentAdditionalPrice = 0

    val addToCartStatus = MutableLiveData<LoadingStatus>()
    var productNature: String = ""
    var productDefaultId: Long = -1L


    fun setSelectedProductId(id: Long) {
        selectedProductId = id
        if (selectedProductId != -1L) {
            loadSelectedProductById(selectedProductId)
            loadListImageByID(selectedProductId)
        }
        cartAmount.value = 1
    }

    suspend fun makeOrder(): Order? {
        var newOrder: Order? = null
        val address: Address? = loadDefaultAddress()
        val setId: Long = loadSetIdByByProductIdAndListProperty()
        if (address != null && setId != -1L) {
            val orderBillingItem = mutableListOf<OrderBillingItem>(
                OrderBillingItem(
                    cartId = -1,
                    setId = setId,
                    quantity = cartAmount.value!!,
                    image = selectedProductImages.value!![0],
                    name = selectedProduct.value!!.name,
                    price = selectedProduct.value!!.price + currentAdditionalPrice,
                    property = propertyValue.value!!
                )
            )
            newOrder = Order(
                billingItems = orderBillingItem,
                address = address.getFullAddress(),
                totalPrice = (selectedProduct.value!!.price + currentAdditionalPrice) * cartAmount.value!!
            )
        }
        return newOrder
    }

    private suspend fun loadDefaultAddress(): Address? {
        try {
            val currentAccessToken = authRepository.getCurrentAccessTokenFromRoom()
            if (!currentAccessToken.isNullOrBlank()) {
                val defaultAddressResponse =
                    deliveryAddressRepository.getUserDefaultAddress(
                        currentAccessToken
                    )
                if (defaultAddressResponse.isSuccessful) {
                    val defaultAddressDTOResponseBody = defaultAddressResponse.body()
                    val defaultAddressDTO = defaultAddressDTOResponseBody!!
                    return convertDeliveryAddressDTOToAddress(defaultAddressDTO)
                } else {
                    if (defaultAddressResponse.code() == 401) {
                        if (authRepository.loadNewAccessTokenSuccess()) {
                            loadDefaultAddress()
                        }
                    }
                }
            }
        } catch (e: Exception) {
            if (e is SocketTimeoutException) {
                loadDefaultAddress()
            } else {
                Log.d("DetailProductViewModel", e.stackTraceToString())
            }
        }
        return null
    }

    private fun convertDeliveryAddressDTOToAddress(deliveryAddressDTO: DeliveryAddressDTO): Address {
        return Address(
            id = deliveryAddressDTO.id,
            name = deliveryAddressDTO.name,
            gender = deliveryAddressDTO.gender,
            phoneNumber = deliveryAddressDTO.phoneNumber,
            city = Province(deliveryAddressDTO.province.id, deliveryAddressDTO.province.name),
            district = District(
                deliveryAddressDTO.district.id,
                deliveryAddressDTO.district.name,
                deliveryAddressDTO.district.province
            ),
            ward = Ward(
                deliveryAddressDTO.ward.id,
                deliveryAddressDTO.ward.name,
                deliveryAddressDTO.ward.district
            ),
            isDefaultAddress = deliveryAddressDTO.isDefault,
            addressLane = deliveryAddressDTO.addressLane
        )
    }

    private fun makeCart(setId: Long): CartDTO {
        return CartDTO(
            setId = setId,
            quantity = cartAmount.value!!,
            select = true
        )
    }

    fun addNewCart() {
        viewModelScope.launch(Dispatchers.IO) {
            val setId = loadSetIdByByProductIdAndListProperty()
            if (setId != -1L) {
                val cartDTO = makeCart(setId)
                saveCart(cartDTO)
            } else {
                addToCartStatus.postValue(LoadingStatus.Fail)
            }
        }
    }

    private suspend fun saveCart(cartDTO: CartDTO) {
        addToCartStatus.postValue(LoadingStatus.Loading)
        val accessToken = authRepository.getCurrentAccessTokenFromRoom()
        if (!accessToken.isNullOrBlank()) {
            val addNewCartResponse = cartRepository.addNewCart(
                accessToken,
                cartDTO
            )
            if (addNewCartResponse.isSuccessful) {
                addToCartStatus.postValue(LoadingStatus.Success)
            } else {
                if (addNewCartResponse.code() == 401) {
                    if (authRepository.loadNewAccessTokenSuccess()) {
                        saveCart(cartDTO)
                    } else {
                        addToCartStatus.postValue(LoadingStatus.Fail)
                    }
                } else {
                    addToCartStatus.postValue(LoadingStatus.Fail)
                }
            }
        } else {
            addToCartStatus.postValue(LoadingStatus.Fail)
        }
    }

    private suspend fun loadSetIdByByProductIdAndListProperty(): Long {
        val currentSelectedValueId: List<Long>
        withContext(Dispatchers.Main) {
            currentSelectedValueId =
                if (selectedProductStringProperty.value?.isEmpty() == true) {
                    listOf(productDefaultId)
                } else {
                    currentSelectedValues.stream().map { it.id }.toList()
                }
        }
        val setResponse = productRepository.getSetIdByByProductIdAndListProperty(
            selectedProductId,
            currentSelectedValueId
        )
        return if (setResponse.isSuccessful) {
            setResponse.body() ?: -1L
        } else {
            val setDefaultResponse = productRepository.getSetIdByByProductIdAndListProperty(
                selectedProductId,
                mutableListOf<Long>(0)
            )
            setDefaultResponse.body() ?: -1L
        }
    }


    fun notifySelectedPropertyChange(property: PropertyValue) {
        if (property.isChosen) {
            if (isValueOfTheSamePropertyExist(property)) {
                removeExistedValue(property)
            }
            currentSelectedValues.add(property)
        } else {
            currentSelectedValues.remove(property)
        }
        if (currentSelectedValues.isNotEmpty()) {
            propertyValue.value = makeStringProperty()
            isPropertyValueError.value = false
            loadQuantityOfProductByProductIdAndListProperty(
                selectedProductId,
                currentSelectedValues.stream().map { it.id }.toList()
            )
            loadPriceOfProductByProductIdAndListProperty(
                selectedProductId,
                currentSelectedValues.stream().map { it.id }.toList()
            )
        } else {
            selectedProductQuantities.value = _selectedProduct.value!!.availableQuantities
            selectedProductPrice.value =
                Converter.convertFromIntToCurrencyString(_selectedProduct.value!!.price)
            propertyValue.value = "Select property to continue"
            isPropertyValueError.value = true
        }
    }

    fun validatePropertyValue() {
        if (isSpecialPropertyHaveNoneValueSelected()) {
            propertyValue.value = "Select property to continue"
            isPropertyValueError.value = true
        } else {
            propertyValue.value = makeStringProperty()
            isPropertyValueError.value = false
        }
    }

    private fun makeStringProperty(): String {
        var sampleProperty = ""
        selectedProductStringProperty.value?.let {
            for (property in it) {
                for (value in property.values) {
                    if (value.isChosen) {
                        sampleProperty += property.name
                        sampleProperty += ": "
                        sampleProperty += value.name
                        sampleProperty += ", "
                    }
                }
            }
        }
        selectedProductColorProperty.value?.let {
            for (property in it) {
                for (value in property.values) {
                    if (value.isChosen) {
                        sampleProperty += property.name
                        sampleProperty += ": "
                        sampleProperty += value.name
                        sampleProperty += ", "
                    }
                }
            }
        }
        return sampleProperty
    }


    private fun loadQuantityOfProductByProductIdAndListProperty(
        productId: Long,
        propertyIds: List<Long>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
            val quantityResponse =
                productRepository.getQuantityOfProductByProductIdAndPropertyValues(
                    productId,
                    propertyIds
                )
            if (quantityResponse.isSuccessful) {
                selectedProductQuantities.postValue(quantityResponse.body())
                withContext(Dispatchers.Main) {
                    if (selectedProductQuantities.value!! < cartAmount.value!!) {
                        cartAmount.value = selectedProductQuantities.value
                    }
                }
            }
            } catch (e: Exception) {
                if (e is SocketTimeoutException) {
                    loadQuantityOfProductByProductIdAndListProperty(productId, propertyIds)
                } else {
                    Log.d("DetailProductViewModel", e.stackTraceToString())
                }
            }
        }
    }

    fun isSpecialPropertyHaveNoneValueSelected(): Boolean {
        var totalChose: Int
        if (selectedProductStringProperty.value != null) {
            for (property in selectedProductStringProperty.value!!) {
                totalChose = 0
                for (value in property.values) {
                    if (value.isChosen) {
                        totalChose++
                    }
                }
                if (totalChose == 0) {
                    return true
                }
            }
        }
        if (selectedProductColorProperty.value != null) {
            for (property in selectedProductColorProperty.value!!) {
                totalChose = 0
                for (value in property.values) {
                    if (value.isChosen) {
                        totalChose++
                    }
                }
                if (totalChose == 0) {
                    return true
                }
            }
        }
        return false
    }


    private fun loadPriceOfProductByProductIdAndListProperty(
        productId: Long,
        propertyIds: List<Long>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val additionalPriceResponse =
                    productRepository.getAdditionalPriceOfPropertyValuesAndProductId(
                        productId,
                        propertyIds
                    )
                if (additionalPriceResponse.isSuccessful) {
                    currentAdditionalPrice = additionalPriceResponse.body()!!
                    selectedProductPrice.postValue(
                        Converter.convertFromIntToCurrencyString(selectedProduct.value!!.price + currentAdditionalPrice)
                    )
                }
            } catch (e: Exception) {
                if (e is SocketTimeoutException) {
                    loadPriceOfProductByProductIdAndListProperty(productId, propertyIds)
                } else {
                    Log.d("DetailProductViewModel", e.stackTraceToString())
                }
            }
        }
    }

    private fun isValueOfTheSamePropertyExist(property: PropertyValue): Boolean {
        for (value in currentSelectedValues) {
            if (value.propertyId == property.propertyId) {
                return true
            }
        }
        return false
    }

    private fun removeExistedValue(property: PropertyValue) {
        for (value in currentSelectedValues) {
            if (value.propertyId == property.propertyId) {
                currentSelectedValues.remove(value)
                return
            }
        }
    }

    fun loadFilterProductRating(filter: String) {
        //fake load case "All" to see data
        selectedProductRatingFilter.value = selectedProductRating.value
        Log.d("ViewModel", "Filter case $filter")
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
        productRatingLoadingState.postValue(LoadingStatus.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val ratingResponseDTO = productRepository.loadProductRatingById(id)
                if (ratingResponseDTO.isSuccessful) {
                    _selectedProductRating.postValue(
                        ratingResponseDTO.body()!!.stream().map {
                            mapToProductRating(it!!)
                        }.collect(Collectors.toList()).toCollection(ArrayList())
                    )
                    productRatingLoadingState.postValue(LoadingStatus.Success)
                } else {
                    productRatingLoadingState.postValue(LoadingStatus.Fail)
                }
            } catch (e: Exception) {
                if (e is SocketTimeoutException) {
                    loadProductRatingById(id)
                } else {
                    Log.d("DetailProductViewModel", e.stackTraceToString())
                }
            }
        }
    }

    private fun loadProductsByKind(kindId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val productByKindIdResponseDTO = productRepository.loadProductByKindId(kindId)
                if (productByKindIdResponseDTO.isSuccessful) {
                    if (productByKindIdResponseDTO.body() != null) {
                        _sameKindProducts.postValue(
                            productByKindIdResponseDTO.body()!!.content.stream().map {
                                mapToHomeProduct(it!!)
                            }.collect(Collectors.toList()).toCollection(ArrayList())
                        )
                    }
                }
            } catch (e : Exception) {
                if (e is SocketTimeoutException) {
                    loadProductsByKind(kindId)
                } else {
                    Log.d("DetailProductViewModel", e.stackTraceToString())
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

    private fun loadSelectedProductColorPropertyById(id: Long, productQuantity: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val productStringResponseDTO = productRepository.loadProductColorPropertyById(id)
                if (productStringResponseDTO.isSuccessful) {
                    _selectedProductColorProperty.postValue(
                        productStringResponseDTO.body()!!.stream().map {
                            mapToProperty(it!!, productQuantity)
                        }.collect(Collectors.toList()).toCollection(ArrayList())
                    )
                }
            } catch (e: Exception) {
                if (e is SocketTimeoutException) {
                    loadSelectedProductColorPropertyById(id, productQuantity)
                } else {
                    Log.d("DetailProductViewModel", e.stackTraceToString())
                }
            }
        }
    }


    private fun mapToPropertyValue(
        productPropertyValueDTO: ProductPropertyValueDTO,
        propertyId: Long
    ): PropertyValue {
        return PropertyValue(
            productPropertyValueDTO.id,
            productPropertyValueDTO.name,
            propertyId,
            productPropertyValueDTO.value,
            false
        )
    }

    private fun mapToProperty(
        productPropertyDTO: ProductPropertyDTO,
        productQuantity: Int
    ): ProductDetailProperty {
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
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val productStringResponseDTO = productRepository.loadProductStringPropertyById(id)
                if (productStringResponseDTO.isSuccessful) {
                    _selectedProductStringProperty.postValue(
                        productStringResponseDTO.body()!!
                            .filter { productPropertyDTO -> productPropertyDTO.id != 0L }.stream()
                            .map {
                                mapToProperty(it!!, productQuantity)
                            }.collect(Collectors.toList()).toCollection(ArrayList())
                    )
                    val productPropertyDTO = productStringResponseDTO.body()!!
                        .find { productPropertyDTO -> productPropertyDTO.id == 0L }
                    if (productPropertyDTO != null) {
                        productNature = productPropertyDTO.valueDTOS[0].name
                        productDefaultId = productPropertyDTO.valueDTOS[0].id
                    }
                }
            } catch (e: Exception) {
                if (e is SocketTimeoutException) {
                    loadSelectedProductStringPropertyById(id, productQuantity)
                } else {
                    Log.d("DetailProductViewModel", e.stackTraceToString())
                }
            }
        }
    }

    private fun mapToProductDetail(productDetailDTO: ProductDetailDTO, id: Long): ProductDetail {
        return ProductDetail(
            id,
            productDetailDTO.name,
            productDetailDTO.price,
            productDetailDTO.purchase,
            true,
            productDetailDTO.description,
            productDetailDTO.quantity,
            productDetailDTO.kindName
        )
    }

    private fun loadSelectedProductById(id: Long) {
        productDetailLoadingState.value = LoadingStatus.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val productResponse = productRepository.loadProductById(id)
                if (productResponse.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        _selectedProduct.value = mapToProductDetail(productResponse.body()!!, id)
                        _selectedProduct.value?.availableQuantities.let {
                            _selectedProductQuantities.value = it
                        }
                        selectedProductPrice.postValue(
                            _selectedProduct.value?.price?.let {
                                Converter.convertFromIntToCurrencyString(
                                    it
                                )
                            }
                        )
                        loadProductsByKind(productResponse.body()!!.kindId)
                        _selectedProduct.value?.availableQuantities?.let {
                            loadSelectedProductStringPropertyById(
                                selectedProductId,
                                it
                            )
                        }
                        _selectedProduct.value?.availableQuantities?.let {
                            loadSelectedProductColorPropertyById(
                                selectedProductId,
                                it
                            )
                        }
                    }
                    productDetailLoadingState.postValue(LoadingStatus.Success)
                } else {
                    productDetailLoadingState.postValue(LoadingStatus.Fail)
                }
            } catch (e: Exception) {
                if (e is SocketTimeoutException) {
                    loadSelectedProductById(id)
                } else {
                    Log.d("DetailProductViewModel", e.stackTraceToString())
                }
            }
        }
    }

    private fun mapToImage(productImageDTO: ProductImageDTO): Image {
        return Image(productImageDTO.imageUrl)
    }

    private fun loadListImageByID(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val productImageResponse = productRepository.loadProductImageByProductId(id)
            if (productImageResponse.isSuccessful) {
                _selectedProductImages.postValue(
                    productImageResponse.body()!!.stream().map {
                        mapToImage(it)
                    }.collect(Collectors.toList())
                )
            }
        }
    }


}