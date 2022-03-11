package com.example.aposs_buyer.uicontroler.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.ActivityCategoryBinding
import com.example.aposs_buyer.model.Category
import com.example.aposs_buyer.uicontroler.fragment.CategoryFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val category = intent.getParcelableExtra<Category>("category")?:Category(-1)
        findNavController(R.id.fragmentContainerView).setGraph(
            R.navigation.navigation_category,
            CategoryFragmentArgs(category).toBundle()
        )
    }
}