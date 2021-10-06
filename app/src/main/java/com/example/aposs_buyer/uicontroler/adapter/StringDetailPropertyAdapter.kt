package com.example.aposs_buyer.uicontroler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.databinding.ItemStringPropertyBinding
import com.example.aposs_buyer.model.PropertyValue

class StringDetailPropertyAdapter (private val propertySelect: PropertyValueSelect) :
    ListAdapter<PropertyValue, StringDetailPropertyAdapter.StringDetailPropertyViewHolder>(
        DiffCallBack
    ) {
    interface PropertyValueSelect{
        fun notifySelectedValueChange(valueId: Long, propertyId: Long)
    }

    object DiffCallBack : DiffUtil.ItemCallback<PropertyValue>() {
        override fun areItemsTheSame(oldItem: PropertyValue, newItem: PropertyValue): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PropertyValue, newItem: PropertyValue): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class StringDetailPropertyViewHolder(val binding: ItemStringPropertyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentPropertyValue: PropertyValue) {
            binding.property = currentPropertyValue
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StringDetailPropertyViewHolder {
        return StringDetailPropertyViewHolder(
            ItemStringPropertyBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: StringDetailPropertyViewHolder, position: Int) {
        val currentPropertyValue = getItem(position)
        holder.bind(currentPropertyValue)
        holder.binding.propertyValue.setOnClickListener {
            propertySelect.notifySelectedValueChange(currentPropertyValue.id, currentPropertyValue.propertyId)
            notifyItemChanged(position)
        }
    }
}