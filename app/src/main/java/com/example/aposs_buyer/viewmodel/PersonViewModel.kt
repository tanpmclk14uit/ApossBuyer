package com.example.aposs_buyer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(): ViewModel() {

    private val _person = MutableLiveData<Person>()
    val person: LiveData<Person> get() = _person

    val isSignIn = MutableLiveData<Boolean>()

    init {
        _person.value = loadPerson()
        isSignIn.value = _person.value!!.id != 0
    }

    fun loadPerson(): Person
    {
        val imgURL3 =
            "https://leep.imgix.net/2021/01/bong-cai-trang-giup-giam-can_001.jpg?auto=compress&fm=pjpg&ixlib=php-1.2.1"
        return Person(1, Image(imgURL3),"Phạm Minh","Tân","tan.lk16.cla@gmai.com", Date(2001, 7,23), true)
//        return Person(0, Image(""), "", "")
    }
}