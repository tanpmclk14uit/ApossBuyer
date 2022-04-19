package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aposs_buyer.model.Category
import com.example.aposs_buyer.model.HomeProduct
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.RankingProduct
import com.example.aposs_buyer.model.dto.ProductDTO
import com.example.aposs_buyer.model.dto.ProductResponseDTO
import com.example.aposs_buyer.responsitory.CategoriesRepository
import com.example.aposs_buyer.responsitory.ProductRepository
import com.example.aposs_buyer.utils.Converter
import com.example.aposs_buyer.utils.LoadingStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.stream.Collectors
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val categoriesRepository: CategoriesRepository
) : ViewModel() {

    //category data
    private var _categories = MutableLiveData<ArrayList<Category>>()
    val categories: LiveData<ArrayList<Category>> get() = _categories

    private var _displayCategory = MutableLiveData<Category>()
    val displayCategory: LiveData<Category> get() = _displayCategory

    var displayCategoryPurchase = MutableLiveData<String>()
    var displayCategoryProducts = MutableLiveData<String>()

    //ranking data
    private var _rankingProducts = MutableLiveData<List<RankingProduct>>()
    val rankingProducts: LiveData<List<RankingProduct>> get() = _rankingProducts


    // products data
    private var _products = MutableLiveData<List<HomeProduct>>()
    val products: LiveData<List<HomeProduct>> get() = _products

    private var currentPage = 1
    private var isLastPage = false
    private var _status = MutableLiveData<LoadingStatus>()
    val status: LiveData<LoadingStatus> get() = _status

    private var _categoryStatus = MutableLiveData<LoadingStatus>()

    init {
        loadAllCategories()
        if (_categoryStatus.value == LoadingStatus.Success) {
            setUpDisplayCategory(0)
        }
        _products.value = ArrayList()
        _status.value = LoadingStatus.Success
        loadProducts()
        loadRankingData()
    }

    fun loadProducts() {
        if (!isLastPage && _status.value != LoadingStatus.Loading) {
            _status.value = LoadingStatus.Loading
            viewModelScope.launch {
                val getProductDeferred =
                    productRepository.productService.getProductsAsync(currentPage)
                try {
                    val productResponseDTO: ProductResponseDTO = getProductDeferred.await()
                    val productsInCurrentPage = productResponseDTO.content.stream()
                        .map { productDTO -> Converter.convertProductDTOtoHomeProduct(productDTO) }
                        .collect(
                            Collectors.toList()
                        )
                    _products.value =
                        Converter.concatenate(_products.value!!, productsInCurrentPage)
                    if (productResponseDTO.last) {
                        isLastPage = true
                    } else {
                        currentPage++
                    }
                    _status.value = LoadingStatus.Success
                } catch (e: Exception) {
                    Log.d("exception", e.toString())
                    _status.value = LoadingStatus.Fail
                }
            }
        }
    }

    private fun loadAllCategories() {
        _categoryStatus.value = LoadingStatus.Loading
        viewModelScope.launch {
            try {
                val allCategoriesResponse = categoriesRepository.loadALlCategories()
                if (allCategoriesResponse.isSuccessful) {
                    _categories.value =
                        ArrayList(allCategoriesResponse.body()!!.stream().map {
                            Converter.convertDetailCategoryDTOToCategory(it)
                        }.collect(Collectors.toList()))
                    _categoryStatus.value = LoadingStatus.Success
                } else {
                    _categoryStatus.value = LoadingStatus.Fail
                }

            } catch (e: java.lang.Exception) {
                Log.e("exception", e.toString())
                _categoryStatus.value = LoadingStatus.Fail
            }
        }
    }

    private fun loadRankingData() {
        viewModelScope.launch {
            val rakingProductResponse = productRepository.loadRakingProduct()
            if (rakingProductResponse.isSuccessful) {
                _rankingProducts.value = rakingProductResponse.body()!!.content.map { productDTO ->
                    mapProductToRankingProduct(productDTO)
                }.toList()
            } else {
                _rankingProducts.value = mutableListOf()
            }
        }
    }

    private fun mapProductToRankingProduct(productDTO: ProductDTO): RankingProduct {
        return RankingProduct(
            id = productDTO.id,
            image = Image(productDTO.image),
            name = productDTO.name,
            price = productDTO.price,
            rating = productDTO.rating.toFloat(),
            totalPurchase = productDTO.purchased
        )
    }

    fun setUpDisplayCategory(currentPosition: Int) {
        val currentCategory: Category = _categories.value!![currentPosition]
        _displayCategory.value = currentCategory
        displayCategoryPurchase.value = String.format("%s purchased", currentCategory.totalPurchase)
        displayCategoryProducts.value = String.format("%s products", currentCategory.totalProduct)
    }
}