package com.example.aposs_buyer.uicontroler.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentKindBinding
import com.example.aposs_buyer.uicontroler.activity.DetailProductActivity
import com.example.aposs_buyer.uicontroler.adapter.HomeProductAdapter
import com.example.aposs_buyer.viewmodel.ProductOfKindViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KindFragment() : Fragment() {

    private lateinit var binding: FragmentKindBinding

    private val viewModel: ProductOfKindViewModel by viewModels()
    private val args: KindFragmentArgs by navArgs()
    private lateinit var homeProductAdapter: HomeProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kind, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        if (args.kind.id != -1L) {
            viewModel.setSelectedKindIdAndName(args.kind.id, args.kind.name)
            viewModel.setSelectedProductsKind()
        }

        homeProductAdapter = HomeProductAdapter(HomeProductAdapter.OnClickListener {
            val intent = Intent(this.context, DetailProductActivity::class.java)
            intent.putExtra("productID", it)
            startActivity(intent)
        })

        binding.rcProducts.adapter = homeProductAdapter
        binding.rcProducts.layoutManager =
            GridLayoutManager(binding.rcProducts.context, 2, GridLayoutManager.VERTICAL, false)
        binding.imgBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        return binding.root
    }
}