package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.entity.Account
import com.example.aposs_buyer.responsitory.database.AccountDatabase
import com.example.aposs_buyer.utils.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {

    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    var toastMessage: MutableLiveData<String> = MutableLiveData()
    var emailErrorMessage: String? = ""
    var passwordErrorMessage: String? = ""

    var loginState: MutableLiveData<LoginState> = MutableLiveData()

    fun onLoginClick() {
        if (email.value != null && password.value != null) {
            if (isValidEmail() && isValidPassword()) {
                Log.d(
                    "SignInViewModel",
                    "Email:" + email.value!! + "; Password:" + password.value!!
                )

                if (isRightEmailPassword()) {
                    loginState.value = LoginState.Success
                    toastMessage.value = "Login success"
                    Log.d(
                        "SignInViewModel",
                        "Success"
                    )

                } else {
                    loginState.value = LoginState.Wait
                    toastMessage.value = "Wrong email or password!"
                    Log.d(
                        "SignInViewModel",
                        "False"
                    )
                }
            } else {
                toastMessage.value = "Email or password is invalid!"
            }
        } else {
            toastMessage.value = "Email or password is invalid!"
        }
    }

    private fun isRightEmailPassword(): Boolean {
        loginState.value = LoginState.Loading
        return email.value == "admin@gmail.com" && password.value == "123456789"
    }


    private fun isEmailRightFormat(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }

    fun isValidPassword(): Boolean {
        return if (password.value!!.isBlank()) {
            passwordErrorMessage = "Password can not empty"
            false
        } else {
            if (password.value!!.count() < 8) {
                passwordErrorMessage = "Password least than 8 character"
                false
            } else {
                passwordErrorMessage = null
                true
            }
        }
    }

    fun isValidEmail(): Boolean {
        return if (email.value!!.isBlank()) {
            emailErrorMessage = "Email can not empty"
            false
        } else {
            if (isEmailRightFormat(email.value!!.trim())) {
                emailErrorMessage = null
                true
            } else {
                emailErrorMessage = "Invalid email"
                false
            }
        }
    }
}