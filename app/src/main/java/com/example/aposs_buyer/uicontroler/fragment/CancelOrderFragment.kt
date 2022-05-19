package com.example.aposs_buyer.uicontroler.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentCancelOrderBinding
import com.example.aposs_buyer.uicontroler.dialog.LoadingDialog
import com.example.aposs_buyer.utils.LoadingStatus
import com.example.aposs_buyer.viewmodel.CancelOrderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CancelOrderFragment : Fragment() {

    private val viewModel: CancelOrderViewModel by viewModels()

    private val args: CancelOrderFragmentArgs by navArgs()

    private lateinit var loadingDialog: LoadingDialog

    private lateinit var binding: FragmentCancelOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_cancel_order,
            container,
            false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.setOrderId(args.id)
        onBackPress()
        setOnConfirmPress()
        setUpLoadingDialog()
        return binding.root
    }

    private fun onBackPress() {
        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setUpLoadingDialog() {
        // create loading dialog
        loadingDialog = LoadingDialog(requireActivity())
        // tracking loading status
        viewModel.loadStatus.observe(this.viewLifecycleOwner) {
            if (it == LoadingStatus.Loading) {
                loadingDialog.startLoading()
            } else {
                loadingDialog.dismissDialog()
                if (it == LoadingStatus.Success) {
                    findNavController().navigate(CancelOrderFragmentDirections.actionCancelOrderFragmentToCancelOrderConfirmFragment())
                } else {
                    Toast.makeText(this.requireContext(), "Cancel out fail", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun setOnConfirmPress() {
        binding.confirmButton.setOnClickListener {
            if (viewModel.cancelReason.value!!.isNotBlank()) {
                viewModel.cancelOrder()
            } else {
                Toast.makeText(this.context, "Please fill reason", Toast.LENGTH_SHORT).show()
            }
        }
    }

}