package com.example.aposs_buyer.viewmodel

import android.text.format.DateFormat
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.utils.Destiny
import com.example.aposs_buyer.utils.LoadingStatus
import com.example.aposs_buyer.utils.LunarConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CheckCompatibilityViewModel @Inject constructor(

): ViewModel() {
    // set up data for product
    val productName= MutableLiveData<String>()
    val productImage = MutableLiveData<Image>()
    val productNature = MutableLiveData<String>()
    // set up data for customer
    val customerBirthDate = MutableLiveData<String>()
    val customerGender = MutableLiveData<Boolean>()
    val customerNature = MutableLiveData<Destiny>()

    val messageGuild = MutableLiveData<String>()
    val status = MutableLiveData<LoadingStatus>()


    init {
        customerGender.value = true
    }
    fun setUpProductData(name: String?, image: Image?, nature: String?){
        productName.value = name?:"Loading name error"
        productImage.value = image?: Image("https://developer.android.com/codelabs/basic-android-kotlin-training-internet-images/img/467c213c859e1904.png")
        productNature.value = nature?:"Loading nature error"
    }

    fun isValidCustomerBirthDate(): Boolean{
        val formatter = SimpleDateFormat("d/M/yyyy", Locale.getDefault())
        formatter.isLenient = false
        if(customerBirthDate.value.isNullOrBlank()){
            messageGuild.value = "Vui lòng nhập hoặc chọn ngày tháng năm sinh!"
            status.value = LoadingStatus.Fail
            return false
        }
        return try{
            customerBirthDate.value?.let {
                formatter.parse(it)
            }
            status.value = LoadingStatus.Loading
            true
        }catch (e: Exception){
            messageGuild.value = "Vui lòng nhập ngày sinh theo định dạng d/m/yyyy"
            status.value = LoadingStatus.Fail
            false
        }
    }
    fun setCustomerNature(){
        val birthDay = customerBirthDate.value?:""
        if(birthDay.isNotBlank()){
            val lunarYear = LunarConverter.getInstance().getLunarYear(birthDay)
            runBlocking {
                customerNature.value = calculateNature(lunarYear, customerGender.value!!)
                status.value = LoadingStatus.Success
            }
        }
    }
    private fun calculateNature(lunarBirthYear: Int, isMale: Boolean): Destiny{
        val natureNumber: Int
        val input = makeInputLessThanTen(lunarBirthYear %100)
        return if(lunarBirthYear<2000){
            natureNumber = if(isMale){
                10 - input
            }else{
                makeInputLessThanTen(5 + input)
            }
            mapToDestinyForLunarBirthYear(natureNumber, isMale)
        }else{
            natureNumber = if(isMale){
                9-input
            }else{
                makeInputLessThanTen(6 + input)
            }
            mapToDestinyForLunarBirthYear(natureNumber, isMale)
        }
    }
    private  fun makeInputLessThanTen(input: Int): Int{
        var result = input
        while (result>9){
            val ten = result/10
            val divide = result%10
            result = ten + divide
        }
        return result
    }
    private fun mapToDestinyForLunarBirthYear(natureNumber: Int,isMale: Boolean): Destiny{
        return when(natureNumber){
            1 -> Destiny.Kham
            2-> Destiny.Khon
            3-> Destiny.Chan
            4 -> Destiny.Ton
            5 -> {
                if (isMale){
                    Destiny.Khon
                }else
                    Destiny.Can1
            }
            6 -> Destiny.Can
            7 -> Destiny.Doai
            8 -> Destiny.Can1
            9 -> Destiny.Ly
            else -> Destiny.Ly
        }
    }
}