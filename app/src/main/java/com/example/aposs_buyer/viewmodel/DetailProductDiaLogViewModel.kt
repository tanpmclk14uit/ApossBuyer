package com.example.aposs_buyer.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.CartItem
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.ProductDetail
import com.example.aposs_buyer.model.ProductDetailProperty
import com.example.aposs_buyer.model.dto.CartDTO
import com.example.aposs_buyer.model.dto.OrderItemDTO
import com.example.aposs_buyer.model.dto.TokenDTO
import com.example.aposs_buyer.responsitory.AuthRepository
import com.example.aposs_buyer.responsitory.CartRepository
import com.example.aposs_buyer.responsitory.OrderRepository
import com.example.aposs_buyer.responsitory.database.AccountDatabase
import com.example.aposs_buyer.utils.LoadingStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class DetailProductDiaLogViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val authRepository: AuthRepository,
    @ApplicationContext val context: Context,
    private val orderRepository: OrderRepository,
) : ViewModel() {

    var tokenDTO: TokenDTO? = null

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

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

    private fun makeTheProductTypeCart(
        selectedProduct: ProductDetail,
        selectedProductImages: Image
    ) {
        val imageURL: String = selectedProductImages.imgURL
        val cartDto = CartDTO(
            id = -1,
            imageUrl = imageURL,
            name = selectedProduct.name,
            price = selectedProduct.price,
            quantity = 1,
            property = "",
            productId = selectedProduct.id,
            select = true
        )
        productTypeCartAmount.value = 1
        productTypeCart.value = cartDto
        _selectedProduct.value = selectedProduct
        _selectedProductImages.value = selectedProductImages
        _selectedProductQuantitiesDiaLog.value = selectedProduct.availableQuantities
    }


    fun setUpDialogData(viewModel: DetailProductViewModel) {
        makeTheProductTypeCart(
            viewModel.selectedProduct.value!!,
            viewModel.selectedProductImages.value!![0]
        )
        _selectedProductStringPropertyDiaLog.value = viewModel.selectedProductStringProperty.value
        _selectedProductColorPropertyDiaLog.value = viewModel.selectedProductColorProperty.value

        setSelectedProductMinValue()
        validatePropertyValue()

    }

    fun addToCart() {
        if (tokenDTO != null) {
            coroutineScope.launch {
                val createRepository = cartRepository.addNewCart(
                    tokenDTO!!.getFullAccessToken(),
                    productTypeCart.value!!
                )
                if (createRepository.isSuccessful) {
                    Log.d(
                        "DetailProductDiaLogViewModel",
                        "Add to cart: \n ${productTypeCart.value!!}"
                    )
                    return@launch
                } else {
                    if (createRepository.code() == 400) {
                        Log.d("cart", "Expire access token")
                        val accessTokenResponse =
                            authRepository.getAccessTokenFromRefreshToken(tokenDTO!!.refreshToken)
                        if (accessTokenResponse.code() == 200) {
                            tokenDTO!!.accessToken = accessTokenResponse.body()!!
                            AccountDatabase.getInstance(context).accountDao.updateAccessToken(
                                tokenDTO!!.accessToken
                            )
                            addToCart()
                        }
                    }
                }
            }
        }
    }

    private fun validatePropertyValue() {
        if (isSpecialPropertySelectMoreThanOneValue()) {
            propertyValue.value = "Special property select more than one value"
            isPropertyValueError.value = true
        } else {
            propertyValue.value = convertPropertyToString()
            productTypeCart.value!!.property = propertyValue.value!!
            isPropertyValueError.value = false
            if (propertyValue.value == "") {
                propertyValue.value = "Select property to continue"
                isPropertyValueError.value = true
            }

        }
    }

    fun isSpecialPropertyHaveNoneValueSelected(): Boolean {
        var totalChose: Int
        for (property in _selectedProductStringPropertyDiaLog.value!!) {
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
        for (property in _selectedProductColorPropertyDiaLog.value!!) {
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
        return false
    }

    private fun isSpecialPropertySelectMoreThanOneValue(): Boolean {
        var totalChose: Int
        for (property in _selectedProductStringPropertyDiaLog.value!!) {
            totalChose = 0
            for (value in property.values) {
                if (value.isChosen) {
                    totalChose++
                }
            }
            if (totalChose > 1) {
                return true
            }
        }
        for (property in _selectedProductColorPropertyDiaLog.value!!) {
            totalChose = 0
            for (value in property.values) {
                if (value.isChosen) {
                    totalChose++
                }
            }
            if (totalChose > 1) {
                return true
            }
        }
        return false
    }

    private fun setSelectedProductMinValue() {
        var quantity = selectedProductQuantitiesDiaLog.value!!
        val stringProperty = selectedProductStringPropertyDiaLog.value
        val colorProperty = selectedProductColorPropertyDiaLog.value

        if (stringProperty != null && stringProperty.isNotEmpty()) {
            for (property in stringProperty) {
                if (property.valueCountSummarize !=0 && property.valueCountSummarize < quantity) {
                    quantity = property.valueCountSummarize
                }
            }
        }
        if (colorProperty != null && colorProperty.isNotEmpty()) {
            for (property in colorProperty) {
                if (property.valueCountSummarize !=0 && property.valueCountSummarize < quantity) {
                    quantity = property.valueCountSummarize
                }
            }
        }
        selectedProductQuantitiesDiaLog.value = quantity
        validatePropertyValue()
    }

    private fun convertPropertyToString(): String {
        if (selectedProductQuantitiesDiaLog.value == selectedProduct.value!!.availableQuantities) {
            return " "
        }
        var sampleProperty = ""
        for (property in selectedProductStringPropertyDiaLog.value!!) {
            for (value in property.values) {
                if (value.isChosen) {
                    sampleProperty += property.name
                    sampleProperty += ": "
                    sampleProperty += value.name
                    sampleProperty += ", "
                }
            }
        }
        for (property in selectedProductColorPropertyDiaLog.value!!) {

            for (value in property.values) {
                if (value.isChosen) {
                    sampleProperty += property.name
                    sampleProperty += ": "
                    sampleProperty += value.name
                    sampleProperty += ", "
                }
            }
        }
        return sampleProperty
    }

    fun notifySelectedStringPropertyChange(propertyId: Long) {
       // load property
    }

    fun notifySelectedColorPropertyChange(propertyId: Long) {
        // load property
    }

    var loadingStatus = MutableLiveData<LoadingStatus>()

    private fun toCartItem(cartDTO: CartDTO): CartItem {
        val image = Image(cartDTO.imageUrl)
        return CartItem(
            id = cartDTO.id,
            image = image,
            name = cartDTO.name,
            price = cartDTO.price,
            amount = cartDTO.quantity,
            property = cartDTO.property,
            isChoose = cartDTO.select,
            product = cartDTO.productId,
        )
    }

    private fun convertToOrderItemDTO(orderItem: CartItem): OrderItemDTO {
        return OrderItemDTO(
            id = 0,
            name = orderItem.name,
            property = orderItem.property,
            price = orderItem.price,
            imageUrl = orderItem.image.imgURL,
            quantity = orderItem.amount,
            product = orderItem.product,
        )
    }
}