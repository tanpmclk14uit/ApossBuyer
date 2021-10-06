package com.example.aposs_buyer.uicontroler.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
            add(MeowBottomNavigation.Model(4, R.drawable.ic_cart_user_detail))
            add(MeowBottomNavigation.Model(5, R.drawable.ic_person))
        }
        binding.meowBottomNavigation.show(3, true)
        binding.meowBottomNavigation.setOnClickMenuListener {
            val navController = Navigation.findNavController(this, R.id.navHostFragment)
            navController.navigateUp()
            when(it.id){
                1 -> {
                    navController.navigate(R.id.favoriteFragment)
                }
                2->{
                    navController.navigate(R.id.messageFragment)
                }
                3-> {
                    navController.navigate(R.id.homeFragment)
                }
                4->{
                    navController.navigate(R.id.cartFragment)
                }
                else -> {
                    navController.navigate(R.id.personFragment)
                }
            }
        }
    }
}