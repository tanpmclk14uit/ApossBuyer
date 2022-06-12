package com.example.aposs_buyer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.Image
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CheckCompatibilityViewModel @Inject constructor(

): ViewModel() {
    // set up data
    val productName= MutableLiveData<String>()
    val productImage = MutableLiveData<Image>()
    val productNature = MutableLiveData<String>()


    fun setUpProductData(name: String?, image: Image?, nature: String?){
        productName.value = name?:"Loading name error"
        productImage.value = image?: Image("https://developer.android.com/codelabs/basic-android-kotlin-training-internet-images/img/467c213c859e1904.png")
        productNature.value = nature?:"Loading nature error"
    }
}