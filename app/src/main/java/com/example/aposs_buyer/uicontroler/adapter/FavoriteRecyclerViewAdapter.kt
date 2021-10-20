package com.example.aposs_buyer.uicontroler.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.databinding.ItemFavoriteBinding
import com.example.aposs_buyer.model.FavoriteProduct

class FavoriteRecyclerViewAdapter(private val favoriteInterface: FavoriteInterface, private val onClickListener: OnClickListener) :
    ListAdapter<FavoriteProduct, FavoriteRecyclerViewAdapter.FavoriteViewHolder>(DiffCallBack) {


    interface FavoriteInterface {
        fun removeFromFavorite(product: FavoriteProduct)
        fun addToCart(product: FavoriteProduct)
    }

    class FavoriteViewHolder(var binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteItem: FavoriteProduct) {
            binding.favoriteProduct = favoriteItem
            binding.executePendingBindings()
        }
    }

    open class OnClickListener(val clickListener: (id: Long) -> Unit) {
        fun onClick(id: Long) = clickListener(id)
    }

    object DiffCallBack : DiffUtil.ItemCallback<FavoriteProduct>() {
        override fun areItemsTheSame(oldItem: FavoriteProduct, newItem: FavoriteProduct): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: FavoriteProduct,
            newItem: FavoriteProduct
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
        holder.binding.favorite.setOnClickListener {
            onFavoriteIconCLick(position, currentItem, it.context)
        }
        holder.binding.cardAdder.setOnClickListener {
            onAddToCartClick(currentItem)
        }
        holder.itemView.setOnClickListener {
            onClickListener.onClick(currentItem.id)
        }
    }

    private fun onFavoriteIconCLick(
        position: Int,
        product: FavoriteProduct,
        context: Context
    ) {
        Toast.makeText(context, "Remove from favorite successfully", Toast.LENGTH_SHORT)
            .show()
        favoriteInterface.removeFromFavorite(product)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }

    private fun onAddToCartClick(
        product: FavoriteProduct,
    ) {
        favoriteInterface.addToCart(product)
    }
}