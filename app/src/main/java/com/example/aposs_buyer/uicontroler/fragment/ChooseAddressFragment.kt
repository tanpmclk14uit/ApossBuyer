package com.example.aposs_buyer.uicontroler.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.navArgs
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentChooseAddressBinding
import com.example.aposs_buyer.model.Address
import com.example.aposs_buyer.uicontroler.activity.AddressActivity
import com.example.aposs_buyer.uicontroler.adapter.ChooseAddressAdapter
import com.example.aposs_buyer.uicontroler.dialog.LoadingDialog
import com.example.aposs_buyer.utils.LoadingStatus
import com.example.aposs_buyer.viewmodel.AddressViewModel
import com.example.aposs_buyer.viewmodel.CheckOutViewModel
import com.example.aposs_buyer.viewmodel.OrderDetailViewModel

class ChooseAddressFragment : Fragment(), ChooseAddressAdapter.OnChooseAddress {

    private lateinit var binding: FragmentChooseAddressBinding
    private val args: ChooseAddressFragmentArgs by navArgs()
    private val addressViewModel: AddressViewModel by activityViewModels()
    private val checkOutViewModel: CheckOutViewModel by activityViewModels()
    private val detailOrderViewModel: OrderDetailViewModel by activityViewModels()
    private lateinit var chooseAddressAdapter: ChooseAddressAdapter
    private lateinit var currentAddress:Address
    private lateinit var loadingDialog: LoadingDialog
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
            if(args.context == CheckOutFragment.context){
                checkOutViewModel.setNewAddress(currentAddress)
                requireActivity().onBackPressed()
            }
            if(args.context == DetailOrderFragment.context){
                setUpLoadingDialog()
                detailOrderViewModel.changeAddress(currentAddress.getFullAddress())
            }
        }
    }
    private fun setUpLoadingDialog() {
        // create loading dialog
        loadingDialog = LoadingDialog(requireActivity())
        // tracking loading status
        detailOrderViewModel.changeAddressStatus.observe(this.viewLifecycleOwner) {
            if (it == LoadingStatus.Loading) {
                loadingDialog.startLoading()
            } else {
                loadingDialog.dismissDialog()
                if (it == LoadingStatus.Success) {
                    requireActivity().onBackPressed()
                } else {
                    Toast.makeText(this.requireContext(), "Change address fail", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        detailOrderViewModel.changeAddressStatus = MutableLiveData<LoadingStatus>()
    }

    override fun onChoose(address: Address) {
        currentAddress = address
    }

}