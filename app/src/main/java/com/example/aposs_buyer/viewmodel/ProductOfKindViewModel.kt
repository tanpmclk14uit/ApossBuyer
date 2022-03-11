package com.example.aposs_buyer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aposs_buyer.model.HomeProduct
import com.example.aposs_buyer.responsitory.ProductRepository
import com.example.aposs_buyer.utils.Converter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.stream.Collectors
import javax.inject.Inject

@HiltViewModel
class ProductOfKindViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _selectedKindId = MutableLiveData<Long>()

    val selectedKindName = MutableLiveData<String>()

    private val _productsKind = MutableLiveData<MutableList<HomeProduct>>()
    val productKind: LiveData<MutableList<HomeProduct>> get() = _productsKind

    fun setSelectedKindIdAndName(id: Long, name: String) {
        selectedKindName.value = name
        _selectedKindId.value = id
    }

    fun setSelectedProductsKind() {
        loadProductsByKind(_selectedKindId.value!!)
    }

    private fun loadProductsByKind(kindId: Long) {
        viewModelScope.launch {
            val productByKindIdResponseDTO = productRepository.loadProductByKindId(kindId)
            if (productByKindIdResponseDTO.isSuccessful) {
                if (productByKindIdResponseDTO.body() != null) {
                    _productsKind.value =
                        productByKindIdResponseDTO.body()!!.content.stream().map {
                            Converter.convertProductDTOtoHomeProduct(
                                it
                            )
                        }.collect(Collectors.toList()).toCollection(ArrayList())
                }
            }
        }
    }
}