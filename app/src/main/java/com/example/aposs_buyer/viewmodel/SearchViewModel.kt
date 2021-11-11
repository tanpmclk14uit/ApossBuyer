package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aposs_buyer.model.HomeProduct
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.dto.ProductResponseDTO
import com.example.aposs_buyer.responsitory.ProductRepository
import com.example.aposs_buyer.utils.ProductsStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.stream.Collectors
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val productRepository: ProductRepository): ViewModel() {


    val listForDisplay =  MutableLiveData<MutableList<HomeProduct>>()
    private var isLastPage = false
    val curentKeyWord = MutableLiveData<String>()
    private var currentPage = 1;

    private var _status = MutableLiveData<ProductsStatus>()
    val status: LiveData<ProductsStatus> get()= _status

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        curentKeyWord.value = "";
        listForDisplay.value = mutableListOf();
        loadListForDisplay();
    }

    fun addToFavorite(product: HomeProduct) {
        // add to favorite in db
    }

    fun removeFromFavorite(product: HomeProduct) {
        //remove from favorite in db
    }

    fun loadListForDisplay()
    {
        if (!isLastPage){
            _status.value = ProductsStatus.Loading;
            coroutineScope.launch {
                val lstProductDeferred = productRepository.productService.getProductsAsyncByKeyword(curentKeyWord.value!!, currentPage)
                try {
                    val productResponseDTO: ProductResponseDTO = lstProductDeferred.await()
                    val lstResultCurrentPage = productResponseDTO.content.stream().map {
                        HomeViewModel.Converter.convertToHomeProduct(it)
                    }.collect(Collectors.toList())
                    listForDisplay.value = HomeViewModel.Converter.concatenateMutable(listForDisplay.value!!,lstResultCurrentPage)
                    _status.value = ProductsStatus.Success
                    if (productResponseDTO.last) {
                        isLastPage = true
                    } else {
                        currentPage++
                    }
                }
                catch (e: Exception)
                {
                    Log.e("Exception", e.toString())
                    _status.value = ProductsStatus.Fail
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onSearchTextChange() {
        isLastPage = false
        currentPage = 1
        listForDisplay.value = mutableListOf()
        loadListForDisplay()
    }

    fun sortByRating (arr: MutableList<HomeProduct>, low: Int, high: Int): Int {
        val pivot = arr[high]
        var i = low - 1
        for (j in low until high) {
            if (arr[j].rating < pivot.rating) {
                i++
                val temp = arr[i]
                arr[i] = arr[j]
                arr[j] = temp
            }
        }
        val temp = arr[i + 1]
        arr[i + 1] = arr[high]
        arr[high] = temp
        return i + 1
    }

    fun sortByPrice(arr: MutableList<HomeProduct>, low: Int, high: Int): Int {
        val pivot = arr[high]
        var i = low - 1
        for (j in low until high) {
            if (arr[j].price < pivot.price) {
                i++
                val temp = arr[i]
                arr[i] = arr[j]
                arr[j] = temp
            }
        }
        val temp = arr[i + 1]
        arr[i + 1] = arr[high]
        arr[high] = temp
        return i + 1
    }

    fun sortByPurchased(arr: MutableList<HomeProduct>, low: Int, high: Int): Int {

    }
}