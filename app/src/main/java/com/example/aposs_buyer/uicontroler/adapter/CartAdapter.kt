package com.example.aposs_buyer.uicontroler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ItemCartBinding
import com.example.aposs_buyer.model.CartItem

class CartAdapter(private val changeAmount: ChangeAmount): ListAdapter<CartItem, CartAdapter.CartViewHolder>(DiffCallBack) {


    interface ChangeAmount{
        fun onChangeAmount()
    }
    class CartViewHolder(val binding: ItemCartBinding): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(cartItem: CartItem) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemCartBinding  = DataBindingUtil.inflate(layoutInflater, R.layout.item_cart, parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
        holder.binding.imgPlus.setOnClickListener {
            onAddAmount(position)
            this.notifyItemChanged(position)
        }
        holder.binding.imgMinus.setOnClickListener {
            onReduceAmount(position)
            this.notifyItemChanged(position)
        }
    }

    private fun onAddAmount(position: Int)
    {
        getItem(position).amount ++
        changeAmount.onChangeAmount()
    }

    private fun onReduceAmount(position: Int)
    {
        if (getItem(position).amount > 1) {
            getItem(position).amount--
            changeAmount.onChangeAmount()
        }
    }

}