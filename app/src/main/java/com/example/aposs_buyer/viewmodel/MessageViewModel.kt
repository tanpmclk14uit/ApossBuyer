package com.example.aposs_buyer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.MessageItem
import java.time.LocalDateTime

class MessageViewModel: ViewModel() {

    private val _lstMessageItem = MutableLiveData<List<MessageItem>>()
    val lstMessageItem: LiveData<List<MessageItem>> get() = _lstMessageItem

    init {
        loadMessageList()
    }
    fun loadMessageList()
    {
        _lstMessageItem.value = listOf(MessageItem(1, "Em tính ngày rồi, không thể có chuyện có bầu đâu!", LocalDateTime.now()),
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
}