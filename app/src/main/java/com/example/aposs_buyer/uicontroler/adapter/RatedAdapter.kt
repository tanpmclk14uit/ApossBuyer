package com.example.aposs_buyer.uicontroler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ItemRatedBinding
import com.example.aposs_buyer.model.RateItem

class RatedAdapter: ListAdapter<RateItem, RatedAdapter.RatedViewHolder>(DiffCallBack) {



    class RatedViewHolder(val binding: ItemRatedBinding): RecyclerView.ViewHolder(binding.root)
    {
        private val rateImageAdapter = RateImageAdapter()
        fun bind(rateItem: RateItem)
        {
            binding.ratedItem = rateItem
            binding.rcRatedImg.adapter = rateImageAdapter
            binding.rcRatedImg.layoutManager = LinearLayoutManager(binding.rcRatedImg.context, LinearLayoutManager.HORIZONTAL, false)
            binding.executePendingBindings()
        }
    }

    object DiffCallBack: DiffUtil.ItemCallback<RateItem>()
    {
        override fun areItemsTheSame(oldItem: RateItem, newItem: RateItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RateItem, newItem: RateItem): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatedViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemRatedBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_rated, parent, false)
        return RatedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RatedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}