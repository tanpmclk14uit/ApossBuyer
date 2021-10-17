package com.example.aposs_buyer.uicontroler.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ActivityCartSecondBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartSecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartSecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}