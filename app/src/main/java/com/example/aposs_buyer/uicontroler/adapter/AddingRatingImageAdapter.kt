package com.example.aposs_buyer.uicontroler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ItemImgAddRatingBinding
import com.example.aposs_buyer.model.RateImage

class AddingRatingImageAdapter: ListAdapter<RateImage, AddingRatingImageAdapter.AddRatingImageViewHolder>(DiffCallBack) {

    companion object DiffCallBack : DiffUtil.ItemCallback<RateImage>() {

        override fun areItemsTheSame(oldItem: RateImage, newItem: RateImage): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: RateImage, newItem: RateImage): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class AddRatingImageViewHolder(private var binding: ItemImgAddRatingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: RateImage) {
            binding.rateImage = image
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddRatingImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemImgAddRatingBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_img_add_rating, parent, false)
        return AddRatingImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddRatingImageViewHolder, position: Int) {
        val currentImage = getItem(position)
        holder.bind(currentImage)
    }
}