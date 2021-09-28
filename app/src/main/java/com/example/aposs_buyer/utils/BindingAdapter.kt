package com.example.aposs_buyer.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.aposs_buyer.R
import com.example.aposs_buyer.model.Image

@BindingAdapter("imageURL")
fun bindImage(imageView: ImageView, image: Image) {
    Glide.with(imageView.context)
        .load(image.getImageUri())
        .apply(
            RequestOptions().placeholder(R.drawable.animation_loading)
        )
        .into(imageView)
}
