package com.example.aposs_buyer.responsitory

import com.example.aposs_buyer.model.dto.KindDTO
import com.example.aposs_buyer.responsitory.webservice.CategoryService
import com.example.aposs_buyer.responsitory.webservice.RetrofitInstance
import retrofit2.Response
import javax.inject.Inject

class CategoryRepository @Inject constructor() {

    private val categoryService: CategoryService by lazy {
        RetrofitInstance.retrofit.create(CategoryService::class.java)
    }

    suspend fun loadAllKindOfCategoryByCategoryId(categoryId: Long): Response<List<KindDTO>> {
        return categoryService.getAllKindOfCategoryByCategoryId(categoryId)
    }

}