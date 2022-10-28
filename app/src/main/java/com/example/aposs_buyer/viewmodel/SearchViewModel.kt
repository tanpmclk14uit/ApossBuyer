package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aposs_buyer.model.HomeProduct
import com.example.aposs_buyer.model.dto.ProductResponseDTO
import com.example.aposs_buyer.responsitory.ProductRepository
import com.example.aposs_buyer.utils.Converter
import com.example.aposs_buyer.utils.LoadingStatus
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

    private var _status = MutableLiveData<LoadingStatus>()
    val status: LiveData<LoadingStatus> get()= _status

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
            setSort()
            _status.value = LoadingStatus.Loading;
            viewModelScope.launch(Dispatchers.IO) {
                val lstProductDeferred = productRepository.productService.getProductsByKeywordAsync(curentKeyWord.value!!, currentPage, sortBy, sortDir)
                try {
                    val productResponseDTO: ProductResponseDTO = lstProductDeferred.await()
                    val lstResultCurrentPage = productResponseDTO.content.stream().map {
                        Converter.convertProductDTOtoHomeProduct(it)
                    }.collect(Collectors.toList())
                    listForDisplay.postValue(mutableListOf())
                        listForDisplay.postValue(Converter.concatenateMutable(
                            listForDisplay.value!!,
                            lstResultCurrentPage) )
                    _status.postValue(LoadingStatus.Success)
                    if (productResponseDTO.last) {
                        isLastPage = true
                    } else {
                        currentPage++
                    }
                }
                catch (e: Exception)
                {
                    Log.e("Exception", e.toString())
                    _status.postValue(LoadingStatus.Fail)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private var sortBy: String = "id"
    private var sortDir: String = "asc"
    private fun setSort()
    {
        when {
            isSortByPurchased -> {
               sortBy = "purchased"
                sortDir = "desc"
            }
            isSortByPrice -> {
                sortBy = "price"
                sortDir = "asc"
            }
            isSortByRating -> {
                sortBy = "rating"
                sortDir = "desc"
            }
            else ->{
                sortBy = "id"
                sortDir= "asc"
            }
        }
    }

    fun onSearchTextChange() {
        isLastPage = false
        currentPage = 1
        listForDisplay.value = mutableListOf()
        loadListForDisplay()
    }

    private var isSortByRating =false;
    fun sortByRating (){
        if (!isSortByRating) {
            isSortByRating = true
            isSortByPrice = false
            isSortByPurchased = false
            isLastPage = false
            currentPage = 1
            listForDisplay.value = mutableListOf()
            loadListForDisplay()
        } else {
            isSortByRating = false
            isLastPage = false
            currentPage = 1
            listForDisplay.value = mutableListOf()
            loadListForDisplay()
        }
    }

    private var isSortByPrice =false
    fun sortByPrice(){
        if (!isSortByPrice) {
            isSortByPrice = true
            isSortByPurchased = false
            isSortByRating = false
            isLastPage = false
            currentPage = 1
            listForDisplay.value = mutableListOf()
            loadListForDisplay()
        } else {
            isSortByPrice = false
            isLastPage = false
            currentPage = 1
            listForDisplay.value = mutableListOf()
            loadListForDisplay()
        }
    }

    private var isSortByPurchased =false
    fun sortByPurchased(){
        if (!isSortByPurchased) {
            isSortByPurchased = true
            isSortByRating = false
            isSortByPrice = false
            isLastPage = false
            currentPage = 1
            listForDisplay.value = mutableListOf()
            loadListForDisplay()
        } else
        {
            isSortByPurchased = false
            isLastPage = false
            currentPage = 1
            listForDisplay.value = mutableListOf()
            loadListForDisplay()
        }
    }
}