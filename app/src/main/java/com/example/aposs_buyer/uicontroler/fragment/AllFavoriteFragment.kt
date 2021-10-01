package com.example.aposs_buyer.uicontroler.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentAllFavoriteBinding
import com.example.aposs_buyer.model.HomeProduct
import com.example.aposs_buyer.uicontroler.adapter.HomeProductAdapter
import com.example.aposs_buyer.viewmodel.FavoriteViewModel
import com.example.aposs_buyer.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllFavoriteFragment : HomeProductAdapter.FavoriteInterface, Fragment() {

    private lateinit var binding: FragmentAllFavoriteBinding

    private val viewModel: FavoriteViewModel by viewModels()

    private val adapter: HomeProductAdapter = HomeProductAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_favorite, container, false)

        binding.lifecycleOwner = this
        binding.allItems.adapter = adapter
        watchFavoriteItemChange()
        binding.viewModel = viewModel
        return binding.root
    }
    private fun watchFavoriteItemChange(){
        viewModel.products.observe(viewLifecycleOwner, { change ->
            adapter.submitList(change)
        })
    }

    override fun addToFavorite(product: HomeProduct) {
       // not implement
    }

    override fun removeFromFavorite(product: HomeProduct) {
        viewModel.removeFromFavoriteProduct(product)
    }

}