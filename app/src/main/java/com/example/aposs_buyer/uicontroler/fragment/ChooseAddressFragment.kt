package com.example.aposs_buyer.uicontroler.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentChooseAddressBinding
import com.example.aposs_buyer.model.Address
import com.example.aposs_buyer.uicontroler.activity.AddressActivity
import com.example.aposs_buyer.uicontroler.adapter.ChooseAddressAdapter
import com.example.aposs_buyer.viewmodel.AddressViewModel
import com.example.aposs_buyer.viewmodel.CheckOutViewModel

class ChooseAddressFragment : Fragment(), ChooseAddressAdapter.OnChooseAddress {

    private lateinit var binding: FragmentChooseAddressBinding
    private val addressViewModel: AddressViewModel by activityViewModels()
    private val checkOutViewModel: CheckOutViewModel by activityViewModels()
    private lateinit var chooseAddressAdapter: ChooseAddressAdapter
    private lateinit var currentAddress:Address
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_choose_address, container, false)
        binding.addressViewModel = addressViewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        setUpAddressAdapter()
        onBackButtonClick()
        onEditButtonClick()
        onSubmitClick()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        addressViewModel.loadUserAddress()
    }
    private fun onBackButtonClick(){
        binding.imgBack.setOnClickListener{
            requireActivity().onBackPressed()
        }
    }
    private fun onEditButtonClick(){
        binding.edit.setOnClickListener{
            startActivity(Intent(requireContext(), AddressActivity::class.java))
        }
    }
    private fun setUpAddressAdapter(){
        chooseAddressAdapter = ChooseAddressAdapter(this)
        binding.chooseAddress.adapter = chooseAddressAdapter
    }
    private fun onSubmitClick(){
        binding.submit.setOnClickListener {
            checkOutViewModel.setNewAddress(currentAddress)
            requireActivity().onBackPressed()
        }
    }

    override fun onChoose(address: Address) {
        currentAddress = address
    }

}