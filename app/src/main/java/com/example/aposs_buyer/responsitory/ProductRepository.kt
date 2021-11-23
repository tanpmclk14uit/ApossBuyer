package com.example.aposs_buyer.responsitory

import com.example.aposs_buyer.model.dto.*
import com.example.aposs_buyer.responsitory.webservice.ProductAPIService
import com.example.aposs_buyer.responsitory.webservice.RetrofitInstance
import retrofit2.Response
import javax.inject.Inject


class ProductRepository @Inject constructor(){

    val productService: ProductAPIService by lazy {
        RetrofitInstance.retrofit.create(ProductAPIService::class.java)
    }
    suspend fun loadProductById(id: Long): Response<ProductDetailDTO>{
        return productService.getProductById(id)
    }

    suspend fun loadProductImageByProductId(id: Long): Response<List<ProductImageDTO>>{
        return productService.getProductImagesById(id)
    }

    suspend fun loadProductByKindId(id: Long): Response<ProductResponseDTO>{
        return productService.getProductByKindId(id)
    }

    suspend fun loadProductStringPropertyById(id: Long): Response<List<ProductPropertyDTO>>{
        return productService.getProductPropertiesById(id, false)
    }

    suspend fun loadProductColorPropertyById(id: Long): Response<List<ProductPropertyDTO>>{
        return productService.getProductPropertiesById(id, true)
    }

    suspend fun loadProductRatingById(id: Long): Response<List<ProductRatingDTO>>{
        return productService.getProductRatingsById(id)
    }


}