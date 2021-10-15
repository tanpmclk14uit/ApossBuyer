package com.example.aposs_buyer.model

import android.net.Uri
import android.os.Parcelable
import androidx.core.net.toUri
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(val imgURL: String): Parcelable{

    private lateinit var imageUri: Uri;
    fun getImageUri(): Uri{
        imageUri = imgURL.toUri().buildUpon().scheme("https").build()
        return imageUri;
    }
}