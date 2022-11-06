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
    var isSortByPurchased = MutableLiveData<Boolean>()
    var isSortByPrice = MutableLiveData<Boolean>()

    init {
        curentKeyWord.value = "";
        listForDisplay.value = mutableListOf();
        isSortByPrice.value = false
        isSortByPurchased.value = false
        loadListForDisplay();
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
            isSortByPurchased.value!! -> {
               sortBy = "purchased"
                sortDir = "desc"
            }
            isSortByPrice.value!! -> {
                sortBy = "price"
                sortDir = "asc"
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



    fun sortByPrice(){
        isSortByPrice.value = !isSortByPrice.value!!
        if (isSortByPrice.value!!) {
            isSortByPurchased.value = false
            isLastPage = false
            currentPage = 1
            listForDisplay.value = mutableListOf()
            loadListForDisplay()
        } else {
            isLastPage = false
            currentPage = 1
            listForDisplay.value = mutableListOf()
            loadListForDisplay()
        }
    }


    fun sortByPurchased(){
        isSortByPurchased.value = !isSortByPurchased.value!!
        if (isSortByPurchased.value!!) {
            isSortByPrice.value = false
            isLastPage = false
            currentPage = 1
            listForDisplay.value = mutableListOf()
            loadListForDisplay()
        } else
        {
            isLastPage = false
            currentPage = 1
            listForDisplay.value = mutableListOf()
            loadListForDisplay()
        }
    }
}