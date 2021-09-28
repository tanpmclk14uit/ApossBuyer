package com.example.aposs_buyer.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.Category
import com.example.aposs_buyer.model.ImageCategory

class HomeViewModel : ViewModel() {

    var category= MutableLiveData<Category>()

    var displayCategoryPurchase: String =""

    var displayCategoryProducts: String=""
    init {

        val imgURL: String =
            "https://i.pinimg.com/originals/58/f3/0a/58f30a4b7fc165af50e7052725b8bc09.jpg"
        val imgCategory = ImageCategory(imgURL, 1)
        category.value = Category(1, "Sport", 5147, 1325, 3.5f, imgCategory)
        displayCategoryPurchase = String.format("%s purchased", category.value!!.totalPurchase)
        displayCategoryProducts = String.format("%s products", category.value!!.totalProduct)

    }

}