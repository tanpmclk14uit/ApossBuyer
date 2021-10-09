package com.example.aposs_buyer.uicontroler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ItemViewPagerDetailCategoryBinding
import com.example.aposs_buyer.model.Image

class DetailCategoryViewPagerAdapter: ListAdapter<Image, DetailCategoryViewPagerAdapter.DetailCategoryViewPagerViewHolder>(DiffCallBack) {

    class DetailCategoryViewPagerViewHolder(val binding: ItemViewPagerDetailCategoryBinding): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(image: Image)
        {
            binding.image = image
            binding.executePendingBindings()
        }
    }

    object DiffCallBack: DiffUtil.ItemCallback<Image>()
    {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailCategoryViewPagerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemViewPagerDetailCategoryBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_view_pager_detail_category, parent, false)
        return DetailCategoryViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailCategoryViewPagerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}