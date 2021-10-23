package com.example.aposs_buyer.model

import java.util.*

data class Person(
    val id: Int,
    val image: Image,
    val name: String,
    val email: String,
    val birthday: Date,
    val gender: Boolean
){
    fun getBirthdayByString(): String
    {
        return "${birthday.date}-${monthFormat(birthday.month)}-${birthday.year}"
    }

    fun getGenderString(): String
    {
        if (gender) return "Male"
        return "Female"
    }

    object convert {
        fun genderToBool(gender: String): Boolean {
            return gender == "Male"
        }

        fun stringToDate(dateString: String): Date
        {
            val listOfDate = dateString.split(Regex("-"), 3)
            return Date(listOfDate[2].toInt(), monthNumFormat(listOfDate[1]), listOfDate[0].toInt())
        }
        private fun monthNumFormat(month: String): Int
        {
            if(month == "January")
                return 1;
            if(month == "February")
                return 2;
            if(month == "March")
                return 3;
            if(month == "April")
                return 4;
            if(month == "May")
                return 5;
            if(month == "June")
                return 6;
            if(month == "July")
                return 7;
            if(month == "August")
                return 8;
            if(month == "September")
                return 9;
            if(month == "October")
                return 10;
            if(month == "November")
                return 11;
            if(month == "December")
                return 12;

            //default should never happen
            return 1;
        }
    }

    private fun monthFormat(month: Int): String
    {
        if(month == 1)
            return "January";
        if(month == 2)
            return "February";
        if(month == 3)
            return "March";
        if(month == 4)
            return "April";
        if(month == 5)
            return "May";
        if(month == 6)
            return "June";
        if(month == 7)
            return "July";
        if(month == 8)
            return "August";
        if(month == 9)
            return "September";
        if(month == 10)
            return "October";
        if(month == 11)
            return "November";
        if(month == 12)
            return "December";

        //default should never happen
        return "January";
    }


}
