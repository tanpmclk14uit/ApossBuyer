package com.example.aposs_buyer.uicontroler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ItemNeedRatingBinding
import com.example.aposs_buyer.model.RateNowItem

class RateNowAdapter(val onItemClick: OnItemClick): ListAdapter<RateNowItem, RateNowAdapter.NeedRateViewHolder>(DiffCallBack) {

    interface OnItemClick{
        fun onItemClick(rateNowItem: RateNowItem)
    }

    class NeedRateViewHolder(val binding: ItemNeedRatingBinding): RecyclerView.ViewHolder(binding.root)
    {
        private val rateImageAdapter = RateImageAdapter()
        fun bind(rateNowItem: RateNowItem)
        {
            binding.rateNowItem = rateNowItem
            binding.executePendingBindings()
        }
    }

    object DiffCallBack: DiffUtil.ItemCallback<RateNowItem>()
    {
        override fun areItemsTheSame(oldItem: RateNowItem, newItem: RateNowItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RateNowItem, newItem: RateNowItem): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NeedRateViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemNeedRatingBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_need_rating, parent, false)
        return NeedRateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NeedRateViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onItemClick.onItemClick(getItem(position))
        }
    }
}