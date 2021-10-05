package com.example.aposs_buyer.uicontroler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.databinding.ItemProductStringPropertyBinding
import com.example.aposs_buyer.model.ProductDetailProperty

class StringPropertyAdapter :
    ListAdapter<ProductDetailProperty, StringPropertyAdapter.StringPropertyViewHolder>(DiffCallBack) {

    object DiffCallBack : DiffUtil.ItemCallback<ProductDetailProperty>() {
        override fun areItemsTheSame(
            oldItem: ProductDetailProperty,
            newItem: ProductDetailProperty
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: ProductDetailProperty,
            newItem: ProductDetailProperty
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }

    class StringPropertyViewHolder(private val binding: ItemProductStringPropertyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentProperty: ProductDetailProperty) {
            binding.stringProperty.adapter = StringDetailPropertyAdapter()
            binding.property = currentProperty
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringPropertyViewHolder {
        return StringPropertyViewHolder(
            ItemProductStringPropertyBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: StringPropertyViewHolder, position: Int) {
        val currentProperty = getItem(position)
        holder.bind(currentProperty)
    }
}