package com.example.aposs_buyer.uicontroler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.databinding.ItemOrderBilingBinding
import com.example.aposs_buyer.model.OrderBillingItem

class BillingItemsAdapter: ListAdapter<OrderBillingItem, BillingItemsAdapter.BillingItemViewHolder>(DiffCallBack) {
    class BillingItemViewHolder(val binding: ItemOrderBilingBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(orderBillingItem: OrderBillingItem){
            binding.billingItem = orderBillingItem
        }
    }

    object DiffCallBack: DiffUtil.ItemCallback<OrderBillingItem>() {
        override fun areItemsTheSame(
            oldItem: OrderBillingItem,
            newItem: OrderBillingItem
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: OrderBillingItem,
            newItem: OrderBillingItem
        ): Boolean {
           return oldItem.id   == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillingItemViewHolder {
        return BillingItemViewHolder(ItemOrderBilingBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BillingItemViewHolder, position: Int) {
        val currentBillingItem =getItem(position)
        holder.bind(currentBillingItem)
    }
}