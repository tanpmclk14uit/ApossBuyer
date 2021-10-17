package com.example.aposs_buyer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.DetailCategory
import com.example.aposs_buyer.model.Image
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AboutUsViewModel @Inject constructor():  ViewModel() {

    private val _listImage = MutableLiveData<ArrayList<Image>>()
    val listImage: LiveData<ArrayList<Image>> get() = _listImage

    init {
        _listImage.value = loadCompanyInformation()
    }

    private fun loadCompanyInformation(): ArrayList<Image>
    {
        val imgURl1 = "https://i.pinimg.com/originals/58/f3/0a/58f30a4b7fc165af50e7052725b8bc09.jpg"
        val imgURL2 =
            "https://th.bing.com/th/id/R.380e118b26177f38ab0036ecb5014a0b?rik=coxm1X%2f%2bYh9klw&pid=ImgRaw&r=0"
        val imgURL3 = "https://www.hartehanks.com/wp-content/uploads/2021/09/MarTech.jpg"
        val imgCategory1 = Image(imgURl1)
        val imgCategory2 = Image(imgURL2)
        val imgCategory3 = Image(imgURL3)
        val sampleCategories : ArrayList<Image> = arrayListOf()
        sampleCategories.add(imgCategory1)
        sampleCategories.add(imgCategory2)
        sampleCategories.add(imgCategory3)
        return sampleCategories
    }
}