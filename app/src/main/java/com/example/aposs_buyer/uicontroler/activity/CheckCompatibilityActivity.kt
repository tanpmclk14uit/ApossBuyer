package com.example.aposs_buyer.uicontroler.activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.icu.util.Calendar
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ActivityCheckCompatibilityBinding
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.viewmodel.CheckCompatibilityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckCompatibilityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckCompatibilityBinding

    //private val tag = "CheckCompatibilityActivity"
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
        binding.dateOfBirth.setOnTouchListener { _, motionEvent ->
            val drawableEnd = 2;
            if(motionEvent.action == MotionEvent.ACTION_UP) {
                if(motionEvent.rawX >= (binding.dateOfBirth.right - binding.dateOfBirth.compoundDrawables[drawableEnd].bounds.width())) {
                    val datePickerDialog = DatePickerDialog(
                        this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener,
                        year,
                        month,
                        day
                    )
                    datePickerDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    datePickerDialog.show()
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }
    }

    private fun setNatureColor() {
        viewModel.productNature.observe(this) {
            var natureColor = ContextCompat.getColor(this, R.color.black)
            when (it) {
                "Kim" -> {
                    natureColor = ContextCompat.getColor(this, R.color.kim)
                }
                "Mộc" -> {
                    natureColor = ContextCompat.getColor(this, R.color.moc)
                }
                "Thủy" -> {
                    natureColor = ContextCompat.getColor(this, R.color.thuy)
                }
                "Hỏa" -> {
                    natureColor = ContextCompat.getColor(this, R.color.hoa)
                }
                "Thổ" -> {
                    natureColor = ContextCompat.getColor(this, R.color.tho)
                }
            }
            binding.nature.setTextColor(natureColor)
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
