package com.example.aposs_buyer.utils

import com.example.aposs_buyer.model.Category
import com.example.aposs_buyer.model.HomeProduct
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.Kind
import com.example.aposs_buyer.model.dto.DetailCategoryDTO
import com.example.aposs_buyer.model.dto.KindDTO
import com.example.aposs_buyer.model.dto.ProductDTO
import java.text.DecimalFormat
import java.util.stream.Collectors

object Converter {
    fun convertProductDTOtoHomeProduct(productDTO: ProductDTO): HomeProduct {
        return HomeProduct(
            id = productDTO.id,
            image = Image(productDTO.image),
            name = productDTO.name,
            price = productDTO.price,
            rating = productDTO.rating.toFloat(),
            purchased = productDTO.purchased
        )
    }

    fun convertDetailCategoryDTOToCategory(categoryDTO: DetailCategoryDTO): Category {
        return Category(
            id = categoryDTO.id,
            name = categoryDTO.name,
            totalProduct = categoryDTO.totalProducts,
            totalPurchase = categoryDTO.totalPurchases,
            rating = categoryDTO.rating,
            mainImage = Image(categoryDTO.images[0])
        )
    }

    fun <T> concatenate(vararg lists: List<T>): List<T> {
        val result: MutableList<T> = ArrayList()
        for (list in lists) {
            result.addAll(list)
        }
        return result
    }

    fun <T> concatenateMutable(vararg lists: MutableList<T>): MutableList<T> {
        val result: MutableList<T> = ArrayList()
        for (list in lists) {
            result.addAll(list)
        }
        return result
    }

    fun convertFromKindDTOToKind(kindDTO: KindDTO): Kind {
        val listHomeProduct = kindDTO.products.stream().map { productDTO ->
            convertProductDTOtoHomeProduct(productDTO)
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

    fun convertFromIntToCurrencyString(money: Int): String {
        val formatter = DecimalFormat("#,###")
        val formattedNumber: String = formatter.format(money)
        return "$formattedNumber VNĐ"
    }
}