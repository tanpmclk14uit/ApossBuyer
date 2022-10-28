package com.example.aposs_buyer.uicontroler.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.example.aposs_buyer.responsitory.database.AccountDatabase
import com.example.aposs_buyer.uicontroler.activity.CartActivity
import com.example.aposs_buyer.uicontroler.activity.LoginActivity
import com.example.aposs_buyer.uicontroler.adapter.AddressAdapter
import com.example.aposs_buyer.utils.LoadingStatus
import com.example.aposs_buyer.viewmodel.AddressViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressFragment : Fragment() {

    private var binding: FragmentAddressBinding? = null
    private val viewModel: AddressViewModel by activityViewModels()
    private lateinit var addressAdapter: AddressAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_address, container, false)
        binding?.lifecycleOwner = this.viewLifecycleOwner
        binding?.viewModel = viewModel

        setAddressRecycleView()
        observeStatus()
        setOnAddNewAddressClick()
        setOnBackClick()
        setOnCartClick()
        return binding?.root!!
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.rcAddress?.adapter = null
        binding = null
    }


    private fun setAddressRecycleView() {
        addressAdapter = AddressAdapter(object: AddressAdapter.OnAddressCLickListener{
            override fun onEdit(address: Address) {
                viewModel.newAddress.value = address
                viewModel.currentAddress = address.copy()
                findNavController().navigate(
                    AddressFragmentDirections.actionAddressFragmentToAddressDialogFragment2()
                )
            }

        })
        binding?.rcAddress?.adapter = addressAdapter
    }

    private fun observeStatus() {
        viewModel.status.observe(viewLifecycleOwner) {
            if (viewModel.status.value!! == LoadingStatus.Success) {
                addressAdapter.submitList(viewModel.listAddress.value)
            }
        }
    }


    private fun setOnAddNewAddressClick() {
        binding?.tvAddNewAddress?.setOnClickListener {
            viewModel.newAddress.value = Address(-1)
            viewModel.currentAddress = Address(-1)
            findNavController().navigate(
                AddressFragmentDirections.actionAddressFragmentToAddressDialogFragment2()
            )
        }
    }

    private fun setOnBackClick() {
        binding?.imgBack?.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setOnCartClick() {
        binding?.clCart?.setOnClickListener {
            if (isUserLoggedIn()) {
                startActivity(Intent(this.context, CartActivity::class.java))
            } else {
                startActivity(Intent(this.context, LoginActivity::class.java))
            }
        }
    }

    private fun isUserLoggedIn(): Boolean {
        return AccountDatabase.getInstance(this.requireContext()).accountDao.getAccount() != null
    }
}