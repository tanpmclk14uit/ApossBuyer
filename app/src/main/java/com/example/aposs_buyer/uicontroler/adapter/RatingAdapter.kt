package com.example.aposs_buyer.uicontroler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.databinding.ItemRatingBinding
import com.example.aposs_buyer.model.ProductRating

class RatingAdapter: ListAdapter<ProductRating, RatingAdapter.RatingViewHolder>(DiffCallBack) {
    companion object DiffCallBack: DiffUtil.ItemCallback<ProductRating>(){
        override fun areItemsTheSame(oldItem: ProductRating, newItem: ProductRating): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ProductRating, newItem: ProductRating): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class RatingViewHolder(private val binding: ItemRatingBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(currentRating: ProductRating){
            binding.rating = currentRating
            binding.images.adapter = RatingImageAdapter()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        return RatingViewHolder(ItemRatingBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        val currentRating = getItem(position)
        holder.bind(currentRating)
    }

}