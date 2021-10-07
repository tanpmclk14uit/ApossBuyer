package com.example.aposs_buyer.uicontroler.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import android.widget.ToggleButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.databinding.ItemProductBinding
import com.example.aposs_buyer.model.HomeProduct

class HomeProductAdapter(
    private val favoriteInterface: FavoriteInterface,
    private val onClickListener: OnClickListener
) :
    ListAdapter<HomeProduct, HomeProductAdapter.HomeProductViewHolder>(DiffCallBack) {

    class OnClickListener(val clickListener: (id: Long) -> Unit) {
        fun onClick(id: Long) = clickListener(id)
    }

    object DiffCallBack : DiffUtil.ItemCallback<HomeProduct>() {
        override fun areItemsTheSame(oldItem: HomeProduct, newItem: HomeProduct): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: HomeProduct, newItem: HomeProduct): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class HomeProductViewHolder(var binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: HomeProduct) {
            binding.product = product
            binding.executePendingBindings()
        }
    }

    interface FavoriteInterface {
        fun addToFavorite(product: HomeProduct)
        fun removeFromFavorite(product: HomeProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeProductViewHolder {
        return HomeProductViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: HomeProductViewHolder, position: Int) {
        val currentProduct = getItem(position)
        holder.bind(currentProduct)
        holder.binding.favorite.setOnClickListener {
            onFavoriteIconCLick(position, currentProduct, holder.binding.favorite, it.context)
        }
        holder.itemView.setOnClickListener {
            onClickListener.onClick(currentProduct.id)
        }
    }

    private fun onFavoriteIconCLick(
        position: Int,
        product: HomeProduct,
        favorite: ToggleButton,
        context: Context
    ) {
        if (favorite.isChecked) {
            Toast.makeText(context, "Add to favorite successfully", Toast.LENGTH_SHORT).show()
            favoriteInterface.addToFavorite(product)
        } else {
            Toast.makeText(context, "Remove from favorite successfully", Toast.LENGTH_SHORT)
                .show()
            favoriteInterface.removeFromFavorite(product)
        }
    }

}