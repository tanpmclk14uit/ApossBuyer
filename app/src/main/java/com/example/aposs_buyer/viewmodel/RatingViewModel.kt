package com.example.aposs_buyer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.RateImage
import com.example.aposs_buyer.model.RateNowItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RatingViewModel @Inject constructor(): ViewModel() {

    private val _currentRatingItem = MutableLiveData<RateNowItem>()
    val currentRatingItem: LiveData<RateNowItem> get() = _currentRatingItem

    private val _listChoseImage = MutableLiveData<MutableList<RateImage>>()
    val listChoseImage: LiveData<MutableList<RateImage>> get() = _listChoseImage

    fun setCurrentRatingItem(choseItem: RateNowItem)
    {
        _currentRatingItem.value = choseItem
    }

    fun onAddImage(image: RateImage)
    {
        _listChoseImage.value!!.add(image)
    }

    init {
        _listChoseImage.value = mutableListOf<RateImage>()
    }
}