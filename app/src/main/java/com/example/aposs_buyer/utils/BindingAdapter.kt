package com.example.aposs_buyer.utils

import android.graphics.Color
import android.net.Uri
import android.view.View
import android.widget.*
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.aposs_buyer.R
import com.example.aposs_buyer.model.*
import com.example.aposs_buyer.uicontroler.adapter.*
import me.relex.circleindicator.CircleIndicator3



@BindingAdapter("imagesPath")
fun bindImagePath(imageView: ImageView, image: Uri?) {
    Glide.with(imageView.context)
        .asBitmap()
        .load(image)
        .apply(
            RequestOptions().placeholder(R.drawable.animation_loading)
        )
        .transition(BitmapTransitionOptions.withCrossFade())
        .into(imageView)
    imageView.scaleType = ImageView.ScaleType.CENTER_CROP
}

@BindingAdapter("categoriesData")
fun bindCategoriesViewPager(viewPager2: ViewPager2, data: ArrayList<Category>?) {
    val adapter = viewPager2.adapter as CategoriesViewPagerAdapter
    adapter.submitList(data)
}

@BindingAdapter("listKind")
fun bindCategoryRecyclerView(recyclerView: RecyclerView, data: List<Kind>?) {
    val adapter = recyclerView.adapter as KindAdapter
    adapter.submitList(data)
}

@BindingAdapter("listProduct")
fun bindKindRecyclerView(recyclerView: RecyclerView, data: List<HomeProduct>?) {
    val adapter = recyclerView.adapter as HomeProductAdapter
    adapter.submitList(data)
}

@BindingAdapter("detailCategories")
fun bindDetailCategoriesRecyclerView(recyclerView: RecyclerView, data: List<DetailCategory>?) {
    val adapter = recyclerView.adapter as DetailCategoryAdapter
    adapter.submitList(data)
}

@BindingAdapter("detailCategoryImages")
fun bindDetailCategoriesViewPager(viewPager2: ViewPager2, data: List<Image>?) {
    val adapter = viewPager2.adapter as DetailCategoryViewPagerAdapter
    adapter.submitList(data)
}

@BindingAdapter("imagesData")
fun bindDetailProductImageViewPager(viewPager2: ViewPager2, data: List<Image>?) {
    val adapter = viewPager2.adapter as DetailProductImageViewPagerAdapter
    adapter.submitList(data)
}

@BindingAdapter("imagesRating")
fun bindDetailProductImageRating(recyclerView: RecyclerView, data: List<Image>?) {
    val adapter = recyclerView.adapter as RatingImageAdapter
    adapter.submitList(data)
}

@BindingAdapter("indicatorSize")
fun bindIndicatorSize(indicator: CircleIndicator3, size: Int) {
    indicator.createIndicators(size, 0)
}

@BindingAdapter("rankingData")
fun bindRankingViewPager(viewPager2: ViewPager2, data: ArrayList<RankingProduct>?) {
    val adapter = viewPager2.adapter as RankingViewPagerAdapter
    adapter.submitList(data)
}

@BindingAdapter("productData")
fun bindProductRecyclerView(recyclerView: RecyclerView, data: List<HomeProduct>?) {
    val adapter = recyclerView.adapter as HomeProductAdapter
    adapter.submitList(data)
}

@BindingAdapter("listMessage")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<MessageItem>?
) {
    if (data!!.isNotEmpty()) {
        recyclerView.visibility = View.VISIBLE
        val adapter = recyclerView.adapter as MessageAdapter
        adapter.submitList(data)
    } else {
        recyclerView.visibility = View.GONE
    }
}

@BindingAdapter("message")
fun bindMessage(textView: TextView, message: String) {
    textView.text = message
}

@BindingAdapter("listMessage")
fun bindContactCommand(linearLayout: LinearLayout, data: List<MessageItem>?) {
    if (data!!.isEmpty()) {
        linearLayout.visibility = View.VISIBLE
    } else {
        linearLayout.visibility = View.GONE
    }
}

@BindingAdapter("listRated")
fun bindRatedRecyclerView(recyclerView: RecyclerView, data: List<RateItem>?) {
    val adapter = recyclerView.adapter as RatedAdapter
    adapter.submitList(data)
}

@BindingAdapter("favoriteData")
fun bindFavoriteRecyclerView(recyclerView: RecyclerView, data: ArrayList<FavoriteProduct>?) {
    val adapter = recyclerView.adapter as FavoriteRecyclerViewAdapter
    adapter.submitList(data)
}

@BindingAdapter("listRatedImage")
fun bindRateImageRecyclerView(recyclerView: RecyclerView, data: List<RateImage>?) {
    val adapter = recyclerView.adapter as RateImageAdapter
    adapter.submitList(data)
}

@BindingAdapter("listOnRatingImage")
fun bindOnRatingImageRecyclerView(recyclerView: RecyclerView, data: List<RateImage>?) {
    val adapter = recyclerView.adapter as AddingRatingImageAdapter
    adapter.submitList(data)
}

@BindingAdapter("listRateNow")
fun bindRateNowRecyclerView(recyclerView: RecyclerView, data: List<RateNowItem>?) {
    val adapter = recyclerView.adapter as RateNowAdapter
    adapter.submitList(data)
}

@BindingAdapter("stringPropertyValue")
fun bindStringPropertyValue(recyclerView: RecyclerView, data: List<PropertyValue>?) {
    val adapter = recyclerView.adapter as StringDetailPropertyAdapter
    adapter.submitList(data)
}

@BindingAdapter("colorPropertyValue")
fun bindColorPropertyValue(recyclerView: RecyclerView, data: List<PropertyValue>?) {
    val adapter = recyclerView.adapter as ColorDetailPropertyAdapter
    adapter.submitList(data)
}

@BindingAdapter("stringProperty")
fun bindStringProperty(recyclerView: RecyclerView, data: List<ProductDetailProperty>?) {
    val adapter = recyclerView.adapter as StringPropertyAdapter
    adapter.submitList(data)
}

@BindingAdapter("colorProperty")
fun bindColorProperty(recyclerView: RecyclerView, data: List<ProductDetailProperty>?) {
    val adapter = recyclerView.adapter as ColorPropertyAdapter
    adapter.submitList(data)
}

@BindingAdapter("listCart", "isCheckOut")
fun bindRecycleView(recyclerView: RecyclerView, lstCart: ArrayList<CartItem>, isCheckOut: Int) {
    when (isCheckOut) {
        1 -> {
            val adapter = recyclerView.adapter as CheckOutAdapter
            adapter.submitList(lstCart)
        }
        2 -> {
            val adapter = recyclerView.adapter as CartAdapter
            adapter.submitList(lstCart)
        }
        else -> {
            val adapter = recyclerView.adapter as CheckOutDialogAdapter
            adapter.submitList(lstCart)
        }
    }
}

@BindingAdapter("userCartData")
fun bindCardRecycleView(recyclerView: RecyclerView, listCart: List<CartItem>?) {
    val adapter = recyclerView.adapter as CartAdapter
    adapter.submitList(listCart)
}


@BindingAdapter("setToggleColor")
fun bindColorToImageBackground(toggleButton: ToggleButton, data: String?) {
    val myColor: Int = Color.parseColor(data)
    toggleButton.setBackgroundColor(myColor)
}

@BindingAdapter("ratings")
fun bindRating(recyclerView: RecyclerView, data: List<ProductRating>?) {
    val adapter = recyclerView.adapter as RatingAdapter
    adapter.submitList(data)
}

@BindingAdapter("listSearchFullFill")
fun bindCategoriesViewPager(linearLayout: LinearLayout, data: List<HomeProduct>?) {
    if (data!!.isEmpty()) {
        linearLayout.visibility = View.INVISIBLE
    } else {
        linearLayout.visibility = View.VISIBLE
    }
}

@BindingAdapter("listSearchEmpty")
fun bindCategoriesViewPager(relativeLayout: RelativeLayout, data: List<HomeProduct>?) {
    if (data!!.isEmpty()) {
        relativeLayout.visibility = View.VISIBLE
    } else {
        relativeLayout.visibility = View.GONE
    }
}

@BindingAdapter("listAddress")
fun bindAddressRecyclerView(recyclerView: RecyclerView, data: List<Address>?) {
    val adapter = recyclerView.adapter as AddressAdapter
    adapter.submitList(data)
}

@BindingAdapter("notificationData")
fun bindNotificationRecyclerView(recyclerView: RecyclerView, data: List<Notification>?) {
    val adapter = recyclerView.adapter as NotificationAdapter
    adapter.submitList(data)
}

@BindingAdapter("billingItemData")
fun bindBillingItemRecyclerView(recyclerView: RecyclerView, data: List<OrderBillingItem>?) {
    val adapter = recyclerView.adapter as BillingItemsAdapter
    adapter.submitList(data)
}

@BindingAdapter("orderData")
fun bindOrderRecyclerView(recyclerView: RecyclerView, data: List<Order>?) {
    val adapter = recyclerView.adapter as OrderAdapter
    adapter.submitList(data)
}

@BindingAdapter("statusIconData")
fun bindStatusIcon(imageView: ImageView, data: OrderStatus?) {
    if (data != null) {
        when (data) {
            OrderStatus.Success -> {
                imageView.setImageResource(R.drawable.ic_order_pass)
            }
            OrderStatus.Pending -> {
                imageView.setImageResource(R.drawable.ic_order_pending)
            }
            OrderStatus.Cancel -> {
                imageView.setImageResource(R.drawable.ic_order_cancel)
            }
            OrderStatus.Confirmed -> {
                imageView.setImageResource(R.drawable.ic_order_confirm)
            }
            OrderStatus.Delivering -> {
                imageView.setImageResource(R.drawable.ic_order_delivering)
            }
        }
    }
}

@BindingAdapter("deliveringStateData")
fun bindDeliveringStateRecyclerView(recyclerView: RecyclerView, data: List<OrderDeliveringState>?) {
    val adapter = recyclerView.adapter as OrderDeliveringStateAdapter
    adapter.submitList(data)
}

