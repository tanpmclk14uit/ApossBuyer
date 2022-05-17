package com.example.aposs_buyer.uicontroler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ItemChooseAddressBinding
import com.example.aposs_buyer.model.Address

class ChooseAddressAdapter(private val onChoose: OnChooseAddress) :
    ListAdapter<Address, ChooseAddressAdapter.ChooseAddressViewHolder>(DiffCallBack) {

    interface OnChooseAddress {
        fun onChoose(address: Address)
    }
    private var lastChosePosition = -1

    class ChooseAddressViewHolder(val binding: ItemChooseAddressBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(address: Address) {
            binding.address = address
            binding.lnItemAddress.setBackgroundResource(R.drawable.bg_address_item)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseAddressViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemChooseAddressBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_choose_address, parent, false)
        return ChooseAddressViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChooseAddressViewHolder, position: Int) {
        val choseAddress = getItem(position)
        holder.bind(choseAddress)
        holder.itemView.setOnClickListener {
            if (lastChosePosition != position) {
                holder.binding.lnItemAddress.setBackgroundResource(R.drawable.bg_item_address_is_selected)
                onChoose.onChoose(choseAddress)
                notifyItemChanged(lastChosePosition)
                lastChosePosition = holder.bindingAdapterPosition
            }
        }
    }


}