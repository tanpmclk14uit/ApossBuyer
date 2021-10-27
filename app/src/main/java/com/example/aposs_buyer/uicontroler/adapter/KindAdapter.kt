package com.example.aposs_buyer.uicontroler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ItemKindBinding
import com.example.aposs_buyer.model.Kind

class KindAdapter(var onClickListener: OnClickListenerInterface) :
    ListAdapter<Kind, KindAdapter.KindViewHolder>(DiffCallBack) {
    class KindViewHolder(
        val binding: ItemKindBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(kind: Kind) {
            binding.kind = kind
            binding.executePendingBindings()
        }
    }

    object DiffCallBack : DiffUtil.ItemCallback<Kind>() {
        override fun areItemsTheSame(oldItem: Kind, newItem: Kind): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Kind, newItem: Kind): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KindViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemKindBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_kind, parent, false)
        return KindViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KindViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.binding.root.setOnClickListener {
            onClickListener.onClick(getItem(position).id, getItem(position).name)
        }
    }

    interface OnClickListenerInterface {
        fun onClick(id: Long, name: String)
    }
}