package com.example.aposs_buyer.uicontroler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ItemAddressBinding
import com.example.aposs_buyer.model.Address

class AddressAdapter(val onClickListener: OnAddressCLickListener) :
    ListAdapter<Address, AddressAdapter.AddressViewHolder>(DiffCallBack) {

    interface OnAddressCLickListener {
        fun onEdit(address: Address)
    }

    class AddressViewHolder(val binding: ItemAddressBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(address: Address) {
            binding.address = address
            if (address.isDefaultAddress) {
                binding.isDefault.visibility = View.VISIBLE
            } else {
                binding.isDefault.visibility = View.GONE
            }
            binding.executePendingBindings()
        }
    }

    object DiffCallBack : DiffUtil.ItemCallback<Address>() {
        override fun areItemsTheSame(oldItem: Address, newItem: Address): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Address, newItem: Address): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemAddressBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_address, parent, false)
        return AddressViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.bind(getItem(position))
        if (holder.binding.address!!.isDefaultAddress)
            holder.binding.lnItemAddress.setBackgroundResource(R.drawable.bg_item_address_is_selected)
        else holder.binding.lnItemAddress.setBackgroundResource(R.drawable.bg_address_item)
        holder.binding.editAddress.setOnClickListener {
            onClickListener.onEdit(getItem(position))
        }
    }
}