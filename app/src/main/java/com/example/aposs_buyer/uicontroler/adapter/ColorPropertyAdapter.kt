package com.example.aposs_buyer.uicontroler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.databinding.ItemProductColorPropertyBinding
import com.example.aposs_buyer.model.ProductDetailProperty

class ColorPropertyAdapter(private val propertyColorValueSelected: ColorDetailPropertyAdapter.PropertyColorValueSelected) :
    ListAdapter<ProductDetailProperty, ColorPropertyAdapter.ColorPropertyViewHolder>(DiffCallBack) {


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

    class ColorPropertyViewHolder(
        private val binding: ItemProductColorPropertyBinding,
        private val propertyColorValueSelected: ColorDetailPropertyAdapter.PropertyColorValueSelected
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentProperty: ProductDetailProperty) {
            binding.colorProperty.adapter = ColorDetailPropertyAdapter(propertyColorValueSelected)
            binding.property = currentProperty
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorPropertyViewHolder {
        return ColorPropertyViewHolder(
            ItemProductColorPropertyBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            ),
            propertyColorValueSelected
        )
    }

    override fun onBindViewHolder(holder: ColorPropertyViewHolder, position: Int) {
        val currentProperty = getItem(position)
        holder.bind(currentProperty)
    }

}