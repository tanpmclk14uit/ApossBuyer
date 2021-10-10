package com.example.aposs_buyer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.Category
import com.example.aposs_buyer.model.DetailCategory
import com.example.aposs_buyer.model.Image
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(): ViewModel() {

    private val _lstCategory = MutableLiveData<MutableList<DetailCategory>>()
    val lstCategory: LiveData<MutableList<DetailCategory>> get() = _lstCategory

    init {
        _lstCategory.value = loadCategory()
    }

    fun loadCategory(): MutableList<DetailCategory>
    {
        val imgURl1 = "https://i.pinimg.com/originals/58/f3/0a/58f30a4b7fc165af50e7052725b8bc09.jpg"
        val imgURL2 =
            "https://th.bing.com/th/id/R.380e118b26177f38ab0036ecb5014a0b?rik=coxm1X%2f%2bYh9klw&pid=ImgRaw&r=0"
        val imgURL3 = "https://www.hartehanks.com/wp-content/uploads/2021/09/MarTech.jpg"
        val imgCategory1 = Image(imgURl1)
        val imgCategory2 = Image(imgURL2)
        val imgCategory3 = Image(imgURL3)
        val sampleCategories : MutableList<DetailCategory> = mutableListOf()
        sampleCategories.add(DetailCategory(1, "Sport Clothes", 1147, 2172, 3.5f, mutableListOf<Image>(imgCategory1, imgCategory2, imgCategory3)))
        sampleCategories.add(DetailCategory(2, "Healthy Food", 2194, 1258, 4f, mutableListOf<Image>(imgCategory1, imgCategory2, imgCategory3)))
        sampleCategories.add(DetailCategory(3, "Laptop", 3153, 1917, 4.5f, mutableListOf<Image>(imgCategory1, imgCategory2, imgCategory3)))
        return sampleCategories
    }

}