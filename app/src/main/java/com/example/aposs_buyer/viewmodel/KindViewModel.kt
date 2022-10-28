package com.example.aposs_buyer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aposs_buyer.model.HomeProduct
import com.example.aposs_buyer.responsitory.ProductRepository
import com.example.aposs_buyer.utils.Converter
import com.example.aposs_buyer.utils.LoadingStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.stream.Collectors
import javax.inject.Inject

@HiltViewModel
class KindViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _selectedKindId = MutableLiveData<Long>()

    val selectedKindName = MutableLiveData<String>()

    private val _productsKind = MutableLiveData<MutableList<HomeProduct>>()
    val productKind: LiveData<MutableList<HomeProduct>> get() = _productsKind

    val status = MutableLiveData<LoadingStatus>()

    fun setSelectedKindIdAndName(id: Long, name: String) {
        selectedKindName.value = name
        _selectedKindId.value = id
    }

    fun setSelectedProductKind() {
        loadProductsByKind(_selectedKindId.value!!)
    }

    private fun loadProductsByKind(kindId: Long) {
        status.value = LoadingStatus.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val productByKindIdResponseDTO = productRepository.loadProductByKindId(kindId)
            if (productByKindIdResponseDTO.isSuccessful) {
                if (productByKindIdResponseDTO.body() != null) {
                    _productsKind.postValue(
                        productByKindIdResponseDTO.body()!!.content.stream().map {
                            Converter.convertProductDTOtoHomeProduct(
                                it
                            )
                        }.collect(Collectors.toList()).toCollection(ArrayList()))
                    status.postValue(LoadingStatus.Success)
                }
            } else {
                status.postValue(LoadingStatus.Fail)
            }
        }
    }
}