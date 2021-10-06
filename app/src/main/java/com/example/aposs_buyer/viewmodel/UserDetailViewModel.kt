package com.example.aposs_buyer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor() :ViewModel() {

    private val _currentUser = MutableLiveData<Person>()
    val currentUser: LiveData<Person> get() = _currentUser

    init {
        _currentUser.value = loadCurrentUser()
    }

    private fun loadCurrentUser(): Person
    {
        val imgURL3 =
            "https://leep.imgix.net/2021/01/bong-cai-trang-giup-giam-can_001.jpg?auto=compress&fm=pjpg&ixlib=php-1.2.1"
        return Person(1, Image(imgURL3),"Phạm Minh Tân","tan.lk16.cla@gmai.com")
    }
}