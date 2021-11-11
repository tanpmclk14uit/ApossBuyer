package com.example.aposs_buyer.uicontroler.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ActivityUserDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class
UserDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}