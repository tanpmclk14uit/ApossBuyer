package com.example.aposs_buyer.uicontroler.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import android.widget.ToggleButton
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ItemRakingBinding
import com.example.aposs_buyer.model.RankingProduct

class RankingViewPagerAdapter(
    private val onClickListener: OnClickListener
) :
    ListAdapter<RankingProduct, RankingViewPagerAdapter.RankingViewHolder>(DiffCallBack) {

    class OnClickListener(val clickListener: (id: Long) -> Unit) {
        fun onClick(id: Long) = clickListener(id)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<RankingProduct>() {
        override fun areItemsTheSame(oldItem: RankingProduct, newItem: RankingProduct): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: RankingProduct, newItem: RankingProduct): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class RankingViewHolder(var binding: ItemRakingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rankingProduct: RankingProduct) {
            binding.rankingProduct = rankingProduct
            binding.executePendingBindings()
        }
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemRakingBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_raking, parent, false)
        return RankingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(currentItem.id)
        }
    }
}