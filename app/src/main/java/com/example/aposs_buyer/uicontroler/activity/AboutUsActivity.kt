package com.example.aposs_buyer.uicontroler.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ActivityAboutUsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutUsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutUsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}