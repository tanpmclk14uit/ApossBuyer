package com.example.aposs_buyer.uicontroler.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentAddressBinding
import com.example.aposs_buyer.model.Address
import com.example.aposs_buyer.uicontroler.activity.CartSecondActivity
import com.example.aposs_buyer.uicontroler.adapter.AddressAdapter
import com.example.aposs_buyer.utils.DeliveryAddressStatus
import com.example.aposs_buyer.viewmodel.AddressViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressFragment : Fragment(), AddressAdapter.OnAddressCLickListener {

    private lateinit var binding: FragmentAddressBinding
    private val viewModel: AddressViewModel by activityViewModels()
    private lateinit var addressAdapter: AddressAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_address, container, false)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = viewModel

        setAddressRecycleView()
        observeStatus()
        setOnAddNewAddressClick()
        setOnBackClick()
        setOnCartClick()
        return binding.root
    }

    private fun setAddressRecycleView(){
        addressAdapter = AddressAdapter(this)
        binding.rcAddress.adapter = addressAdapter
    }

    private fun observeStatus(){
        viewModel.status.observe(viewLifecycleOwner) {
            if (viewModel.status.value!! == DeliveryAddressStatus.Success) {
                addressAdapter.submitList(viewModel.listAddress.value)
            }
        }
    }

    override fun onEdit(address: Address) {
        viewModel.newAddress.value = address
        viewModel.currentAddress = address.copy()
        findNavController().navigate(
            AddressFragmentDirections.actionAddressFragmentToAddressDialogFragment2()
        )
    }

    private fun setOnAddNewAddressClick() {
        binding.tvAddNewAddress.setOnClickListener {
            viewModel.newAddress.value = Address(-1)
            viewModel.currentAddress = Address(-1)
            findNavController().navigate(
                AddressFragmentDirections.actionAddressFragmentToAddressDialogFragment2()
            )
        }
    }

    private fun setOnBackClick() {
        binding.imgBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setOnCartClick() {
        binding.clCart.setOnClickListener {
            val intent = Intent(this.context, CartSecondActivity::class.java)
            startActivity(intent)
        }
    }
}