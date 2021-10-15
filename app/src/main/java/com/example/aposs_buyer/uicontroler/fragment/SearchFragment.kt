package com.example.aposs_buyer.uicontroler.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentSearchBinding
import com.example.aposs_buyer.model.HomeProduct
import com.example.aposs_buyer.uicontroler.activity.DetailProductActivity
import com.example.aposs_buyer.uicontroler.adapter.HomeProductAdapter
import com.example.aposs_buyer.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), HomeProductAdapter.FavoriteInterface {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var homeProductAdapter: HomeProductAdapter
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_search, container, false)
        binding.lifecycleOwner=this
        homeProductAdapter = HomeProductAdapter(this, HomeProductAdapter.OnClickListener {
            val intent = Intent(this.context, DetailProductActivity::class.java)
            intent.putExtra("productID", it)
            startActivity(intent)
        })
        binding.viewModel = viewModel
        binding.rcSearch.adapter = homeProductAdapter
        binding.rcSearch.layoutManager = GridLayoutManager(binding.rcSearch.context, 2, GridLayoutManager.VERTICAL, false)
        viewModel.curentKeyWord.observe(viewLifecycleOwner, Observer {
            onSearchTextChange()
            homeProductAdapter.submitList(viewModel.listForDisplay.value)
            homeProductAdapter.notifyDataSetChanged()
        })
        return binding.root
    }

    override fun addToFavorite(product: HomeProduct) {
        viewModel.addToFavorite(product)
    }

    override fun removeFromFavorite(product: HomeProduct) {
        viewModel.removeFromFavorite(product)
    }

    fun onSearchTextChange()
    {
        viewModel.changeListDispaly()
    }
}