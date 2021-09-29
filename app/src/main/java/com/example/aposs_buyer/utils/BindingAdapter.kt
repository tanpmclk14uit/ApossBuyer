package com.example.aposs_buyer.utils

import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.aposs_buyer.R
import com.example.aposs_buyer.model.Category
import com.example.aposs_buyer.model.HomeProduct
import com.example.aposs_buyer.model.RankingProduct
import com.example.aposs_buyer.uicontroler.adapter.CategoriesViewPagerAdapter
import com.example.aposs_buyer.uicontroler.adapter.HomeProductAdapter
import com.example.aposs_buyer.uicontroler.adapter.RankingViewPagerAdapter
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