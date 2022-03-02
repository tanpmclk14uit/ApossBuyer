package com.example.aposs_buyer.utils

import java.util.regex.Pattern

object StringValidator {
    private fun isNameContainNumberOrSpecialCharacter(name: String): Boolean {
        val hasNumber: Boolean = Pattern.compile(
            "[0-9]"
        ).matcher(name).find()
        val hasSpecialCharacter: Boolean = Pattern.compile(
            "[!@#$%&.,\"':;?*()_+=|<>{}\\[\\]~-]"
        ).matcher(name).find()
        return hasNumber || hasSpecialCharacter
    }

    fun getNameError(name: String): String? {
        return if (name.isBlank()) {
            "Name can not empty"
        } else {
            return if (isNameContainNumberOrSpecialCharacter(name)) {
                "Name can't contain special character"
            } else {
                null
            }
        }
    }

    private fun isPhoneNumberRightFormat(str: String): Boolean {
        val regex = "(84|0[35789])+([0-9]{8})\\b".toRegex()
        return str.matches(regex)
    }

    fun getPhoneNumberError(cellNumber: String): String? {
        return if (cellNumber.isBlank()) {
            "Phone number is require!"
        } else {
            return if (!isPhoneNumberRightFormat(cellNumber)) {
                "Phone number in wrong format"
            } else {
                null
            }
        }
    }
}