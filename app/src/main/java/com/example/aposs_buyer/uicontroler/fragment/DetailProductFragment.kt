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
import com.example.aposs_buyer.databinding.FragmentDetailProductBinding
import com.example.aposs_buyer.viewmodel.DetailProductViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailProductFragment : Fragment() {

    private lateinit var binding: FragmentDetailProductBinding
    private val viewModel: DetailProductViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_product, container, false)
        val selectedProductId: Long? = arguments?.getLong("productID")
        binding.viewModel = viewModel
        if (selectedProductId != null) {
            viewModel.setSelectedProductId(selectedProductId)
        }
        binding.lifecycleOwner = this

        // Inflate the layout for this fragment
        return binding.root
    }


}

