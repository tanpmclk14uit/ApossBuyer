package com.example.aposs_buyer.uicontroler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.databinding.ItemOrderBinding
import com.example.aposs_buyer.model.Order
import com.example.aposs_buyer.utils.OrderStatus

class OrderAdapter: ListAdapter<Order, OrderAdapter.OrderViewHolder>(DiffCallBack) {
    class OrderViewHolder(val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(order: Order) {
            binding.order = order
            binding.address.visibility = View.GONE
            val adapter = BillingItemsAdapter()
            binding.billingItems.adapter = adapter
            binding.billingItems.visibility = View.GONE
            setShowAllAddress()
            setShowAllBillingItems()
            setUpRatingButton(order)

        }
        private fun setShowAllAddress(){
            binding.showAll.setOnCheckedChangeListener { _, check ->
                if(check){
                    binding.address.visibility = View.VISIBLE
                }else{
                    binding.address.visibility = View.GONE
                }
            }
        }
        private fun setShowAllBillingItems(){
            binding.showAllBillingItems.setOnCheckedChangeListener { _, check ->
                if(check){
                    binding.billingItems.visibility = View.VISIBLE
                }else{
                    binding.billingItems.visibility = View.GONE
                }
            }
        }
        private fun setUpRatingButton(order: Order){
            if(order.status == OrderStatus.Success){
                binding.ratingNow.visibility = View.VISIBLE
            }else{
                binding.ratingNow.visibility = View.GONE
            }
        }
    }

    object DiffCallBack : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(ItemOrderBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val currentOrder = getItem(position)
        holder.bind(currentOrder)
    }
}