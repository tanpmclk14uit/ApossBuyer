package com.example.aposs_buyer.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {

    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    var name: MutableLiveData<String> = MutableLiveData()
    var confirmPassword: MutableLiveData<String> = MutableLiveData()
    var cellNumber: MutableLiveData<String> = MutableLiveData()

    var toastMessage: MutableLiveData<String> = MutableLiveData()

    var emailErrorMessage: String? = ""
    var passwordErrorMessage: String? = ""
    var nameErrorMessage: String? = ""
    var confirmErrorMessage: String? = ""
    var cellNumberErrorMessage: String? = ""

    fun onSignUpClick() {
        if (email.value != null && password.value != null && name.value != null && confirmPassword.value != null && cellNumber.value != null) {
            if (isValidName() && isValidEmail() && isValidPassword() && isValidConfirmPassword() && isValidPhoneNumber()) {
                
                Log.d(
                    "SignInViewModel",
                    "Name:" + name.value!! + "; Email:" + email.value!! + "; Password:" + password.value!! + "; Confirm password:" + confirmPassword.value!! + "; Cell phone:" + cellNumber.value!!
                )
            } else {
                toastMessage.value = "One ore more filed is invalid!"
            }
        } else {
            toastMessage.value = "One ore more filed is blank!"
        }
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

    private fun isNameContainNumberOrSpecialCharacter(name: String): Boolean {
        val hasNumber: Boolean = Pattern.compile(
            "[0-9]"
        ).matcher(name).find()
        val hasSpecialCharacter: Boolean = Pattern.compile(
            "[!@#$%&.,\"':;?*()_+=|<>?{}\\[\\]~-]"
        ).matcher(name).find()
        return hasNumber || hasSpecialCharacter
    }

    fun isValidName(): Boolean {
        return if (name.value!!.isBlank()) {
            nameErrorMessage = "Name can not empty"
            false
        } else {
            return if (isNameContainNumberOrSpecialCharacter(name.value!!)) {
                nameErrorMessage = "Name can't contain special character"
                false
            } else {
                nameErrorMessage = null
                true
            }
        }
    }

    fun isValidConfirmPassword(): Boolean {
        return if (confirmPassword.value!!.isBlank()) {
            confirmErrorMessage = "Confirm password can not empty"
            false
        } else {
            return if (confirmPassword.value!! != password.value!!) {
                confirmErrorMessage = "Password and Confirm Password must be one"
                false
            } else {
                confirmErrorMessage = null
                true
            }
        }
    }

    private fun isPhoneNumberRightFormat(str: String): Boolean {
        val regex = "(84|0[3|5|7|8|9])+([0-9]{8})\\b".toRegex()
        return str.matches(regex)
    }

    fun isValidPhoneNumber(): Boolean {
        return if (cellNumber.value!!.isBlank()) {
            cellNumberErrorMessage = "Phone number is require!"
            false
        } else {
            return if (!isPhoneNumberRightFormat(cellNumber.value!!)) {
                cellNumberErrorMessage = "Phone number in wrong format"
                false
            } else {
                cellNumberErrorMessage = null
                true
            }
        }
    }


}