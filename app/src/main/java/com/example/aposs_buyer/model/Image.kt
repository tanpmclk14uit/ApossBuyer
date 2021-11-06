package com.example.aposs_buyer.model

import android.net.Uri
import android.os.Parcelable
import android.util.Log
import androidx.core.net.toUri
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(val imgURL: String): Parcelable{

    private lateinit var imageUri: Uri;
    fun getImageUri(): Uri{
        imageUri = imgURL.toUri().buildUpon().scheme("https").build()
        return imageUri;
    }

    fun getUriRoot(): Uri
    {
        return Uri.parse(imgURL)
    }
}