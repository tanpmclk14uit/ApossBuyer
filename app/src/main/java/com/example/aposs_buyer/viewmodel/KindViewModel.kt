package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.HomeProduct
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.Kind
import com.example.aposs_buyer.model.dto.KindDTO
import com.example.aposs_buyer.responsitory.KindRepository
import com.example.aposs_buyer.utils.KindStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.stream.Collectors
import javax.inject.Inject

@HiltViewModel
class KindViewModel @Inject constructor(private val kindRepository: KindRepository): ViewModel() {

    private val _status = MutableLiveData<KindStatus>()

    private val selectedCategoryId = MutableLiveData<Long>()

    private val _listKind = MutableLiveData<MutableList<Kind>>()
    val listKind : LiveData<MutableList<Kind>> get() = _listKind

    val selectedCategoryName = MutableLiveData<String>()

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun setSelectedCategory()
    {
        loadProduct(selectedCategoryId.value!!)
    }

    fun getSelectedCategory(): MutableList<Kind>
    {
        return _listKind.value!!
    }

    fun setSelectedKindIdAndName(selectedCategory: Long, selectedName: String)
    {
        selectedCategoryId.value = selectedCategory
        selectedCategoryName.value = selectedName
    }

    private fun loadProduct(selectedCategory: Long)
    {
        _status.value = KindStatus.Loading
        coroutineScope.launch {
            val listKindDTO = kindRepository.kindService.getAllKind()
            try {
                val listSelectedKindDTO: MutableList<KindDTO> = mutableListOf()
                for (item in listKindDTO) {
                    if (item.category == selectedCategory) {
                        listSelectedKindDTO.add(item)
                    }
                }
                _listKind.value = listSelectedKindDTO.stream().map {
                    Converter.convertFromKindDTOToKind(it)
                }.collect(Collectors.toList())
                _status.value = KindStatus.Success
            }
            catch (e: Exception)
            {
                Log.e("exception", e.toString())
                _status.value = KindStatus.Fail
            }
        }
    }
    object Converter {
        fun convertFromKindDTOToKind(kindDTO: KindDTO): Kind {
            val listHomeProduct = kindDTO.products.stream().map {
                productDTO -> HomeViewModel.Converter.convertToHomeProduct(productDTO)
            }.collect(Collectors.toList())
            return Kind(
                id = kindDTO.id,
                name = kindDTO.name,
                totalPurchase = kindDTO.totalPurchases,
                totalProduct = kindDTO.totalProducts,
                rating = kindDTO.rating,
                image = Image(kindDTO.image),
                Products = listHomeProduct,
                category = kindDTO.category
            )
        }
    }

}