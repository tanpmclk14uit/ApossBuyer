package com.example.aposs_buyer.responsitory

import com.example.aposs_buyer.model.dto.*
import com.example.aposs_buyer.responsitory.webservice.ProductAPIService
import com.example.aposs_buyer.responsitory.webservice.RetrofitInstance
import retrofit2.Response
import javax.inject.Inject


class ProductRepository @Inject constructor() {

    val productService: ProductAPIService by lazy {
        RetrofitInstance.retrofit.create(ProductAPIService::class.java)
    }

    suspend fun loadRakingProduct(): Response<ProductResponseDTO> {
        return productService.getRakingProducts()
    }

    suspend fun loadProductById(id: Long): Response<ProductDetailDTO> {
        return productService.getProductById(id)
    }

    suspend fun loadProductImageByProductId(id: Long): Response<List<ProductImageDTO>> {
        return productService.getProductImagesById(id)
    }

    suspend fun loadProductByKindId(id: Long): Response<ProductResponseDTO> {
        return productService.getProductByKindId(id)
    }

    suspend fun loadProductStringPropertyById(id: Long): Response<List<ProductPropertyDTO>> {
        return productService.getProductPropertiesById(id, false)
    }

    suspend fun loadProductColorPropertyById(id: Long): Response<List<ProductPropertyDTO>> {
        return productService.getProductPropertiesById(id, true)
    }

    suspend fun loadProductRatingById(id: Long): Response<List<ProductRatingDTO>> {
        return productService.getProductRatingsById(id)
    }

    suspend fun getQuantityOfProductByProductIdAndPropertyValues(
        productId: Long,
        propertyValues: List<Long>
    ): Response<Int> {
        return productService.getProductQuantityByProductIdAndPropertyValue(
            productId,
            propertyValues
        )
    }

    suspend fun getAdditionalPriceOfPropertyValuesAndProductId(
        productId: Long,
        propertyValues: List<Long>
    ): Response<Int> {
        return productService.getAdditionalPriceOfPropertyValuesAndProductId(
            productId,
            propertyValues
        )
    }

    suspend fun getSetIdByByProductIdAndListProperty(
        productId: Long,
        propertyValues: List<Long>
    ): Response<Long> {
        return productService.getSetIdByByProductIdAndListProperty(
            productId,
            propertyValues
        )
    }


}