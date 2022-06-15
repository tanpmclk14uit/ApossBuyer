package com.example.aposs_buyer.uicontroler.activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ActivityCheckCompatibilityBinding
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.utils.Destiny
import com.example.aposs_buyer.utils.LoadingStatus
import com.example.aposs_buyer.utils.Nature
import com.example.aposs_buyer.viewmodel.CheckCompatibilityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckCompatibilityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckCompatibilityBinding

    private val tag = "CheckCompatibilityActivity"
    private val viewModel: CheckCompatibilityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckCompatibilityBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        setUpProductData()
        setButtonBack()
        setNatureColor()
        setUpBirthDatePicker()
        setUpGenderAutomationText()
        setUpSeeNow()
        setCustomerNatureColor()
        setUpSuitableLevel()
    }
    private fun setUpSeeNow(){
        binding.seeNow.setOnClickListener {
            if(viewModel.isValidCustomerBirthDate()) {
                viewModel.setCustomerNature()
            }
        }
    }
    private fun setUpSuitableLevel(){
        viewModel.suitableRecommend.observe(this){
            when(it.suitableLever.value){
                1 -> binding.suitTableLevel.setImageResource(R.drawable.tuongkhac)
                2-> binding.suitTableLevel.setImageResource(R.drawable.khonganhhuong)
                3-> binding.suitTableLevel.setImageResource(R.drawable.chengu)
                4 -> binding.suitTableLevel.setImageResource(R.drawable.tuonghop)
            }
        }
    }
    @SuppressLint("SetTextI18n")
    private fun setUpGenderAutomationText() {
        val genderList = listOf("Nam", "Nữ")
        val genderAdapter = ArrayAdapter(this, R.layout.gender_list_item, genderList)
        binding.actvGender.setAdapter(genderAdapter)
        binding.actvGender.setText("Nam", false)
        binding.actvGender.addTextChangedListener {
            viewModel.status.value = LoadingStatus.Init
            viewModel.customerGender.value = it.toString() == "Nam"
            // clear data
        }
    }

    @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
    private fun setUpBirthDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val setListener: DatePickerDialog.OnDateSetListener =
            DatePickerDialog.OnDateSetListener { _, choseYear, choseMonth, choseDay ->
                binding.dateOfBirth.setText("$choseDay/${choseMonth + 1}/$choseYear")
            }
        // set on select date click
        binding.dateOfBirth.setOnTouchListener { _, motionEvent ->
            val drawableEnd = 2
            if(motionEvent.action == MotionEvent.ACTION_UP) {
                if(motionEvent.rawX >= (binding.dateOfBirth.right - binding.dateOfBirth.compoundDrawables[drawableEnd].bounds.width())) {
                    val datePickerDialog = DatePickerDialog(
                        this,
                        setListener,
                        year,
                        month,
                        day
                    )
                    datePickerDialog.show()
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }
        // set on text change
        binding.dateOfBirth.doAfterTextChanged {
            viewModel.status.value = LoadingStatus.Init
            // clear data
        }
    }

    private fun setNatureColor() {
        viewModel.productNature.observe(this) {
            var natureColor = ContextCompat.getColor(this, R.color.black)
            when (it) {
                Nature.Kim -> {
                    natureColor = ContextCompat.getColor(this, R.color.kim)
                }
                Nature.Moc -> {
                    natureColor = ContextCompat.getColor(this, R.color.moc)
                }
                Nature.Thuy -> {
                    natureColor = ContextCompat.getColor(this, R.color.thuy)
                }
                Nature.Hoa -> {
                    natureColor = ContextCompat.getColor(this, R.color.hoa)
                }
                Nature.Tho -> {
                    natureColor = ContextCompat.getColor(this, R.color.tho)
                }
            }
            binding.nature.setTextColor(natureColor)
        }
    }
    @Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA")
    private fun setCustomerNatureColor() {
        viewModel.customerDestiny.observe(this) {
            var natureColor = ContextCompat.getColor(this, R.color.black)
            when (it) {
                Destiny.Can, Destiny.Doai -> {
                    natureColor = ContextCompat.getColor(this, R.color.kim)
                }
                Destiny.Chan, Destiny.Ton -> {
                    natureColor = ContextCompat.getColor(this, R.color.moc)
                }
                Destiny.Kham -> {
                    natureColor = ContextCompat.getColor(this, R.color.thuy)
                }
                Destiny.Ly -> {
                    natureColor = ContextCompat.getColor(this, R.color.hoa)
                }
                Destiny.Can1, Destiny.Khon -> {
                    natureColor = ContextCompat.getColor(this, R.color.tho)
                }
            }
            binding.customerNature.setTextColor(natureColor)
        }
    }

    private fun setUpProductData() {
        val productName = intent.getStringExtra("ProductName")
        val productImage = intent.getParcelableExtra<Image>("ProductImage")
        val productNature = intent.getStringExtra("ProductNature")
        viewModel.setUpProductData(productName, productImage, productNature)
    }

    private fun setButtonBack() {
        binding.back.setOnClickListener {
            onBackPressed()
            finish()
        }
    }
}
