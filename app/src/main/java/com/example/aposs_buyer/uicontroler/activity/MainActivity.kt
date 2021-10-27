package com.example.aposs_buyer.uicontroler.activity

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this;
        binding.bottomNavigation.setOnItemSelectedListener(mOnNavigationItemSelectedListener)
        navController = findNavController(R.id.fragment)

    }

    private var exit: Boolean = false

    override fun onBackPressed() {
        if (!exit) {
            Toast.makeText(this, "Click back again to exit", Toast.LENGTH_SHORT).show()
            exit = true
            Handler().postDelayed(
                {
                exit = false
                },
                2000
            )
        } else {
            finish()
        }
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    navController.navigate(R.id.homeFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.favorite -> {
                    navController.navigate(R.id.favoriteFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.message -> {
                    navController.navigate(R.id.messageFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.cart -> {
                    navController.navigate(R.id.cartFragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.person -> {
                    navController.navigate(R.id.personFragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
}