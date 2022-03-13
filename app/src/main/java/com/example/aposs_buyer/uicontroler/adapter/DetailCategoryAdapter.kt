package com.example.aposs_buyer.uicontroler.adapter

import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ItemCategoryBinding
import com.example.aposs_buyer.model.DetailCategory

class DetailCategoryAdapter(var clickListener: OnItemClickListener) :
    ListAdapter<DetailCategory, DetailCategoryAdapter.CategoryViewHolder>(DiffCallBack) {


    open class OnItemClickListener(private val clickListener: (detailCategory: DetailCategory) -> Unit) {
        fun onItemClick(detailCategory: DetailCategory) = clickListener(detailCategory)
    }

    class CategoryViewHolder(
        val binding: ItemCategoryBinding,
        private val itemClickListener: OnItemClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var detailCategoryViewPagerAdapter: DetailCategoryViewPagerAdapter
        private var categoriesLeftToRight: Boolean = true
        private val handler = Handler()
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
            detailCategoryViewPagerAdapter =
                DetailCategoryViewPagerAdapter(DetailCategoryViewPagerAdapter.OnImageItemClickListener {
                    itemClickListener.onItemClick(category)
                })
            binding.imageViewPager.adapter = detailCategoryViewPagerAdapter
            binding.indicator.setViewPager(binding.imageViewPager)
            setUpViewPagerCallBack()
            binding.executePendingBindings()
        }

        fun onDestroy() {
            handler.removeCallbacks(categoriesRunnable)
        }

        private fun setUpViewPagerCallBack() {
            binding.imageViewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    handler.removeCallbacks(categoriesRunnable)
                    if (binding.imageViewPager.currentItem == binding.category!!.images.size - 1) {
                        categoriesLeftToRight = false
                    } else {
                        if (binding.imageViewPager.currentItem == 0) {
                            categoriesLeftToRight = true
                        }
                    }
                    handler.postDelayed(categoriesRunnable, 4000)
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


    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int
    ) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
        holder.itemView.setOnClickListener {
            clickListener.onItemClick(currentItem)
        }
    }

    override fun onViewDetachedFromWindow(holder: CategoryViewHolder) {
        holder.onDestroy()
        super.onViewDetachedFromWindow(holder)
    }
}