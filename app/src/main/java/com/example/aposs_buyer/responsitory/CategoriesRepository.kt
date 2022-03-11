package com.example.aposs_buyer.responsitory

import com.example.aposs_buyer.model.dto.DetailCategoryDTO
import com.example.aposs_buyer.responsitory.webservice.CategoriesService
import com.example.aposs_buyer.responsitory.webservice.RetrofitInstance
import retrofit2.Response
import javax.inject.Inject

class CategoriesRepository @Inject constructor() {

    private val categoriesService: CategoriesService by lazy {
        RetrofitInstance.retrofit.create(CategoriesService::class.java)
    }

    suspend fun loadALlCategories(): Response<List<DetailCategoryDTO>> {
        return categoriesService.getAllCategory();
    }
}