package com.example.aposs_buyer.model

import android.net.Uri
import androidx.core.net.toUri


data class Image(val imgURL: String){

    private lateinit var imageUri: Uri;
    fun getImageUri(): Uri{
        imageUri = imgURL.toUri().buildUpon().scheme("https").build()
        return imageUri;
    }
}