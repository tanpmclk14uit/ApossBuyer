package com.example.aposs_buyer.viewmodel

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.Category
import com.example.aposs_buyer.model.ImageCategory
import com.example.aposs_buyer.uicontroler.activity.MainActivity
import java.util.logging.Handler

class HomeViewModel : ViewModel() {

    private var _categories = MutableLiveData<ArrayList<Category>>()
    val categories: LiveData<ArrayList<Category>> get() = _categories

    private var _displayCategory= MutableLiveData<Category>()
    val displayCategory: LiveData<Category> get() = _displayCategory

    var displayCategoryPurchase= MutableLiveData<String>()

    var displayCategoryProducts= MutableLiveData<String>()

    init {
        _categories.value = loadCategoriesData()
        setUpDisplayCategory(0)
    }
    private fun loadCategoriesData(): ArrayList<Category>{
        val imgURl1 = "https://i.pinimg.com/originals/58/f3/0a/58f30a4b7fc165af50e7052725b8bc09.jpg"
        val imgURL2 ="https://th.bing.com/th/id/R.380e118b26177f38ab0036ecb5014a0b?rik=coxm1X%2f%2bYh9klw&pid=ImgRaw&r=0"
        val imgURL3 ="https://www.hartehanks.com/wp-content/uploads/2021/09/MarTech.jpg"
        val imgCategory1 = ImageCategory(imgURl1, 1)
        val imgCategory2 = ImageCategory(imgURL2, 1)
        val imgCategory3 = ImageCategory(imgURL3, 1)
        val sampleCategories = ArrayList<Category>()
        sampleCategories.add(Category(1, "Sport Clothes", 1147, 2172, 3.5f, imgCategory1))
        sampleCategories.add(Category(2, "Healthy Food", 2194, 1258, 4f, imgCategory2))
        sampleCategories.add(Category(3, "Laptop", 3153, 1917, 4.5f, imgCategory3))
        return sampleCategories
    }
     fun setUpDisplayCategory(currentPosition: Int){
         val currentCategory: Category = _categories.value!![currentPosition]
        _displayCategory.value = currentCategory
        displayCategoryPurchase.value = String.format("%s purchased", currentCategory.totalPurchase)
        displayCategoryProducts.value = String.format("%s products", currentCategory.totalProduct)

    }
}