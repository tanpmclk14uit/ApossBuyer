package com.example.aposs_buyer.uicontroler.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentCategoriesBinding
import com.example.aposs_buyer.uicontroler.activity.CartActivity
import com.example.aposs_buyer.uicontroler.activity.CategoryActivity
import com.example.aposs_buyer.uicontroler.adapter.DetailCategoryAdapter
import com.example.aposs_buyer.viewmodel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment(), DetailCategoryAdapter.ClickListener {

    private lateinit var binding: FragmentCategoriesBinding
    private val viewModel: CategoryViewModel by viewModels()
    private val categoryAdapter = DetailCategoryAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.rcCategory.adapter = categoryAdapter
        binding.rcCategory.layoutManager =
            LinearLayoutManager(binding.rcCategory.context, LinearLayoutManager.VERTICAL, false)
        binding.imgBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.clCart.setOnClickListener {
            startActivity(Intent(this.context, CartActivity::class.java))
        }
        return binding.root
    }

    override fun onClick(id: Long, name: String) {
        startActivity(Intent(this.context, CategoryActivity::class.java))
    }
}