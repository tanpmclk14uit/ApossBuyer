package com.example.aposs_buyer.uicontroler.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.aposs_buyer.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_main)

        supportActionBar?.hide()


        Handler().postDelayed(
            {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            },
            2000
        )
        
    }
}