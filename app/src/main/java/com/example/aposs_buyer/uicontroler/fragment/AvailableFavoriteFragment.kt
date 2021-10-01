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
import com.example.aposs_buyer.databinding.FragmentAvailableFavoriteBinding
import com.example.aposs_buyer.model.FavoriteProduct
import com.example.aposs_buyer.uicontroler.adapter.FavoriteRecyclerViewAdapter
import com.example.aposs_buyer.viewmodel.FavoriteViewModel


class AvailableFavoriteFragment : FavoriteRecyclerViewAdapter.FavoriteInterface, Fragment() {

    private lateinit var binding: FragmentAvailableFavoriteBinding

    private val viewModel: FavoriteViewModel by activityViewModels()

    private val adapter: FavoriteRecyclerViewAdapter = FavoriteRecyclerViewAdapter(this)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_available_favorite,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.allItems.adapter = adapter
        watchFavoriteItemChange()
        binding.viewModel = viewModel
        return binding.root
    }

    private fun watchFavoriteItemChange() {
        viewModel.availableProduct.observe(viewLifecycleOwner, { change ->
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
    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

}