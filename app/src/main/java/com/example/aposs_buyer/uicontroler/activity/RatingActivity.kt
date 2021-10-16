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
//        binding.bottomNavigation.selectedItemId = R.id.page_1

//        binding.bottomNavigation.setOnNavigationItemReselectedListener {
//            val navController = Navigation.findNavController(this, R.id.fragmentContainerView4)
//                navController.setGraph(
//                    R.navigation.navigation_rating
//                )
//            when(it.itemId) {
//                com.example.aposs_buyer.R.id.item1 -> {
//                    navController.navigate(R.id.ratedFragment)
//                    true
//                }
//                com.example.aposs_buyer.R.id.item2 -> {
//                    navController.navigate(R.id.rateNowFragment)
//                    true
//                }
//                else -> false
//            }
//        }
    }

    private var ratedFragment =com.example.aposs_buyer.uicontroler.fragment.RatedFragment()
    private var rateNowFragment = com.example.aposs_buyer.uicontroler.fragment.RateNowFragment()
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.fragmentContainerView4)
        Toast.makeText(this, "ooooooooooooooooooooooooo", Toast.LENGTH_LONG).show()
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