package com.example.aposs_buyer.utils

import android.net.Uri
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.aposs_buyer.R
import com.example.aposs_buyer.model.*
import com.example.aposs_buyer.uicontroler.adapter.*
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
@BindingAdapter("imagesData")
fun bindDetailProductImageViewPager(viewPager2: ViewPager2, data: List<Image>?){
    val adapter = viewPager2.adapter as DetailProductImageViewPagerAdapter
    adapter.submitList(data)
}
@BindingAdapter( "indicatorSize")
fun bindIndicatorSize(indicator: CircleIndicator3, size: Int){
    indicator.createIndicators(size , 0)
}

@BindingAdapter("rankingData")
fun bindRankingViewPager(viewPager2: ViewPager2, data: ArrayList<RankingProduct>?){
    val adapter = viewPager2.adapter as RankingViewPagerAdapter
    adapter.submitList(data)
}
@BindingAdapter("productData")
fun bindProductRecyclerView(recyclerView: RecyclerView, data: ArrayList<HomeProduct>?){
    val adapter = recyclerView.adapter as HomeProductAdapter
    adapter.submitList(data)
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

@BindingAdapter("favoriteData")
fun bindFavoriteRecyclerView(recyclerView: RecyclerView, data: ArrayList<FavoriteProduct>?){
    val adapter = recyclerView.adapter as FavoriteRecyclerViewAdapter
    adapter.submitList(data)
}

@BindingAdapter("listCart")
fun bindRecycleView(recyclerView: RecyclerView, lstCart: ArrayList<CartItem>)
{
    val adapter = recyclerView.adapter as CartAdapter
    adapter.submitList(lstCart)
}

