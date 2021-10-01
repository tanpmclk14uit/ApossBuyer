package com.example.aposs_buyer.utils

import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.aposs_buyer.R
import com.example.aposs_buyer.model.Category
import com.example.aposs_buyer.model.MessageItem
import com.example.aposs_buyer.uicontroler.adapter.CategoriesViewPagerAdapter
import com.example.aposs_buyer.uicontroler.adapter.MessageAdapter
import me.relex.circleindicator.CircleIndicator3
import java.util.*
import kotlin.collections.ArrayList

@BindingAdapter("image")
fun bindImage(imageView: ImageView, image: Uri?) {
    Glide.with(imageView.context)
        .load(image)
        .apply(
            RequestOptions().placeholder(R.drawable.animation_loading)
        )
        .into(imageView)
}
@BindingAdapter("categoriesData")
fun bindCategoriesViewPager(viewPager2: ViewPager2, data: ArrayList<Category>?){
    val adapter =viewPager2.adapter as CategoriesViewPagerAdapter
    adapter.submitList(data)
}
@BindingAdapter( "indicatorSize")
fun bindIndicatorSize(indicator: CircleIndicator3, size: Int){
    indicator.createIndicators(size , 0)
}

@BindingAdapter("listMessage")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<MessageItem>?) {
    if (data!!.isNotEmpty()) {
        recyclerView.visibility = View.VISIBLE
        val adapter = recyclerView.adapter as MessageAdapter
        adapter.submitList(data)
    } else
    {
        recyclerView.visibility = View.GONE
    }
}

@BindingAdapter("message")
fun bindMessage(textView: TextView, message:String) {
    textView.text = message
    Log.i("Binding for Text View", "doneeeeeeeeeeee")
}

@BindingAdapter("listMessage")
fun bindContactCommand(linearLayout: LinearLayout, data: List<MessageItem>?)
{
    if (data!!.isEmpty())
    {
        linearLayout.visibility = View.VISIBLE
    }
    else
    {
        linearLayout.visibility = View.GONE
    }
}


