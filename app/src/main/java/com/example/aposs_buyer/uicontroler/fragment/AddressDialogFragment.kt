package com.example.aposs_buyer.uicontroler.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentAddressDialogBinding
import com.example.aposs_buyer.utils.StringValidator
import com.example.aposs_buyer.viewmodel.AddressViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddressDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddressDialogBinding
    private val viewModel: AddressViewModel by activityViewModels()
    private lateinit var provinceAdapter: ArrayAdapter<String>
    private lateinit var districtAdapter: ArrayAdapter<String>
    private lateinit var wardAdapter: ArrayAdapter<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_address_dialog, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setGenderAutomationText()
        setUpProvinceAutomationText()
        setUpDistrictAutomationText()
        setUpWardAutomationText()
        setUpFirstState()
        doOnTextChange()

//        checkButtonMatchDialog()
//        binding.btnEditAddAddress.setOnClickListener {
//            onClickEditOrAdd()
//        }
//        binding.btnDelete.setOnClickListener {
//            if (binding.btnDelete.text == "Cancel")
//            {
//                findNavController().navigate(AddressDialogFragmentDirections.actionAddressDialogFragment2ToAddressFragment())
//            }
//            else {
//                onOpenDeleteDialog()
//            }
//        }
//        onSetLinkOfEventForAddressChoose()
//        onAddNewAddress()
//        setCheckingCellPhone()
//        setCheckingName()
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    private fun setUpFirstState() {
        if (viewModel.currentAddress.value!!.id != -1L) {
            // edit address
            // set up gender check box
            binding.actvGender.setText(
                viewModel.currentAddress.value!!.getGenderSmallString(),
                false
            )
            // set up edit button
        } else {
            //create new address
            //set up gender first box
            binding.actvGender.setText("Male", false)
            // set up submit button
            binding.submitButton.setOnClickListener {
                Log.d("address", viewModel.currentAddress.value!!.toString())
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setGenderAutomationText() {
        val genderList = listOf("Male", "Female")
        val genderAdapter = ArrayAdapter(requireContext(), R.layout.gender_list_item, genderList)
        binding.actvGender.setAdapter(genderAdapter)
        binding.actvGender.addTextChangedListener {
            viewModel.currentAddress.value!!.setGenderFromString(it.toString());
        }
    }

    private fun setUpProvinceAutomationText() {
        provinceAdapter = ArrayAdapter(requireContext(), R.layout.province_list_item, arrayListOf())
        binding.actvCity.setAdapter(provinceAdapter)
        // submit province data
        if (provinceAdapter.isEmpty) {
            provinceAdapter.addAll(viewModel.listProvince.value!!.map { province -> province.name })
        }
        // on user select province
        binding.actvCity.doOnTextChanged { text, _, _, _ ->
            val provinceId = viewModel.getIdOfProvince(text.toString())
            if (provinceId != -1L) {
                //load all districts of selected province
                viewModel.loadDistrictsByProvinceId(provinceId)
                //clear old district value
                binding.actvDistrict.setText("", false)
                //clear old ward value
                binding.actvWard.setText("",false)
                // set address value
                viewModel.currentAddress.value!!.city = text.toString()
                // tracking valid button
                viewModel.trackingValidInformation()
            }
        }
    }

    private fun setUpDistrictAutomationText() {
        // set up adapter
        districtAdapter = ArrayAdapter(requireContext(), R.layout.district_list_item, arrayListOf())
        binding.actvDistrict.setAdapter(districtAdapter)
        // observe data
        viewModel.listDistrict.observe(viewLifecycleOwner) {
            districtAdapter.clear()
            districtAdapter.addAll(it.map { district -> district.name })
        }
        // on user select district
        binding.actvDistrict.doOnTextChanged { text, _, _, _ ->
            val districtId = viewModel.getIdOfDistrict(text.toString())
            if (districtId != -1L) {
                // load all wards of selected district
                viewModel.loadWardsByDistrictId(districtId)
                //clear old ward value
                binding.actvWard.setText("",false)
                //set address value
                viewModel.currentAddress.value!!.district = text.toString()
                // tracking valid button
                viewModel.trackingValidInformation()
            }
        }
    }

    private fun setUpWardAutomationText() {
        //set up adapter
        wardAdapter = ArrayAdapter(requireContext(), R.layout.ward_list_item, arrayListOf())
        binding.actvWard.setAdapter(wardAdapter)
        // observe data
        viewModel.listWard.observe(viewLifecycleOwner) {
            wardAdapter.clear()
            wardAdapter.addAll(it.map { ward -> ward.name })
        }
        // on user select ward
        binding.actvWard.doOnTextChanged { text, _, _, _ ->
            viewModel.currentAddress.value!!.ward = text.toString()
            // tracking valid button
            viewModel.trackingValidInformation()
        }
    }

    private fun doOnTextChange() {
        // name edit text
        binding.etName.doAfterTextChanged {
            binding.etName.error = StringValidator.getNameError(it.toString())
            // tracking valid button
            viewModel.trackingValidInformation()
        }
        // phone edit text
        binding.etPhone.doAfterTextChanged {
            binding.etPhone.error = StringValidator.getPhoneNumberError(it.toString())
            // tracking valid button
            viewModel.trackingValidInformation()
        }
        // address edit text
        binding.etAddressLane.doAfterTextChanged {
            if (it.toString().isBlank()) {
                binding.etAddressLane.error = "Please provide detail address lane!"
            } else {
                binding.etAddressLane.error = null
            }
            // tracking valid button
            viewModel.trackingValidInformation()
        }
    }


//    private fun onOpenDeleteDialog()
//    {
//        val dialogDelete = this.context?.let { Dialog(it) }
//        dialogDelete?.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialogDelete?.setContentView(R.layout.delete_permission_dialog)
//
//        val window = dialogDelete?.window ?: return
//
//        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
//        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//
//        val windowAttribute = window.attributes
//
//        windowAttribute.gravity = Gravity.CENTER
//        window.attributes= windowAttribute
//
//        dialogDelete.setCancelable(false)
//
//        val btnDelete: AppCompatButton = dialogDelete.findViewById(R.id.btn_delete)
//        val btnCancel: AppCompatButton = dialogDelete.findViewById(R.id.btn_cancel)
//
//        btnCancel.setOnClickListener {
//            dialogDelete.dismiss()
//        }
//        btnDelete.isEnabled = true
//        btnDelete.setOnClickListener {
//            viewModel.deleteDeliveryAddress(args.defaultAddress.id)
//            Toast.makeText(this.context, "Delete success", Toast.LENGTH_SHORT).show()
//            dialogDelete.dismiss()
//            requireActivity().onBackPressed()
//        }
//
//        dialogDelete.show()
//    }

//    @SuppressLint("SetTextI18n")
//    private fun checkButtonMatchDialog()
//    {
//        if( args.defaultAddress.id == 0L) {
//            binding.btnEditAddAddress.text = "Add new address"
//            binding.btnDelete.text = "Cancel"
//            binding.btnDelete.setOnClickListener {
//                this.dismiss()
//            }
//        }
//        else {
//            binding.btnEditAddAddress.text = "Edit"
//            binding.btnDelete.text = "Delete"
//        }
////        if (binding.btnEditAddAddress.text == "Edit") binding.btnEditAddAddress.isEnabled = false
//    }

//    private fun onAddNewAddress()
//    {
//        if (binding.actvCity.text.toString() != "" && binding.actvDistrict.text.toString() != ""
//            && binding.actvGender.text.toString() != "" && binding.actvWard.text.toString() != ""
//            && binding.etName.text.toString() != "" && binding.etPhone.text.toString() != ""
//            && binding.etAddressLane.text.toString()!="" ){
//            val newAddress = Address(args.defaultAddress.id,binding.etName.text.toString(),binding.actvGender.text.toString()=="Male",
//                binding.etPhone.text.toString(), binding.actvCity.text.toString(),
//                binding.actvDistrict.text.toString(), binding.actvWard.text.toString(),
//                binding.etAddressLane.text.toString(), viewModel.isDefault.value!!)
//            viewModel.onAddNewAddress(newAddress)
//            requireActivity().onBackPressed()
//        }
//        else if (binding.actvCity.text.toString() != "" && binding.actvDistrict.text.toString() != ""
//            && binding.actvGender.hint != null && binding.actvWard.text.toString() != ""
//            && binding.etName.text.toString() != "" && binding.etPhone.text.toString() != ""
//            && binding.etAddressLane.text.toString()!= "" ) {
//            val newAddress = Address(args.defaultAddress.id,binding.etName.text.toString(),binding.actvGender.hint.toString()=="Male",
//                binding.etPhone.text.toString(), binding.actvCity.text.toString(),
//                binding.actvDistrict.text.toString(), binding.actvWard.text.toString(),
//                binding.etAddressLane.text.toString(), viewModel.isDefault.value!!)
//            viewModel.onAddNewAddress(newAddress)
//            requireActivity().onBackPressed()
//        }
//        else {
//            Toast.makeText(this.context, "Please enter full information", Toast.LENGTH_SHORT).show()
//        }
//    }

//    private fun onClickEditOrAdd()
//    {
//        if (binding.btnEditAddAddress.text == "Edit") {
//            Log.d("postingDefault", viewModel.isDefault.value!!.toString())
//            if (binding.actvCity.text.toString() != "" && binding.actvDistrict.text.toString() != "" && binding.actvWard.text.toString() != "" &&
//                binding.actvGender.text.toString() != ""
//            ) {
//                var newAddress: Address = Address(
//                    args.defaultAddress.id,
//                    binding.etName.text.toString(),
//                    binding.actvGender.text.toString() == "Male",
//                    binding.etPhone.text.toString(),
//                    binding.actvCity.text.toString(),
//                    binding.actvDistrict.text.toString(),
//                    binding.actvWard.text.toString(),
//                    binding.etAddressLane.text.toString(),
//                    viewModel.isDefault.value!!
//                )
//                onUpdateAddress(newAddress)
//                requireActivity().onBackPressed()
//            } else {
//                Toast.makeText(this.context, "Please enter full information", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        } else onAddNewAddress()
//    }

//    private fun onUpdateAddress(address: Address)
//    {
//        viewModel.onUpdateAddress(address)
//    }
//
//    private fun havingLegalToClick(isLegal: Boolean)
//    {
//        binding.btnEditAddAddress.isEnabled =  true
//    }
//
//    private fun setCheckingName(){
//        viewModel.name.observe(this.viewLifecycleOwner) {
//            viewModel.isValidName()
//            binding.etName.error = viewModel.nameErrorMessage
//        }
//    }

//    private fun setCheckingCellPhone(){
//        viewModel.cellNumber.observe(this.viewLifecycleOwner) {
//            viewModel.isValidPhoneNumber()
//            binding.etPhone.error = viewModel.cellNumberErrorMessage
//        }
//    }
//
//    fun setOnChange() {
//        if (binding.btnEditAddAddress.text == "Edit") {
//            binding.actvCity.addTextChangedListener {
//                havingLegalToClick(viewModel.isChangeCity(it.toString()))
//            }
//            binding.actvGender.addTextChangedListener {
//                havingLegalToClick(viewModel.isChangeGender(it.toString()))
//            }
//            binding.actvDistrict.addTextChangedListener {
//                havingLegalToClick(viewModel.isChangeDistrict(it.toString()))
//            }
//            binding.actvWard.addTextChangedListener {
//                havingLegalToClick(viewModel.isChangeWard(it.toString()))
//            }
//            binding.etAddressLane.addTextChangedListener {
//                havingLegalToClick(viewModel.isChangeAddressLane(it.toString()))
//            }
//            binding.etName.addTextChangedListener {
//                havingLegalToClick(viewModel.isChangeName(it.toString()))
//            }
//            binding.etPhone.addTextChangedListener {
//                havingLegalToClick(viewModel.isChangePhone(it.toString()))
//            }
//        }
//    }
}