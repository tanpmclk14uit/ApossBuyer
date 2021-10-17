package com.example.aposs_buyer.uicontroler.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.example.aposs_buyer.databinding.ActivityRatingBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.appcompat.app.ActionBar
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.aposs_buyer.R
import com.example.aposs_buyer.uicontroler.fragment.DetailProductFragmentArgs
import com.example.aposs_buyer.uicontroler.fragment.RateNowFragment
import com.example.aposs_buyer.uicontroler.fragment.RatedFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


@AndroidEntryPoint
class RatingActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding =  ActivityRatingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.fragmentContainerView4)
        return when(item.itemId) {
                com.example.aposs_buyer.R.id.page_1 -> {
                    navController.navigate(R.id.ratedFragment)
                    true
                }
                com.example.aposs_buyer.R.id.page_2 -> {
                    navController.navigate(R.id.rateNowFragment)
                    true
                }
                else -> true
            }
    }
}