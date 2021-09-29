package com.example.aposs_buyer.uicontroler.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.databinding.ItemProductBinding
import com.example.aposs_buyer.model.HomeProduct

class HomeProductAdapter:  ListAdapter<HomeProduct, HomeProductAdapter.HomeProductViewHolder>(DiffCallBack){

    object DiffCallBack: DiffUtil.ItemCallback<HomeProduct>() {
        override fun areItemsTheSame(oldItem: HomeProduct, newItem: HomeProduct): Boolean {
            return oldItem ===newItem
        }

        override fun areContentsTheSame(oldItem: HomeProduct, newItem: HomeProduct): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class HomeProductViewHolder(private var binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(product: HomeProduct){
            binding.product = product
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeProductViewHolder {
        return HomeProductViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: HomeProductViewHolder, position: Int) {
        val currentProduct = getItem(position)
        holder.bind(currentProduct)
    }
}