package com.example.aposs_buyer.uicontroler.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ActivityDetailProductBinding
import com.example.aposs_buyer.uicontroler.fragment.DetailProductFragmentArgs
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedProductId: Long = intent.getLongExtra("productID", -1)
        findNavController(R.id.fragmentContainerView)
            .setGraph(
                R.navigation.navigation_detail_product,
                DetailProductFragmentArgs(selectedProductId).toBundle(),
            )
    }
}