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
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentAddressDialogBinding
import com.example.aposs_buyer.uicontroler.dialog.LoadingDialog
import com.example.aposs_buyer.utils.LoadingStatus
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
        binding.lifecycleOwner = this.viewLifecycleOwner
        setUpGenderAutomationText()
        setUpProvinceAutomationText()
        setUpDistrictAutomationText()
        setUpWardAutomationText()
        setUpLoadingDialog()
        setUpDefaultAddressCheckChange()
        // set up edit text: name, phone, address lane
        doOnTextChange()
        // set up first state of dialog
        setUpFirstState()
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun setUpFirstState() {
        viewModel.trackingValidInformation()
        val currentAddress = viewModel.newAddress.value!!
        if (currentAddress.id != -1L) {
            // edit address
            // set up gender check box
            binding.actvGender.setText(
                currentAddress.getGenderSmallString(),
                false
            )
            //set up city automation text
            binding.actvCity.setText(currentAddress.city.name, false)
            // set up district automation text
            binding.actvDistrict.setText(currentAddress.district.name, false)
            viewModel.loadDistrictsByProvinceId(currentAddress.city.id)
            // set up ward automation text
            binding.actvWard.setText(currentAddress.ward.name, false)
            viewModel.loadWardsByDistrictId(currentAddress.district.id)
            //set up edit button
            binding.buttonEdit.setOnClickListener {
                viewModel.onUpdateAddress()
                this.dismiss()
            }
            //set up first state of "address default" check box
            viewModel.checkChange.value = currentAddress.isDefaultAddress
            // set up delete button
            binding.btnDelete.setOnClickListener {
                onOpenDeleteDialog()
            }
        } else {
            //create new address
            //set up gender first box
            binding.actvGender.setText("Male", false)
            // set up submit button
            binding.submitButton.setOnClickListener {
                viewModel.addNewAddress()
                this.dismiss()
            }
            //set up first state of "address default" check box
            viewModel.checkChange.value = true
        }
    }

    private fun setUpLoadingDialog() {
        // observe with loading status
        viewModel.loadingStatus.observe(viewLifecycleOwner) {
            if (it == LoadingStatus.Fail) {
                Toast.makeText(this.requireContext(), "Server error!!!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setUpDefaultAddressCheckChange() {
        viewModel.checkChange.observe(viewLifecycleOwner){
            viewModel.newAddress.value!!.isDefaultAddress = it
            viewModel.trackingValidInformation()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUpGenderAutomationText() {
        val genderList = listOf("Male", "Female")
        val genderAdapter = ArrayAdapter(requireContext(), R.layout.gender_list_item, genderList)
        binding.actvGender.setAdapter(genderAdapter)
        binding.actvGender.addTextChangedListener {
            viewModel.newAddress.value!!.setGenderFromString(it.toString())
            // tracking valid button
            viewModel.trackingValidInformation()
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
            val province = viewModel.getProvinceFromName(text.toString())
            if (province.id != -1L) {
                //load all districts of selected province
                viewModel.loadDistrictsByProvinceId(province.id)
                //clear old district value
                binding.actvDistrict.setText("", false)
                //clear old ward value
                binding.actvWard.setText("", false)
                // set address value
                viewModel.newAddress.value!!.city = province
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
            val district = viewModel.getDistrictFromName(text.toString())
            if (district.id != -1L) {
                // load all wards of selected district
                viewModel.loadWardsByDistrictId(district.id)
                //clear old ward value
                binding.actvWard.setText("", false)
                //set address value
                viewModel.newAddress.value!!.district = district
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
            val ward = viewModel.getWardFromName(text.toString())
            if (ward.id != -1L) {
                viewModel.newAddress.value!!.ward = ward
                // tracking valid button
                viewModel.trackingValidInformation()
            }
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

    private fun onOpenDeleteDialog() {
        val dialogDelete = this.context?.let { Dialog(it) }
        dialogDelete?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogDelete?.setContentView(R.layout.delete_permission_dialog)

        val window = dialogDelete?.window ?: return

        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val windowAttribute = window.attributes

        windowAttribute.gravity = Gravity.CENTER
        window.attributes = windowAttribute

        dialogDelete.setCancelable(false)

        val btnDelete: AppCompatButton = dialogDelete.findViewById(R.id.btn_delete)
        val btnCancel: AppCompatButton = dialogDelete.findViewById(R.id.btn_cancel)

        btnCancel.setOnClickListener {
            dialogDelete.dismiss()
        }
        btnDelete.isEnabled = true
        btnDelete.setOnClickListener {
            viewModel.deleteDeliveryAddress()
            Toast.makeText(this.context, "Delete success", Toast.LENGTH_SHORT).show()
            dialogDelete.dismiss()
            requireActivity().onBackPressed()
        }
        dialogDelete.show()
    }
}