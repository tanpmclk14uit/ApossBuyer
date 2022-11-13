package com.example.aposs_buyer.uicontroler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.databinding.ItemOrderBinding
import com.example.aposs_buyer.model.Order

class OrderAdapter(
    private val onClickListener: OnClickListener,
    private val orderInterface: OrderInterface
) : ListAdapter<Order, OrderAdapter.OrderViewHolder>(DiffCallBack) {

    open class OnClickListener(val clickListener: (order: Order) -> Unit) {
        fun onClick(order: Order) = clickListener(order)
    }

    interface OrderInterface {
        fun onCheckOutClick(orderId: Long)
    }

    class OrderViewHolder(val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(order: Order) {
            binding.order = order
            val adapter = BillingItemsAdapter()
            binding.billingItems.adapter = adapter
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
        holder.itemView.setOnClickListener {
            onClickListener.onClick(currentOrder)
        }
        holder.binding.checkOutNow.setOnClickListener {
            orderInterface.onCheckOutClick(currentOrder.id)
        }
    }
}