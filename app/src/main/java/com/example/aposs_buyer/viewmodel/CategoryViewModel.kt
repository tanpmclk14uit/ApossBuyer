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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.util.stream.Collectors
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryRepository: CategoryRepository) :
    ViewModel() {

    val status = MutableLiveData<LoadingStatus>()

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
        status.value = LoadingStatus.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val allKindsOfSelectedCategoryResponse =
                    categoryRepository.loadAllKindOfCategoryByCategoryId(selectedCategory)
                if (allKindsOfSelectedCategoryResponse.isSuccessful) {
                    _listKind.postValue(allKindsOfSelectedCategoryResponse.body()!!.stream().map {
                        Converter.convertFromKindDTOToKind(it)
                    }.collect(Collectors.toList()))
                    status.postValue(LoadingStatus.Success)
                } else {
                    status.postValue(LoadingStatus.Fail)
                }
            } catch (e: Exception) {
                if (e is SocketTimeoutException) {
                    loadAllKindsOfSelectedCategoryById(selectedCategory)
                } else {
                    Log.e("exception", e.toString())
                    status.postValue(LoadingStatus.Fail)
                }
            }
        }
    }

}