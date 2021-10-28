package com.example.aposs_buyer.uicontroler.fragment

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentAddressBinding
import com.example.aposs_buyer.uicontroler.activity.AddressActivity
import com.example.aposs_buyer.uicontroler.activity.CartSecondActivity
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
    ): View {
        // Inflate the layout for this fragment
       binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_address, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        addressAdapter = AddressAdapter(this)
        binding.rcAddress.adapter = addressAdapter
        binding.rcAddress.layoutManager = LinearLayoutManager(binding.rcAddress.context, LinearLayoutManager.VERTICAL, false)
        setOnClick()
        return binding.root
    }

    override fun onClick(position: Int) {

    }

    override fun onEdit(addressId: Long) {
        findNavController().navigate(AddressFragmentDirections.actionAddressFragmentToAddressDialogFragment2(viewModel.getCurrentDefaultAddress(addressId)))
    }

    private fun setOnClick()
    {
        binding.tvAddNewAddress.setOnClickListener {
            findNavController().navigate(AddressFragmentDirections.actionAddressFragmentToAddressDialogFragment2(viewModel.getCreateAddress()))
        }
        binding.imgBack.setOnClickListener{
            requireActivity().onBackPressed()
        }
        binding.clCart.setOnClickListener {
            val intent = Intent(this.context, CartSecondActivity::class.java)
            startActivity(intent)
        }
    }


}