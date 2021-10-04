package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailProductViewModel @Inject constructor(
): ViewModel() {
    private var selectedProductId: Long = 0
    private val TAG = "DetailProductViewModel"


    fun setSelectedProductId(id: Long){
        selectedProductId = id
        if(selectedProductId !=  (-1).toLong()){
            Log.d(TAG, selectedProductId.toString())
        }
    }

}