package com.example.aposs_buyer.uicontroler.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aposs_buyer.databinding.ActivityCategoriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}