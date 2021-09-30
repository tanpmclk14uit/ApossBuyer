package com.example.aposs_buyer.uicontroler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ItemViewPaperCategoriesBinding
import com.example.aposs_buyer.model.Category
import com.example.aposs_buyer.model.ImageCategory

class CategoriesViewPagerAdapter :
    ListAdapter<Category, CategoriesViewPagerAdapter.CategoryViewHolder>(DiffCallBack) {


    companion object DiffCallBack : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class CategoryViewHolder(private var binding: ItemViewPaperCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imgCategory: ImageCategory) {
            binding.imgCategory = imgCategory
            binding.executePendingBindings()
        }
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemViewPaperCategoriesBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.item_view_paper_categories, parent, false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentCategory = getItem(position)
        holder.bind(currentCategory.mainImage)
    }
}