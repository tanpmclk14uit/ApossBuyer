package com.example.aposs_buyer.uicontroler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.databinding.ItemRatingImageBinding
import com.example.aposs_buyer.model.Image

class RatingImageAdapter: ListAdapter<Image, RatingImageAdapter.RatingImageViewHolder>(DiffCallBack) {

    companion object DiffCallBack : DiffUtil.ItemCallback<Image>() {

        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.imgURL == newItem.imgURL
        }
    }

    class RatingImageViewHolder(private var binding: ItemRatingImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Image) {
            binding.image = image
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingImageViewHolder {
        return RatingImageViewHolder(
            ItemRatingImageBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: RatingImageViewHolder, position: Int) {
        val currentImage = getItem(position)
        holder.bind(currentImage)
    }
}