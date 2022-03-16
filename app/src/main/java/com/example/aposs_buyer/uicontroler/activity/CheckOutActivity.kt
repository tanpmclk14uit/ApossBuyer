package com.example.aposs_buyer.uicontroler.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ActivityCheckOutBinding
import com.example.aposs_buyer.model.Order
import com.example.aposs_buyer.uicontroler.fragment.CheckOutFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckOutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckOutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckOutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentOrder = intent.getParcelableExtra<Order>("order")
        findNavController(R.id.fragmentCheckOut).setGraph(
            R.navigation.navigation_check_out,
            CheckOutFragmentArgs(currentOrder!!).toBundle()
        )
    }
}