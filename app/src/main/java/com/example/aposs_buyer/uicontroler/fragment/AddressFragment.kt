package com.example.aposs_buyer.uicontroler.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentAddressBinding
import com.example.aposs_buyer.uicontroler.adapter.AddressAdapter
import com.example.aposs_buyer.viewmodel.AddressViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressFragment : Fragment(), AddressAdapter.OnAddressCLickListener {

    private lateinit var binding: FragmentAddressBinding
    private val viewModel: AddressViewModel by viewModels()
    private lateinit var addressAdapter: AddressAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_address, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        addressAdapter = AddressAdapter(this)
        binding.rcAddress.adapter = addressAdapter
        binding.rcAddress.layoutManager = LinearLayoutManager(binding.rcAddress.context, LinearLayoutManager.VERTICAL, false)
        binding.btnEditDefault.setOnClickListener {
            findNavController().navigate(AddressFragmentDirections.actionAddressFragmentToAddressDialogFragment2(viewModel.getCurrentDefaultAddress()))
        }
        binding.tvAddNewAddress.setOnClickListener {
            findNavController().navigate(AddressFragmentDirections.actionAddressFragmentToAddressDialogFragment2(viewModel.getCreateAddress()))
        }
        return binding.root
    }

    override fun onClick(position: Int) {
        viewModel.onChangeDefault(position)
        addressAdapter.notifyDataSetChanged()
    }
}