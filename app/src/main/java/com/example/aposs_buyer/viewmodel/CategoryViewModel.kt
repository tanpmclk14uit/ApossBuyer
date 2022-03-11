package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aposs_buyer.model.Kind
import com.example.aposs_buyer.responsitory.CategoryRepository
import com.example.aposs_buyer.utils.Converter
import com.example.aposs_buyer.utils.LoadingStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.stream.Collectors
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryRepository: CategoryRepository) :
    ViewModel() {

    private val _status = MutableLiveData<LoadingStatus>()

    private val selectedCategoryId = MutableLiveData<Long>()

    private val _listKind = MutableLiveData<MutableList<Kind>>()
    val listKind: LiveData<MutableList<Kind>> get() = _listKind

    val selectedCategoryName = MutableLiveData<String>()

    fun setSelectedCategory() {
        loadAllKindsOfSelectedCategoryById(selectedCategoryId.value!!)
    }

    fun setSelectedKindIdAndName(selectedCategory: Long, selectedName: String) {
        selectedCategoryId.value = selectedCategory
        selectedCategoryName.value = selectedName
    }

    private fun loadAllKindsOfSelectedCategoryById(selectedCategory: Long) {
        _status.value = LoadingStatus.Loading
        viewModelScope.launch {
            try {
                val allKindsOfSelectedCategoryResponse =
                    categoryRepository.loadAllKindOfCategoryByCategoryId(selectedCategory)
                if (allKindsOfSelectedCategoryResponse.isSuccessful) {
                    _listKind.value = allKindsOfSelectedCategoryResponse.body()!!.stream().map {
                        Converter.convertFromKindDTOToKind(it)
                    }.collect(Collectors.toList())
                    _status.value = LoadingStatus.Success
                } else {
                    _status.value = LoadingStatus.Fail
                }
            } catch (e: Exception) {
                Log.e("exception", e.toString())
                _status.value = LoadingStatus.Fail
            }
        }
    }

}