package com.example.aposs_buyer.uicontroler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.databinding.ItemOrderDeliveringStateBinding
import com.example.aposs_buyer.model.OrderDeliveringState

class OrderDeliveringStateAdapter: ListAdapter<OrderDeliveringState, OrderDeliveringStateAdapter.OrderDeliveringHolder>(DiffCallBack) {

    class OrderDeliveringHolder (val binding: ItemOrderDeliveringStateBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(orderDeliveringState: OrderDeliveringState, position: Int, size: Int){
            binding.orderDeliveringState = orderDeliveringState
            if(position == 0){
                binding.start.visibility = View.GONE
            }
            if(position == size-1){
                binding.stop.visibility = View.GONE
            }
        }
    }
    object DiffCallBack: DiffUtil.ItemCallback<OrderDeliveringState>() {
        override fun areItemsTheSame(
            oldItem: OrderDeliveringState,
            newItem: OrderDeliveringState
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: OrderDeliveringState,
            newItem: OrderDeliveringState
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderDeliveringHolder {
        return OrderDeliveringHolder(ItemOrderDeliveringStateBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: OrderDeliveringHolder, position: Int) {
        val currentOrderState = getItem(position)
        holder.bind(currentOrderState, position, itemCount)
    }

}