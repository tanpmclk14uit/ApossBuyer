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
import com.example.aposs_buyer.model.Category
import com.example.aposs_buyer.responsitory.database.AccountDatabase
import com.example.aposs_buyer.uicontroler.activity.CartActivity
import com.example.aposs_buyer.uicontroler.activity.CategoryActivity
import com.example.aposs_buyer.uicontroler.activity.LoginActivity
import com.example.aposs_buyer.uicontroler.adapter.DetailCategoryAdapter
import com.example.aposs_buyer.viewmodel.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding
    private val viewModel: CategoriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container, false)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = viewModel

        setUpAppBar()
        setUpCategories()
        return binding.root
    }

    private fun setUpAppBar() {
        // set back button
        binding.imgBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        // set icon cart
        binding.clCart.setOnClickListener {
            if (isUserLoggedIn()) {
                startActivity(Intent(this.context, CartActivity::class.java))
            } else {
                startActivity(Intent(this.context, LoginActivity::class.java))
            }
        }
    }

    private fun isUserLoggedIn(): Boolean {
        return AccountDatabase.getInstance(this.requireContext()).accountDao.getAccount() != null
    }

    private fun setUpCategories() {
        // create adapter
        val categoryAdapter = DetailCategoryAdapter(DetailCategoryAdapter.OnItemClickListener {
            // set on item click listener
            val currentCategory = Category(it.id, it.name)
            val intent = Intent(this.context, CategoryActivity::class.java)
            intent.putExtra("category", currentCategory)
            startActivity(intent)
        })
        binding.rcCategory.adapter = categoryAdapter
    }
}