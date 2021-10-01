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

    init {
        loadMessageList()
    }
    fun loadMessageList()
    {
        _lstMessageItem.value = mutableListOf(MessageItem(1, "Em tính ngày rồi, không thể có chuyện có bầu đâu!", LocalDateTime.now()),
            MessageItem(2, "ok em, hen em tối nay 8h chỗ cũ nha. Nhưng nhớ uống thuốc em nhé", LocalDateTime.now()),
            MessageItem(1, "Em tính ngày rồi, không thể có chuyện có bầu đâu!", LocalDateTime.now()),
            MessageItem(2, "ok em, hen em tối nay 8h chỗ cũ nha. Nhưng nhớ uống thuốc em nhé", LocalDateTime.now()),
            MessageItem(2, "ok em, hen em tối nay 8h chỗ cũ nha. Nhưng nhớ uống thuốc em nhé", LocalDateTime.now()),
            MessageItem(1, "Em tính ngày rồi, không thể có chuyện có bầu đâu!", LocalDateTime.now()),
            MessageItem(2, "ok em, hen em tối nay 8h chỗ cũ nha. Nhưng nhớ uống thuốc em nhé", LocalDateTime.now()),
            MessageItem(2, "ok em, hen em tối nay 8h chỗ cũ nha. Nhưng nhớ uống thuốc em nhé", LocalDateTime.now()),
            MessageItem(1, "Em tính ngày rồi, không thể có chuyện có bầu đâu!", LocalDateTime.now()),
            MessageItem(2, "ok em, hen em tối nay 8h chỗ cũ nha. Nhưng nhớ uống thuốc em nhé", LocalDateTime.now()))
    }

    fun addMessage() {
        val random = Random.nextLong(1, 3)
        if (newMessage.value != null || newMessage.value != "") {
            val newListMessage: MutableList<MessageItem>
            newListMessage = _lstMessageItem.value!!
            newListMessage.add(0 , MessageItem(random, newMessage.value!!, LocalDateTime.now()))
            _lstMessageItem.value = newListMessage
            Log.i("MessageViewModel", lstMessageItem.value!!.size.toString())
        }
    }

}