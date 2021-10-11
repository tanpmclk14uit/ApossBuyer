package com.example.aposs_buyer.uicontroler.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ItemAddressBinding
import com.example.aposs_buyer.model.Address

class AddressAdapter(val onClickListener: OnAddressCLickListener): ListAdapter<Address, AddressAdapter.AddressViewHolder>(DiffCallBack) {

    interface OnAddressCLickListener{
        fun onClick(position: Int)
    }

    class AddressViewHolder(val binding: ItemAddressBinding ): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(address: Address)
        {
            binding.address = address

//            if (address.isDefault) binding.lnItemAddress.setBackgroundResource(R.drawable.bg_item_address_is_selected)
//            else binding.lnItemAddress.setBackgroundResource(R.drawable.bg_address_item)

            binding.executePendingBindings()
        }
    }

    object DiffCallBack: DiffUtil.ItemCallback<Address>() {
        override fun areItemsTheSame(oldItem: Address, newItem: Address): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Address, newItem: Address): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemAddressBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_address, parent, false)
        return AddressViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.bind(getItem(position))
        if (holder.binding.address!!.isDefault) holder.binding.lnItemAddress.setBackgroundResource(R.drawable.bg_item_address_is_selected)
        else holder.binding.lnItemAddress.setBackgroundResource(R.drawable.bg_address_item)
        holder.itemView.setOnClickListener {
            holder.binding.address!!.isDefault = true
            holder.binding.lnItemAddress.setBackgroundResource(R.drawable.bg_address_item)
            onClickListener.onClick(position)
        }
    }
}