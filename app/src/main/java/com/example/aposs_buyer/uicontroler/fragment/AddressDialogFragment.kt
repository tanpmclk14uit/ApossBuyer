package com.example.aposs_buyer.uicontroler.fragment

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentAddressDialogBinding
import com.example.aposs_buyer.model.Address
import com.example.aposs_buyer.viewmodel.AddressDialogViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel


@AndroidEntryPoint
class AddressDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddressDialogBinding
    private val viewModel: AddressDialogViewModel by viewModels()
    private val args: AddressDialogFragmentArgs by navArgs()

    //override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_address_dialog, container, false)
        binding.viewModel = viewModel
        viewModel.address.value = args.defaultAddress
        viewModel.name.value = viewModel.address.value!!.name
        viewModel.cellNumber.value = viewModel.address.value!!.phoneNumber
        addGenderList()
        setOnChange()
        checkButtonMatchDialog()

        binding.btnEditAddAddress.setOnClickListener {
            onClickEditOrAdd()
        }
        onAddNewAddress()
        setCheckingCellPhone()
        setCheckingName()
        return binding.root
    }

    private fun checkButtonMatchDialog()
    {
        if( args.defaultAddress.id == 0L) {
            binding.btnEditAddAddress.text = "Add new address"
        }
        else binding.btnEditAddAddress.text = "Edit"
        if (binding.btnEditAddAddress.text == "Edit") binding.btnEditAddAddress.isEnabled = false
    }

    private fun addGenderList()
    {
        val genderList = listOf("Male", "Female")
        val genderAdapter = ArrayAdapter(requireContext(), R.layout.gender_list_item, genderList)
        binding.actvGender.setAdapter(genderAdapter)
    }

    private fun onAddNewAddress()
    {
        if (binding.actvCity.text.toString() != "" && binding.actvDistrict.text.toString() != ""
            && binding.actvGender.text.toString() != "" && binding.actvWard.text.toString() != ""
            && binding.etName.text.toString() != "" && binding.etPhone.text.toString() != ""
            && binding.etAddressLane.text.toString()!="") {
            val newAddress = Address(args.defaultAddress.id,binding.etName.text.toString(),binding.actvGender.text.toString()=="Male",
                binding.etPhone.text.toString(), binding.actvCity.text.toString(),
                binding.actvDistrict.text.toString(), binding.actvWard.text.toString(),
                binding.etAddressLane.text.toString(), false)
            viewModel.onAddNewAddress(newAddress)
            findNavController().navigate(AddressDialogFragmentDirections.actionAddressDialogFragment2ToAddressFragment())
        }
        else {
            Toast.makeText(this.context, "Please enter full information", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onClickEditOrAdd()
    {
        if (binding.btnEditAddAddress.text == "Edit")
        {
            val newAddress = Address(args.defaultAddress.id,binding.etName.text.toString(),binding.actvGender.text.toString()=="Male",
                                    binding.etPhone.text.toString(), binding.actvCity.text.toString(),
                                     binding.actvDistrict.text.toString(), binding.actvWard.text.toString(),
                                    binding.etAddressLane.text.toString(), false)
            onUpdateAddress(newAddress)
            findNavController().navigate(AddressDialogFragmentDirections.actionAddressDialogFragment2ToAddressFragment())
        }
        else onAddNewAddress()
    }

    private fun onUpdateAddress(address: Address)
    {
        viewModel.onUpdateAddress(address)
    }

    private fun havingLegalToClick(isLegal: Boolean)
    {
        binding.btnEditAddAddress.isEnabled =  isLegal
    }

    private fun setCheckingName(){
        viewModel.name.observe(this.viewLifecycleOwner,{
            viewModel.isValidName()
            binding.etName.error = viewModel.nameErrorMessage
        })
    }

    private fun setCheckingCellPhone(){
        viewModel.cellNumber.observe(this.viewLifecycleOwner,{
            viewModel.isValidPhoneNumber()
            binding.etPhone.error = viewModel.cellNumberErrorMessage
        })
    }

    fun setOnChange() {
        if (binding.btnEditAddAddress.text == "Edit") {
            binding.actvCity.addTextChangedListener {
                havingLegalToClick(viewModel.isChangeCity(it.toString()))
            }
            binding.actvGender.addTextChangedListener {
                havingLegalToClick(viewModel.isChangeGender(it.toString()))
            }
            binding.actvDistrict.addTextChangedListener {
                havingLegalToClick(viewModel.isChangeDistrict(it.toString()))
            }
            binding.actvWard.addTextChangedListener {
                havingLegalToClick(viewModel.isChangeWard(it.toString()))
            }
            binding.etAddressLane.addTextChangedListener {
                havingLegalToClick(viewModel.isChangeAddressLane(it.toString()))
            }
            binding.etName.addTextChangedListener {
                havingLegalToClick(viewModel.isChangeName(it.toString()))
            }
            binding.etPhone.addTextChangedListener {
                havingLegalToClick(viewModel.isChangePhone(it.toString()))
            }
        }
    }
}