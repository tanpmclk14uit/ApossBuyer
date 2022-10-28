package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aposs_buyer.model.DetailCategory
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.dto.DetailCategoryDTO
import com.example.aposs_buyer.responsitory.CategoriesRepository
import com.example.aposs_buyer.utils.LoadingStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.util.stream.Collectors
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(private val categoriesRepository: CategoriesRepository) :
    ViewModel() {

    private val _lstCategory = MutableLiveData<MutableList<DetailCategory>>()
    val lstCategory: LiveData<MutableList<DetailCategory>> get() = _lstCategory

    val status = MutableLiveData<LoadingStatus>()

    init {
        loadAllCategories()
    }

    private fun loadAllCategories() {
        status.value = LoadingStatus.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val allCategoriesResponse = categoriesRepository.loadALlCategories()
                if (allCategoriesResponse.isSuccessful) {
                    _lstCategory.postValue(
                        allCategoriesResponse.body()?.stream()?.map { detailCategoryDTO ->
                            convertFromDetailCategoryDTOToDetailCategory(detailCategoryDTO)
                        }?.collect(Collectors.toList()))
                    status.postValue(LoadingStatus.Success)
                } else {
                    status.postValue(LoadingStatus.Fail)
                }

            } catch (e: Exception) {
                if (e is SocketTimeoutException)
                {
                    loadAllCategories()
                } else {
                    Log.e("exception", e.toString())
                    status.postValue(LoadingStatus.Fail)
                }
            }
        }
    }

    private fun convertFromDetailCategoryDTOToDetailCategory(detailCategoryDTO: DetailCategoryDTO): DetailCategory {
        val images: MutableList<Image> = mutableListOf()
        for (item in detailCategoryDTO.images) {
            images.add(Image(item))
        }
        return DetailCategory(
            id = detailCategoryDTO.id,
            name = detailCategoryDTO.name,
            totalProduct = detailCategoryDTO.totalProducts,
            totalPurchase = detailCategoryDTO.totalPurchases,
            rating = detailCategoryDTO.rating,
            images = images
        )
    }

}