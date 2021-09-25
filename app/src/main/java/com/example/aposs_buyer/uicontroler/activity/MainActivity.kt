package com.example.aposs_buyer.uicontroler.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNav()
    }

    private fun setUpNav(){
        binding.meowBottomNavigation.apply {
            add(MeowBottomNavigation.Model(1, R.drawable.ic_love))
            add(MeowBottomNavigation.Model(2, R.drawable.ic_message))
            add(MeowBottomNavigation.Model(3, R.drawable.ic_home))
            add(MeowBottomNavigation.Model(4, R.drawable.ic_cart))
            add(MeowBottomNavigation.Model(5, R.drawable.ic_person))
        }
        binding.meowBottomNavigation.show(3, true)
    }
}