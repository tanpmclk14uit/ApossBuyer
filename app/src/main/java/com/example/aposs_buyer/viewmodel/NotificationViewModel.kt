package com.example.aposs_buyer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.Notification
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class NotificationViewModel @Inject constructor(): ViewModel() {

    private var _notifications: MutableLiveData<ArrayList<Notification>> = MutableLiveData()
    val notifications: LiveData<ArrayList<Notification>> get() = _notifications

    init {
        _notifications.value = loadNotifications()
    }
    private fun loadNotifications(): ArrayList<Notification>{
        val sampleNotification: ArrayList<Notification> = ArrayList()
        sampleNotification.add(Notification("Your order is delivering", "Delivering line text string with two actions. One to two lines is preferable on mobile.", Date()))
        sampleNotification.add(Notification("Your order is confirmed", "Confirmed line text string with two actions. One to two lines is preferable on mobile.", Date()))
        sampleNotification.add(Notification("Your order is finish", "Finish line text string with two actions. One to two lines is preferable on mobile.", Date()))
        sampleNotification.add(Notification("Your order is delivering", "Second Delivering line text string with two actions. One to two lines is preferable on mobile.", Date()))
        return sampleNotification
    }
}