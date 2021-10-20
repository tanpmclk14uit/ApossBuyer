package com.example.aposs_buyer.uicontroler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.databinding.ItemNotificationBinding
import com.example.aposs_buyer.model.Notification

class NotificationAdapter(private val notificationInterface: NotificationInterface): ListAdapter<Notification, NotificationAdapter.NotificationViewHolder>(DiffCallBack) {


    interface NotificationInterface{
        fun onSeeNowClick(id: Long)
    }
    class NotificationViewHolder(val binding: ItemNotificationBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(notification: Notification){
            binding.notification = notification
        }
    }
    object DiffCallBack: DiffUtil.ItemCallback<Notification>() {
        override fun areItemsTheSame(oldItem: Notification, newItem: Notification): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Notification, newItem: Notification): Boolean {
           return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder(ItemNotificationBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val currentNotification = getItem(position)
        holder.bind(currentNotification)
        holder.binding.seeNow.setOnClickListener {
            notificationInterface.onSeeNowClick(currentNotification.orderId)
        }
    }
}