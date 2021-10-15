package com.example.aposs_buyer.uicontroler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ItemCheckOutBinding
import com.example.aposs_buyer.model.CartItem

class CheckOutAdapter: ListAdapter<CartItem, CheckOutAdapter.CheckOutViewHolder>(DiffCallBack) {

    class CheckOutViewHolder(var binding: ItemCheckOutBinding): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(cartItem: CartItem)
        {
            binding.cartItem = cartItem
            binding.executePendingBindings()
        }
    }

    object DiffCallBack: DiffUtil.ItemCallback<CartItem>()
    {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckOutViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemCheckOutBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_check_out, parent, false)
        return CheckOutViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CheckOutViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}