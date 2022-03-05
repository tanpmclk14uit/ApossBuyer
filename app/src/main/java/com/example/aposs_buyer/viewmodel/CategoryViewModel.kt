package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.DetailCategory
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.dto.DetailCategoryDTO
import com.example.aposs_buyer.responsitory.CategoryRepository
import com.example.aposs_buyer.utils.LoadingStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.stream.Collectors
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryRepository: CategoryRepository): ViewModel() {

    private val _lstCategory = MutableLiveData<MutableList<DetailCategory>>()
    val lstCategory: LiveData<MutableList<DetailCategory>> get() = _lstCategory

    private val _status = MutableLiveData<LoadingStatus>()

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        loadCategory()
    }

    private fun loadCategory()
    {
        _status.value = LoadingStatus.Loading
        coroutineScope.launch {
            val lstDetailCategoryDTO = categoryRepository.categoryService.getAllCategory()
            try {
                _lstCategory.value = lstDetailCategoryDTO.stream().map{
                    detailCategoryDTO -> convertFromDetailCategoryDTOToDetailCategory(detailCategoryDTO)
                }.collect(Collectors.toList())
                _status.value = LoadingStatus.Success
            }
            catch (e: Exception){
                Log.e("exception", e.toString())
                _status.value = LoadingStatus.Fail
            }
        }
    }

    private fun convertFromDetailCategoryDTOToDetailCategory(detailCategoryDTO: DetailCategoryDTO): DetailCategory
    {
        val images: MutableList<Image> = mutableListOf()
        for(item in detailCategoryDTO.images)
        {
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