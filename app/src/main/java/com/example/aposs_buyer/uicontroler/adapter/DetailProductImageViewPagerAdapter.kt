package com.example.aposs_buyer.uicontroler.adapter

import android.view.LayoutInflater
import android.view.ScaleGestureDetector
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ItemDetailProductImageViewPagerBinding
import com.example.aposs_buyer.model.Image

class DetailProductImageViewPagerAdapter(private val onClickListener: OnClickListener): ListAdapter<Image, DetailProductImageViewPagerAdapter.DetailProductImageViewPagerViewHolder>(DiffCallBack) {

    class OnClickListener(val clickListener: (position: Int) -> Unit) {
        fun onClick(position: Int) = clickListener(position)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Image>() {

        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.imgURL == newItem.imgURL
        }
    }
    class DetailProductImageViewPagerViewHolder(private var binding: ItemDetailProductImageViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Image) {
            binding.image = image
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailProductImageViewPagerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemDetailProductImageViewPagerBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.item_detail_product_image_view_pager, parent, false
        )
        return DetailProductImageViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailProductImageViewPagerViewHolder, position: Int) {
        val currentImage: Image = getItem(position)
        holder.bind(currentImage)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(position)
        }
    }
}