package com.example.aposs_buyer.uicontroler.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ActivityAddressBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}