package com.example.aposs_buyer.uicontroler.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
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
import com.example.aposs_buyer.utils.AddingStatus
import com.example.aposs_buyer.utils.BridgeObject
import com.example.aposs_buyer.viewmodel.AddressDialogViewModel
import com.example.aposs_buyer.viewmodel.AddressViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddressDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddressDialogBinding
    private val viewModel: AddressDialogViewModel by viewModels()
    private val args: AddressDialogFragmentArgs by navArgs()
    private var firsTimeLoadProvince  = false
    private var firsTimeLoadWard  = false
    private lateinit var provinceAdapter: ArrayAdapter<String>
    private lateinit var districtAdapter: ArrayAdapter<String>
    private lateinit var wardAdapter: ArrayAdapter<String>

    //override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_address_dialog, container, false)
        binding.viewModel = viewModel

        setStartValue()
//        viewModel.listProvince.value = mutableListOf()
//        viewModel.loadProvince()
        setAdapterForAddressElement()
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
        onSetLinkOfEventForAddressChoose()
        onAddNewAddress()
        setCheckingCellPhone()
        setCheckingName()
        return binding.root
    }

    private fun setStartValue()
    {
        viewModel.address.value = args.defaultAddress
        viewModel.name.value = viewModel.address.value!!.name
        viewModel.cellNumber.value = viewModel.address.value!!.phoneNumber
        viewModel.isDefault.value = viewModel.address.value!!.isDefault
        binding.actvCity.setText(args.defaultAddress.city, false)
        viewModel.positionOfProvince(args.defaultAddress.city)
        if (args.defaultAddress.gender) {
            binding.actvGender.setText("Male", false)
        }
        else {binding.actvGender.setText("Female", false)}
        binding.actvDistrict.setText(args.defaultAddress.district, false)
        binding.actvWard.setText(args.defaultAddress.ward, false)
    }

    private fun setAdapterForAddressElement()
    {
        provinceAdapter = ArrayAdapter(requireContext(), R.layout.province_list_item, arrayListOf())
        binding.actvCity.setAdapter(provinceAdapter)
        districtAdapter = ArrayAdapter(requireContext(), R.layout.district_list_item, arrayListOf())
        binding.actvDistrict.setAdapter(districtAdapter)
        wardAdapter = ArrayAdapter(requireContext(), R.layout.ward_list_item, arrayListOf())
        binding.actvWard.setAdapter(wardAdapter)
    }

    private fun onSetLinkOfEventForAddressChoose()
    {
        viewModel.listProvince.observe(viewLifecycleOwner, Observer {
            addProvinceList()
            if (viewModel.positionOfProvince(args.defaultAddress.city)<it.size && viewModel.positionOfProvince(args.defaultAddress.city) != 0)
            {
                Log.d("district2", idOfProvince(viewModel.positionOfProvince(args.defaultAddress.city)).toString())
                viewModel.listDistrict.value = mutableListOf()
                viewModel.loadDistrictByProvince(idOfProvince(viewModel.positionOfProvince(args.defaultAddress.city)))
            }
        })
        binding.actvCity.setOnItemClickListener{ adapterView, view, i, l ->
            deleteDistrict()
            deleteWardList()
            binding.actvDistrict.setText("", false)
            binding.actvWard.setText("", false)
            viewModel.listWard.value = mutableListOf()
            viewModel.loadDistrictByProvince(idOfProvince(i))
        }
        viewModel.listDistrict.observe(viewLifecycleOwner, Observer {
            addDistrictList()
            districtAdapter.notifyDataSetChanged()
            if (viewModel.positionOfDistrict(args.defaultAddress.district)<=it.size && viewModel.positionOfDistrict(args.defaultAddress.district)!=0 && !firsTimeLoadProvince)
            {
                firsTimeLoadProvince = true
                viewModel.loadWardByDistrict(idOfDistrict(viewModel.positionOfDistrict(args.defaultAddress.district)))
            }
        })
        binding.actvDistrict.setOnItemClickListener { adapterView, view, i, l ->
            deleteWardList()
            binding.actvWard.setText("", false)
            wardAdapter.notifyDataSetChanged()
            viewModel.loadWardByDistrict(idOfDistrict(i))
        }
        viewModel.listWard.observe(viewLifecycleOwner, Observer {
            addWardList()
            wardAdapter.notifyDataSetChanged()
            if (viewModel.positionOfWard(args.defaultAddress.ward)<=it.size && viewModel.positionOfWard(args.defaultAddress.ward)!=0 && !firsTimeLoadWard)
            {
                binding.actvWard.setSelection(viewModel.positionOfWard(args.defaultAddress.ward))
                firsTimeLoadWard= true
            }
        })
        binding.btnEditAddAddress.isEnabled = true
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
        btnDelete.isEnabled = true
        btnDelete.setOnClickListener {
            viewModel.deleteDeliveryAddress(args.defaultAddress.id)
            Toast.makeText(this.context, "Delete success", Toast.LENGTH_SHORT).show()
            dialogDelete.dismiss()
        }

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
        deleteProvince()
        if (provinceList.value != null) {
            for (i in 0 until provinceList.value!!.size) {
                provinceAdapter.insert(provinceList.value!![i].name, i)
//                Log.d("province", provinceList.value!![i].name)
            }
            provinceAdapter.notifyDataSetChanged()
            provinceAdapter.notifyDataSetChanged()
        }
    }

    private fun addDistrictList()
    {
        val districtList = viewModel.listDistrict
        deleteDistrict()
        for (i in 0 until  districtList.value!!.size)
        {
            districtAdapter.insert(districtList.value!![i].name, i)
//            Log.d("district", districtList.value!![i].name)
        }
        districtAdapter.notifyDataSetChanged()
        districtAdapter.notifyDataSetChanged()
    }

    private fun addWardList()
    {
        val wardList = viewModel.listWard
        deleteWardList()
        for (i in 0 until wardList.value!!.size)
        {
            wardAdapter.insert(wardList.value!![i].name, i)
//            Log.d("district", wardList.value!![i].name)
        }
        wardAdapter.notifyDataSetChanged()
        wardAdapter.notifyDataSetChanged()
    }

    private fun deleteWardList()
    {
        val wardList = viewModel.listWard
        if (wardList.value != null) {
            for (i in 0 until wardList.value!!.size) {
                provinceAdapter.remove(wardList.value!![i].name)
            }
            provinceAdapter.notifyDataSetChanged()
            provinceAdapter.notifyDataSetChanged()
        }
    }

    private fun deleteDistrict()
    {
        val districtList = viewModel.listDistrict
        if (districtList.value!= null) {
            for (i in 0 until districtList.value!!.size) {
                districtAdapter.remove(districtList.value!![i].name)
            }
            districtAdapter.notifyDataSetChanged()
            districtAdapter.notifyDataSetChanged()
        }
    }

    private fun deleteProvince()
    {
        val provinceList = viewModel.listDistrict
        if (provinceList.value!= null) {
            for (i in 0 until provinceList.value!!.size) {
                districtAdapter.remove(provinceList.value!![i].name)
            }
            districtAdapter.notifyDataSetChanged()
            districtAdapter.notifyDataSetChanged()
        }
    }

    private fun idOfProvince(position: Int): Long
    {
       return viewModel.listProvince.value!![position].id
    }

    private fun idOfDistrict(position: Int): Long
    {
        return viewModel.listDistrict.value!![position].id
    }

    private fun onAddNewAddress()
    {
        if (binding.actvCity.text.toString() != "" && binding.actvDistrict.text.toString() != ""
            && binding.actvGender.text.toString() != "" && binding.actvWard.text.toString() != ""
            && binding.etName.text.toString() != "" && binding.etPhone.text.toString() != ""
            && binding.etAddressLane.text.toString()!="" ){
            val newAddress = Address(args.defaultAddress.id,binding.etName.text.toString(),binding.actvGender.text.toString()=="Male",
                binding.etPhone.text.toString(), binding.actvCity.text.toString(),
                binding.actvDistrict.text.toString(), binding.actvWard.text.toString(),
                binding.etAddressLane.text.toString(), viewModel.isDefault.value!!)
            viewModel.onAddNewAddress(newAddress)
            if (newAddress.isDefault)
            {
                val currentDefault = args.currentDefaultAddress
                currentDefault.isDefault = false
                viewModel.onUpdateAddress(currentDefault)
            }
            findNavController().navigate(AddressDialogFragmentDirections.actionAddressDialogFragment2ToAddressFragment())
        }
        if (binding.actvCity.text.toString() != "" && binding.actvDistrict.text.toString() != ""
            && binding.actvGender.hint != null && binding.actvWard.text.toString() != ""
            && binding.etName.text.toString() != "" && binding.etPhone.text.toString() != ""
            && binding.etAddressLane.text.toString()!= "" ) {
            val newAddress = Address(args.defaultAddress.id,binding.etName.text.toString(),binding.actvGender.hint.toString()=="Male",
                binding.etPhone.text.toString(), binding.actvCity.text.toString(),
                binding.actvDistrict.text.toString(), binding.actvWard.text.toString(),
                binding.etAddressLane.text.toString(), viewModel.isDefault.value!!)
            viewModel.onAddNewAddress(newAddress)
            if (newAddress.isDefault)
            {
                val currentDefault = args.currentDefaultAddress
                currentDefault.isDefault = false
                viewModel.onUpdateAddress(currentDefault)
            }
            findNavController().navigate(AddressDialogFragmentDirections.actionAddressDialogFragment2ToAddressFragment())
        }
        else {
            Toast.makeText(this.context, "Please enter full information", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onClickEditOrAdd()
    {
        if (binding.btnEditAddAddress.text == "Edit") {
            Log.d("postingDefault", viewModel.isDefault.value!!.toString())
            if (binding.actvCity.text.toString() != "" && binding.actvDistrict.text.toString() != "" && binding.actvWard.text.toString() != "" &&
                binding.actvGender.text.toString() != ""
            ) {
                var newAddress: Address = Address(
                    args.defaultAddress.id,
                    binding.etName.text.toString(),
                    binding.actvGender.text.toString() == "Male",
                    binding.etPhone.text.toString(),
                    binding.actvCity.text.toString(),
                    binding.actvDistrict.text.toString(),
                    binding.actvWard.text.toString(),
                    binding.etAddressLane.text.toString(),
                    viewModel.isDefault.value!!
                )
                onUpdateAddress(newAddress)
                if (newAddress.isDefault)
                {
                    val currentDefault = args.currentDefaultAddress
                    currentDefault.isDefault = false
                    viewModel.onUpdateAddress(currentDefault)
                }
                requireActivity().onBackPressed()
            } else {
                Toast.makeText(this.context, "Please enter full information", Toast.LENGTH_SHORT)
                    .show()
            }
        } else onAddNewAddress()
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