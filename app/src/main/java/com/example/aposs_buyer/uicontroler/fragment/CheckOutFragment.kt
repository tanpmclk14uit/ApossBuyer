package com.example.aposs_buyer.uicontroler.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentCheckOutBinding
import com.example.aposs_buyer.uicontroler.adapter.BillingItemsAdapter
import com.example.aposs_buyer.viewmodel.CheckOutViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckOutFragment : Fragment() {

    private lateinit var binding: FragmentCheckOutBinding
    private val args: CheckOutFragmentArgs by navArgs()
    private val viewModel: CheckOutViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_check_out, container, false)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = viewModel

        setUpFirstState()
        binding.rcCheckOut.adapter = BillingItemsAdapter()
        binding.imgBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.btnConfirm.setOnClickListener {
            // add new order
        }
        binding.clCart.setOnClickListener {
            // go to select cart
        }
        binding.imgCart2.setOnClickListener {
            // go to select cart
        }
        binding.imgEditAddress.setOnClickListener {
            // go to select select address
        }
        return binding.root
    }

    private fun setUpFirstState() {
        // set up current order data
        viewModel.setCurrentOrder(args.currentOrder)
    }

}