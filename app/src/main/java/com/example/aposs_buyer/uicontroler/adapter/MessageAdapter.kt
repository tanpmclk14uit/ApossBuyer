package com.example.aposs_buyer.uicontroler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ItemLeftSideMessageBinding
import com.example.aposs_buyer.databinding.ItemRightSideMessageBinding
import com.example.aposs_buyer.model.Category
import com.example.aposs_buyer.model.MessageItem
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class MessageAdapter: ListAdapter<MessageItem, RecyclerView.ViewHolder>(DiffCallBack) {

    companion object DiffCallBack : DiffUtil.ItemCallback<MessageItem>() {
        override fun areItemsTheSame(oldItem: MessageItem, newItem: MessageItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MessageItem, newItem: MessageItem): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class MessageRightViewHolder(private var binding:ItemRightSideMessageBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(messageItem: MessageItem) {
            binding.messageItem = messageItem
            binding.executePendingBindings()
        }
    }

    class MessageLeftViewHolder(private var binding:ItemLeftSideMessageBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(messageItem: MessageItem) {
            binding.messageItem = messageItem
            binding.executePendingBindings()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).id != 1L) 2 else 1

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            1->{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: ItemRightSideMessageBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_right_side_message, parent, false)
                MessageRightViewHolder(binding)
            }
            else->{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: ItemLeftSideMessageBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_left_side_message, parent, false)
                MessageLeftViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            1->{
                (holder as MessageRightViewHolder).bind(getItem(position))
            }
            else->{
                (holder as MessageLeftViewHolder).bind(getItem(position))
            }
        }
    }
}