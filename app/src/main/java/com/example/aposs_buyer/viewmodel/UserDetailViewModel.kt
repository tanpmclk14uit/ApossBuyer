package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
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
        return Person(1, Image(imgURL3),"Phạm Minh","Tân","tan.lk16.cla@gmai.com", Date(2001, 7, 23), true)
    }

    fun isChangeFirstName(newFirstName: String): Boolean
    {
        Log.d("fuckkkk", (currentUser.value!!.firstName != newFirstName).toString())
        return currentUser.value!!.firstName != newFirstName
    }

    fun isChangeLastName(newLastName: String): Boolean
    {
        return currentUser.value!!.lastName != newLastName
    }

    fun isChangeEmail(newEmail: String): Boolean
    {
        return currentUser.value!!.email != newEmail
    }

    fun isChangeBirthday(newBirthDay: String): Boolean
    {
        return currentUser.value!!.getBirthdayByString() != newBirthDay
    }

    fun isChangeGender(newGender: String): Boolean
    {
        return currentUser.value!!.getGenderString() != newGender
    }

    fun onSave(newUserInfo: Person) {
        _currentUser.value = newUserInfo
    }

}