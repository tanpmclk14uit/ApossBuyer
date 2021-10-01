package com.example.aposs_buyer.uicontroler.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentAllFavoriteBinding
import com.example.aposs_buyer.model.FavoriteProduct
import com.example.aposs_buyer.uicontroler.adapter.FavoriteRecyclerViewAdapter
import com.example.aposs_buyer.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllFavoriteFragment : FavoriteRecyclerViewAdapter.FavoriteInterface, Fragment() {

    private lateinit var binding: FragmentAllFavoriteBinding

    private val viewModel: FavoriteViewModel by activityViewModels()

    private val adapter: FavoriteRecyclerViewAdapter = FavoriteRecyclerViewAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_all_favorite, container, false)
        binding.lifecycleOwner = this
        binding.allItems.adapter = adapter
        watchFavoriteItemChange()
        binding.viewModel = viewModel
        return binding.root
    }


    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    private fun watchFavoriteItemChange() {
        viewModel.products.observe(viewLifecycleOwner, { change ->
            adapter.submitList(change)
            binding.allItems.recycledViewPool.clear()
        })
    }

    override fun removeFromFavorite(product: FavoriteProduct) {
        viewModel.removeFromFavoriteProduct(product)
    }

    override fun addToCart(product: FavoriteProduct) {
        viewModel.addAvailableProductToCart(product)
    }

}