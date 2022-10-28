package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.MessageItem
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random

class MessageViewModel: ViewModel() {

    private val _lstMessageItem = MutableLiveData<MutableList<MessageItem>>()
    val lstMessageItem: LiveData<MutableList<MessageItem>> get() = _lstMessageItem

    val newMessage = MutableLiveData<String>()

    val size= MutableLiveData<Int>()
    init {
        loadMessageList()
        size.value = _lstMessageItem.value!!.size
    }
    fun loadMessageList()
    {
        _lstMessageItem.value = mutableListOf(MessageItem(1, "A B C D E", LocalDateTime.now()),
            MessageItem(2, "A B C D E A B C D E A B C D E", LocalDateTime.now()),
            MessageItem(1, "A B C D EA B C D EA B C D EA B C D EA B C D E", LocalDateTime.now()),
            MessageItem(2, "A B C D E", LocalDateTime.now()),
            MessageItem(2, "A B C D EA B C D EA B C D E", LocalDateTime.now()),
            MessageItem(1, "A B C D E", LocalDateTime.now()),
            MessageItem(2, "oA B C D EA B C D EA B C D E", LocalDateTime.now()),
            MessageItem(2, "A B C D EA B C D EA B C D EA B C D EA B C D E", LocalDateTime.now()),
            MessageItem(1, "A B C D EA B C D E", LocalDateTime.now()),
            MessageItem(2, "A B C D EA B C D EA B C D EA B C D E", LocalDateTime.now()))
    }

    fun addMessage() {
        val random = Random.nextLong(1, 3)
        if (newMessage.value != null || newMessage.value != "") {
            _lstMessageItem.value!!.add(0 , MessageItem(random, newMessage.value!!, LocalDateTime.now()))
            size.value = _lstMessageItem.value!!.size
            Log.i("MessageViewModel", lstMessageItem.value!!.toString())
        }
    }

}