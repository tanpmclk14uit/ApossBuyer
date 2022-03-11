package com.example.aposs_buyer.model

import android.net.Uri
import android.os.Parcelable
import androidx.core.net.toUri
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(val imgURL: String): Parcelable{

    fun getImageUri(): Uri{
        return imgURL.toUri().buildUpon().scheme("https").build()
    }

    fun getUriRoot(): Uri
    {
        return Uri.parse(imgURL)
    }
}