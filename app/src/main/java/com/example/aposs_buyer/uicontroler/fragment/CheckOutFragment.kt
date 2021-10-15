package com.example.aposs_buyer.uicontroler.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentCheckOutBinding
import com.example.aposs_buyer.uicontroler.adapter.CheckOutAdapter
import com.example.aposs_buyer.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckOutFragment : Fragment() {


    private lateinit var binding: FragmentCheckOutBinding
    private val viewModel: CartViewModel by activityViewModels()
    private val checkOutAdapter = CheckOutAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_check_out, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.rcCheckOut.adapter = checkOutAdapter
        binding.rcCheckOut.layoutManager = LinearLayoutManager(binding.rcCheckOut.context, LinearLayoutManager.VERTICAL, false)
        binding.imgBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.btnConfirm.setOnClickListener {
            findNavController().navigate(CheckOutFragmentDirections.actionCheckOutFragmentToCheckOutDialogFragment())
        }
        return binding.root
    }
}