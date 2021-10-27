package com.example.aposs_buyer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.HomeProduct
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.Kind
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class KindViewModel @Inject constructor(): ViewModel() {

    private val selectedCategoryId = MutableLiveData<Long>()

    private val _listKind = MutableLiveData<MutableList<Kind>>()
    val listKind : LiveData<MutableList<Kind>> get() = _listKind

    val selectedCategoryName = MutableLiveData<String>()

    fun setSelectedCategory()
    {
        _listKind.value = loadProduct(selectedCategoryId.value!!)
    }

    fun getSelectedCategory(): MutableList<Kind>
    {
        return _listKind.value!!
    }

    fun setSelectedKindIdAndName(selectedKindId: Long, selectedName: String)
    {
        selectedCategoryId.value = selectedKindId
        selectedCategoryName.value = selectedName
    }

    private fun loadProduct(selectedKindId: Long): MutableList<Kind>
    {
        //declare call db to get all product having selected kind id here
        return when (selectedKindId) {
            1L -> {
                val imgURl1 =
                    "https://dailytimes.com.pk/assets/uploads/2019/04/09/fast-food-chain.jpg"
                val imgProduct1 = Image(imgURl1)
                val imgURl2 ="https://vietcetera.com/wp-content/uploads/2018/07/Bitis_Hunter_Featured.jpg"
                val imgProduct2 = Image(imgURl2)
                val sampleProducts: MutableList<HomeProduct> = mutableListOf()
                val sampleKinds: MutableList<Kind> = mutableListOf()
                sampleKinds.add(Kind(1L, "Vegetable", 23424, 75, 3.4F, sampleProducts, imgProduct1))
                sampleKinds.add(Kind(1L, "Meat", 1244, 253, 3.8F, sampleProducts, imgProduct2))
                sampleKinds.add(Kind(1L, "Chicken", 14124, 23, 4.4F, sampleProducts, imgProduct2))
                sampleKinds.add(Kind(1L, "Fish", 25452, 34, 2.4F, sampleProducts, imgProduct1))
                sampleKinds.add(Kind(1L, "Vegetable", 23424, 75, 3.4F, sampleProducts, imgProduct1))
                sampleKinds.add(Kind(1L, "Meat", 1244, 253, 3.8F, sampleProducts, imgProduct2))
                sampleKinds.add(Kind(1L, "Chicken", 14124, 23, 4.4F, sampleProducts, imgProduct2))
                sampleKinds.add(Kind(1L, "Fish", 25452, 34, 2.4F, sampleProducts, imgProduct1))
                sampleKinds
            }
            else ->{
                val imgURl1 =
                    "https://dailytimes.com.pk/assets/uploads/2019/04/09/fast-food-chain.jpg"
                val imgProduct1 = Image(imgURl1)
                val imgURl2 ="https://vietcetera.com/wp-content/uploads/2018/07/Bitis_Hunter_Featured.jpg"
                val imgProduct2 = Image(imgURl2)
                val sampleProducts: MutableList<HomeProduct> = mutableListOf()
                val sampleKinds: MutableList<Kind> = mutableListOf()
                sampleKinds.add(Kind(1L, "Vegetable", 23424, 75, 3.4F, sampleProducts, imgProduct1))
                sampleKinds.add(Kind(1L, "Meat", 1244, 253, 3.8F, sampleProducts, imgProduct2))
                sampleKinds.add(Kind(1L, "Chicken", 14124, 23, 4.4F, sampleProducts, imgProduct2))
                sampleKinds.add(Kind(1L, "Fish", 25452, 34, 2.4F, sampleProducts, imgProduct1))
                sampleKinds.add(Kind(1L, "Vegetable", 23424, 75, 3.4F, sampleProducts, imgProduct1))
                sampleKinds.add(Kind(1L, "Meat", 1244, 253, 3.8F, sampleProducts, imgProduct2))
                sampleKinds.add(Kind(1L, "Chicken", 14124, 23, 4.4F, sampleProducts, imgProduct2))
                sampleKinds.add(Kind(1L, "Fish", 25452, 34, 2.4F, sampleProducts, imgProduct1))
                sampleKinds
            }
        }
    }

}