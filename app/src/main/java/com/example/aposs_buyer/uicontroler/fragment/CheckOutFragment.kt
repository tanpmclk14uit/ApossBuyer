package com.example.aposs_buyer.uicontroler.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentCheckOutBinding
import com.example.aposs_buyer.uicontroler.activity.CartActivity
import com.example.aposs_buyer.uicontroler.adapter.BillingItemsAdapter
import com.example.aposs_buyer.uicontroler.dialog.LoadingDialog
import com.example.aposs_buyer.utils.LoadingStatus
import com.example.aposs_buyer.viewmodel.CheckOutViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckOutFragment : Fragment() {

    private var binding: FragmentCheckOutBinding? =null
    private val args: CheckOutFragmentArgs by navArgs()
    private val viewModel: CheckOutViewModel by activityViewModels()
    private lateinit var loadingDialog: LoadingDialog
    companion object CheckOutContext{
        const val context = "Check out"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_check_out, container, false)
        binding?.lifecycleOwner = this.viewLifecycleOwner
        binding?.viewModel = viewModel
        setUpFirstState()
        setPaymentMethod()
        setUpAppBar()
        setUpAddress()
        setUpBillings()
        setUpConfirmButton()
        setUpLoadingDialog()
        return binding?.root!!
    }

    override fun onResume() {
        super.onResume()
        binding?.paymentMethodSelectionView?.setText("", false)
        setPaymentMethod()
    }

    private fun setPaymentMethod(){
        val data = listOf("Tiền mặt", "Trực tuyến")
        val adapter = ArrayAdapter(requireContext(), R.layout.gender_list_item, data)
        binding?.paymentMethodSelectionView?.setAdapter(adapter)
        binding?.paymentMethodSelectionView?.setOnItemClickListener { adapterView, view, i, l ->
            viewModel.setNewPaymentMethod(adapter.getItem(i).toString())
            binding?.paymentMethodSelectionView?.setText(adapter.getItem(i).toString(), false)
        }
    }

    private fun setUpAddress() {
        binding?.imgEditAddress?.setOnClickListener {
            // go to select select address
            findNavController().navigate(CheckOutFragmentDirections.actionCheckOutFragmentToChooseAddressFragment(
                CheckOutContext.context
            ))
        }
    }

    private fun setUpConfirmButton() {
        // set up onclick button
        binding?.btnConfirm?.setOnClickListener {
            if (viewModel.isValidAddress.value == true){
                viewModel.addNewOrder()
            }else{
                Toast.makeText(this.requireContext(), "Địa chỉ không hợp lệ", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun setUpLoadingDialog() {
        // create loading dialog
        loadingDialog = LoadingDialog(requireActivity())
        // tracking loading status
        viewModel.checkOutStatus.observe(this.viewLifecycleOwner) {
            if (it == LoadingStatus.Loading) {
                loadingDialog.startLoading()
            } else {
                loadingDialog.dismissDialog()
                if (it == LoadingStatus.Success) {

                    findNavController().navigate(CheckOutFragmentDirections.actionCheckOutFragmentToCheckOutSuccessFragment())
                } else {
                    Toast.makeText(this.requireContext(), "THanh toán thất bại", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun setUpBillings() {
        // set up billing adapter
        binding?.rcCheckOut?.adapter = BillingItemsAdapter()
        // set up cart in billing click
        binding?.imgCart2?.setOnClickListener {
            startActivity(Intent(this.requireContext(), CartActivity::class.java))
            this.requireActivity().finish()
        }
    }

    private fun setUpFirstState() {
        // set up current order data
        viewModel.setCurrentOrder(args.currentOrder)
    }

    private fun setUpAppBar() {
        binding?.imgBack?.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

}