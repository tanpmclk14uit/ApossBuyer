package com.example.aposs_buyer.uicontroler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.persistableBundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ItemCartBinding
import com.example.aposs_buyer.databinding.ItemRightSideMessageBinding
import com.example.aposs_buyer.model.AmountChangeListener
import com.example.aposs_buyer.model.CartItem
import com.example.aposs_buyer.uicontroler.fragment.CartFragment
import com.example.aposs_buyer.viewmodel.CartViewModel
import java.util.zip.Inflater

class CartAdapter: ListAdapter<CartItem, CartAdapter.CartViewHolder>(DiffCallBack) {

    object listener {
        val currentListener = MutableLiveData<AmountChangeListener>()
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
            listener.currentListener.value = AmountChangeListener(currentItem.id,currentItem.amount)
            this.notifyItemChanged(position)
        }
        holder.binding.imgMinus.setOnClickListener {
            onReduceAmount(position)
            listener.currentListener.value = AmountChangeListener(currentItem.id,currentItem.amount)
            this.notifyItemChanged(position)
        }
    }

    private fun onAddAmount(position: Int)
    {
        getItem(position).amount ++
    }

    private fun onReduceAmount(position: Int)
    {
        if (getItem(position).amount > 1) {
            getItem(position).amount--
        }
    }

}