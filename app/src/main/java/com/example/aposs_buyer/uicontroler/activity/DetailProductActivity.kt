package com.example.aposs_buyer.uicontroler.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ActivityDetailProductBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val selectedProductId: Long = intent.getLongExtra("productID", -1)
        val bundle: Bundle = Bundle()
        bundle.putLong("productID", selectedProductId)
        val navController = Navigation.findNavController(this, R.id.navDetailFragment)
        navController.navigateUp()
        navController.navigate(R.id.detailProductFragment, bundle)
    }
}