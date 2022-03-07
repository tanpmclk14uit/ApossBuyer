package com.example.aposs_buyer.uicontroler.fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentUserDetailBinding
import com.example.aposs_buyer.viewmodel.UserDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import com.example.aposs_buyer.model.Person.convert

@AndroidEntryPoint
class UserDetailFragment : Fragment() {

    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var binding: FragmentUserDetailBinding
    private val viewModel: UserDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_user_detail, container, false)
        binding.viewModel= viewModel
        binding.lifecycleOwner = this

        initDatePicker()

        val genderList = listOf("Male", "Female")
        val genderAdapter = ArrayAdapter(requireContext(), R.layout.gender_list_item, genderList)
        binding.actvGender.setAdapter(genderAdapter)
        isChange()
        setCheckingName()
        binding.btnSaveChanges.setOnClickListener {
            onSaveCick()
            binding.btnSaveChanges.isEnabled = false
        }
        binding.etBirthday.setText(getTodayDate())
        binding.etBirthday.setOnClickListener {
            openDatePicker()
        }

        binding.imgBack.setOnClickListener {
            this.requireActivity().onBackPressed()
        }

        binding.imgCart.setOnClickListener {
            // go to cart
        }
        return binding.root
    }


    private fun getTodayDate(): String? {
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        var month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]
        month += 1
        return makeStringDate(year, month, day)
    }
    fun initDatePicker()
    {
        var dateSetListener = DatePickerDialog.OnDateSetListener() { datePicker: DatePicker, year: Int, month: Int, day: Int ->
            var date: String = makeStringDate(year, month.plus(1), day)
            binding.etBirthday.setText(date)
        }

        var calendar = Calendar.getInstance()
        var year: Int = calendar.get(Calendar.YEAR)
        var month:Int =  calendar.get(Calendar.MONTH)
        var day: Int=  calendar.get(Calendar.DAY_OF_MONTH)

        val style: Int = AlertDialog.THEME_HOLO_LIGHT

        datePickerDialog = DatePickerDialog(binding.root.context, style,dateSetListener, year, month,day)
    }

    private fun makeStringDate(year: Int, month: Int, day: Int): String {
            return "$day-${monthFormat(month)}-$year"
    }
    fun isEmailValid(email: CharSequence?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    fun validateEmail()
    {
        if (!isEmailValid(binding.etEmail.text.toString()))
        binding.etEmail.error = "please enter an email format"
    }

    fun monthFormat(month: Int): String
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

    fun openDatePicker()
    {
        datePickerDialog.show()
    }

    fun legalToClick(isLegal: Boolean)
    {
        binding.btnSaveChanges.isEnabled = isLegal
    }

    fun onSaveCick()
    {
        val oldInfo = viewModel.currentUser.value
        val newUserInfo = com.example.aposs_buyer.model.Person(oldInfo!!.id, oldInfo!!.image, binding.etName.text.toString(),
            binding.etEmail.text.toString(), convert.stringToDate(binding.etBirthday.text.toString()),
        convert.genderToBool(binding.actvGender.text.toString()))
        viewModel.onSave(newUserInfo)
    }



    private fun isChange()
    {
        binding.etEmail.addTextChangedListener {
            validateEmail()
            if (binding.etEmail.error == null)
            {
                legalToClick(viewModel.isChangeEmail(binding.etEmail.text.toString()))
            }
        }
        binding.etBirthday.addTextChangedListener {
            legalToClick(viewModel.isChangeBirthday(it.toString()))
        }
        binding.etName.addTextChangedListener {
            viewModel.name.value = it.toString()
            legalToClick(viewModel.isChangeName(it.toString()))
        }
        binding.actvGender.addTextChangedListener {
            legalToClick(viewModel.isChangeGender(it.toString()))
        }
    }

    private fun setCheckingName(){
        viewModel.name.observe(this.viewLifecycleOwner,{
            viewModel.isValidName()
            binding.etName.error = viewModel.nameErrorMessage
        })
    }
}