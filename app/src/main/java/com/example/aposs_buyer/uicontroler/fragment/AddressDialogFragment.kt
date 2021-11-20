package com.example.aposs_buyer.uicontroler.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentAddressDialogBinding
import com.example.aposs_buyer.model.Address
import com.example.aposs_buyer.model.Province
import com.example.aposs_buyer.viewmodel.AddressDialogViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddressDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddressDialogBinding
    private val viewModel: AddressDialogViewModel by viewModels()
    private val args: AddressDialogFragmentArgs by navArgs()

    private lateinit var provinceAdapter: ArrayAdapter<String>
    private lateinit var districtAdapter: ArrayAdapter<String>
    //override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_address_dialog, container, false)
        binding.viewModel = viewModel
        viewModel.address.value = args.defaultAddress
        viewModel.name.value = viewModel.address.value!!.name
        viewModel.cellNumber.value = viewModel.address.value!!.phoneNumber
        provinceAdapter = ArrayAdapter(requireContext(), R.layout.province_list_item, arrayListOf())
        binding.actvCity.setAdapter(provinceAdapter)
        districtAdapter = ArrayAdapter(requireContext(), R.layout.province_list_item, arrayListOf())
        binding.actvDistrict.setAdapter(districtAdapter)
        addGenderList()
        setOnChange()
        checkButtonMatchDialog()
        binding.btnEditAddAddress.setOnClickListener {
            onClickEditOrAdd()
        }
        binding.btnDelete.setOnClickListener {
            if (binding.btnDelete.text == "Cancel")
            {
                findNavController().navigate(AddressDialogFragmentDirections.actionAddressDialogFragment2ToAddressFragment())
            }
            else {
                onOpenDeleteDialog()
            }
        }
        viewModel.listProvince.observe(viewLifecycleOwner, Observer {
            addProvinceList()
        })
        binding.actvCity.setOnItemClickListener{ adapterView, view, i, l ->
            viewModel.loadDistrictByProvince(idOfProvince(i))
            Log.d("choosed the province", idOfProvince(i).toString())
        }
        viewModel.listDistrict.observe(viewLifecycleOwner, Observer {
            addDistrictList()
            Log.d("choosed the province", viewModel.listDistrict.toString())
        })
        onAddNewAddress()
        setCheckingCellPhone()
        setCheckingName()
        return binding.root
    }
    private fun onOpenDeleteDialog()
    {
        val dialogDelete = this.context?.let { Dialog(it) }
        dialogDelete?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogDelete?.setContentView(R.layout.delete_permission_dialog)

        val window = dialogDelete?.window ?: return

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val windowAttribute = window.attributes

        windowAttribute.gravity = Gravity.CENTER
        window.attributes= windowAttribute

        dialogDelete.setCancelable(false)

        val btnDelete: AppCompatButton = dialogDelete.findViewById(R.id.btn_delete)
        val btnCancel: AppCompatButton = dialogDelete.findViewById(R.id.btn_cancel)

        btnCancel.setOnClickListener {
            dialogDelete.dismiss()
        }

//        btnDelete.setOnClickListener {
//            viewModel.deleteDefaultAddress()
//            Toast.makeText(this.context, "Delete success", Toast.LENGTH_SHORT).show()
//            dialogDelete.dismiss()
//        }

        dialogDelete.show()
    }

    @SuppressLint("SetTextI18n")
    private fun checkButtonMatchDialog()
    {
        if( args.defaultAddress.id == 0L) {
            binding.btnEditAddAddress.text = "Add new address"
            binding.btnDelete.text = "Cancel"
            binding.btnDelete.setOnClickListener {
                this.dismiss()
            }
        }
        else {
            binding.btnEditAddAddress.text = "Edit"
            binding.btnDelete.text = "Delete"
        }
        if (binding.btnEditAddAddress.text == "Edit") binding.btnEditAddAddress.isEnabled = false
    }

    private fun addGenderList()
    {
        val genderList = listOf("Male", "Female")
        val genderAdapter = ArrayAdapter(requireContext(), R.layout.gender_list_item, genderList)
        binding.actvGender.setAdapter(genderAdapter)
    }

    private fun addProvinceList()
    {
        val provinceList = viewModel.listProvince
        for (i in 0 .. provinceList.value!!.size)
        {
            provinceAdapter.insert(provinceList.value!![i].name, i)
        }
        provinceAdapter.notifyDataSetChanged()
    }

    private fun addDistrictList()
    {
        val districtList = viewModel.listDistrict
        for (i in 0 .. districtList.value!!.size)
        {
            districtAdapter.insert(districtList.value!![i].name, i)
        }
        districtAdapter.notifyDataSetChanged()
    }

    private fun idOfProvince(position: Int): Long
    {
       return viewModel.listProvince.value!![position].id
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