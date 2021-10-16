package com.example.aposs_buyer.uicontroler.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ItemImgRatingBinding
import com.example.aposs_buyer.databinding.ItemRatingImageBinding
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.RateImage


class RateImageAdapter: ListAdapter<RateImage, RateImageAdapter.RateImageViewHolder>(DiffCallBack) {

    class RateImageViewHolder(val binding: ItemImgRatingBinding): RecyclerView.ViewHolder(binding.root)
    {
         fun bind(rateImage: RateImage)
         {
             binding.rateImage = rateImage
             binding.executePendingBindings()
         }
    }

    object DiffCallBack: DiffUtil.ItemCallback<RateImage>()
    {
        override fun areItemsTheSame(oldItem: RateImage, newItem: RateImage): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RateImage, newItem: RateImage): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemImgRatingBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_img_rating, parent, false)
        return RateImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RateImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}