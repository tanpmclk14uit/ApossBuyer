package com.example.aposs_buyer.uicontroler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.databinding.ItemColorPropertyBinding
import com.example.aposs_buyer.model.PropertyValue

class ColorDetailPropertyAdapter(private val propertyColorValueSelected: PropertyColorValueSelected) :
    ListAdapter<PropertyValue, ColorDetailPropertyAdapter.ColorDetailPropertyViewHolder>(
        DiffCallBack
    ) {

    interface PropertyColorValueSelected {
        fun notifySelectedColorValueChange(propertyId: Long)
    }

    object DiffCallBack : DiffUtil.ItemCallback<PropertyValue>() {
        override fun areItemsTheSame(oldItem: PropertyValue, newItem: PropertyValue): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PropertyValue, newItem: PropertyValue): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class ColorDetailPropertyViewHolder(val binding: ItemColorPropertyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentPropertyValue: PropertyValue) {
            binding.property = currentPropertyValue
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ColorDetailPropertyViewHolder {
        return ColorDetailPropertyViewHolder(
            ItemColorPropertyBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ColorDetailPropertyViewHolder, position: Int) {
        val currentPropertyValue = getItem(position)
        holder.bind(currentPropertyValue)
        holder.binding.color.setOnClickListener {
            propertyColorValueSelected.notifySelectedColorValueChange(currentPropertyValue.propertyId)
            notifyItemChanged(position)
        }
    }
}
