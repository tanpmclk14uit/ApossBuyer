package com.example.aposs_buyer.uicontroler.adapter

import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ItemCategoryBinding
import com.example.aposs_buyer.model.DetailCategory

class DetailCategoryAdapter(var clickListener: ClickListener) :
    DetailCategoryViewPagerAdapter.ImageClickListener,
    ListAdapter<DetailCategory, DetailCategoryAdapter.CategoryViewHolder>(DiffCallBack) {

    interface ClickListener {
        fun onClick(id: Long, name: String)
    }

    class CategoryViewHolder(val binding: ItemCategoryBinding, imageClickListener: ClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        private val detailCategoryViewPagerAdapter =
            DetailCategoryViewPagerAdapter(imageClickListener)
        private var categoriesLeftToRight: Boolean = true
        private val mHandler: Handler = Handler()
        private val categoriesRunnable: Runnable = Runnable() {
            kotlin.run {
                if (categoriesLeftToRight) {
                    binding.imageViewPager.currentItem += 1

                } else {
                    binding.imageViewPager.currentItem -= 1
                }

            }
        }

        fun bind(category: DetailCategory) {
            binding.category = category
            detailCategoryViewPagerAdapter.detailCategory = category
            binding.imageViewPager.adapter = detailCategoryViewPagerAdapter
            binding.executePendingBindings()
        }

        fun setUpViewPagerCallBack() {
            binding.imageViewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    mHandler.removeCallbacks(categoriesRunnable)
                    if (binding.imageViewPager.currentItem == binding.category!!.images.size - 1) {
                        categoriesLeftToRight = false
                    } else {
                        if (binding.imageViewPager.currentItem == 0) {
                            categoriesLeftToRight = true
                        }
                    }
                    mHandler.postDelayed(categoriesRunnable, 4000)
                }
            })
        }
    }

    object DiffCallBack : DiffUtil.ItemCallback<DetailCategory>() {
        override fun areItemsTheSame(oldItem: DetailCategory, newItem: DetailCategory): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DetailCategory, newItem: DetailCategory): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemCategoryBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_category, parent, false)

        return CategoryViewHolder(binding, clickListener)
    }

    private var id: Long = 0
    private lateinit var name: String
    private lateinit var unit: Unit

    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
        name = getItem(position).name
        id = getItem(position).id
        holder.binding.indicator.setViewPager(holder.binding.imageViewPager)
        unit = holder.itemView.setOnClickListener {
            name = getItem(position).name
            id = getItem(position).id
            clickListener.onClick(getItem(position).id, getItem(position).name)
        }
        holder.setUpViewPagerCallBack()
    }

    override fun onImageClick() {
        clickListener.onClick(id, name)
    }
}