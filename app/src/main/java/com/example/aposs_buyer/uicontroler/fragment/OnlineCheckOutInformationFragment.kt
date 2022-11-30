package com.example.aposs_buyer.uicontroler.fragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentOnlineCheckOutInformationBinding
import com.example.aposs_buyer.uicontroler.activity.OrderActivity
import com.example.aposs_buyer.uicontroler.dialog.LoadingDialog
import com.example.aposs_buyer.utils.LoadingStatus
import com.example.aposs_buyer.viewmodel.OnlineCheckOutInformationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnlineCheckOutInformationFragment : Fragment() {

    private lateinit var binding: FragmentOnlineCheckOutInformationBinding
    private val viewModel: OnlineCheckOutInformationViewModel by viewModels()

    private val args: OnlineCheckOutInformationFragmentArgs by navArgs()

    private lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_online_check_out_information, container, false)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = viewModel
        binding.bankingContent.text = args.id.toString()
        binding.imgBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.confirmPaid.setOnClickListener {
            viewModel.updatePaymentStatus(args.id)
        }
        setUpLoadingDialog()
        setCopy()
        return  binding.root
    }

    private fun setUpLoadingDialog() {
        // create loading dialog
        loadingDialog = LoadingDialog(requireActivity())
        // tracking loading status
        viewModel.updateStatus.observe(this.viewLifecycleOwner) {
            if (it == LoadingStatus.Loading) {
                loadingDialog.startLoading()
            } else {
                loadingDialog.dismissDialog()
                if (it == LoadingStatus.Success) {
                    Toast.makeText(this.requireContext(), "Gửi yêu cầu xác nhân thanh toán thành công!", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(Intent(requireContext(), OrderActivity::class.java))
                    requireActivity().finish()
                } else {
                    Toast.makeText(this.requireContext(), "Gửi yêu cầu xác nhân thanh toán thất bại!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun setCopy(){
        binding.bankingContentCopy.setOnClickListener {
            copyToClipBoard(args.id.toString())
        }

        binding.bankNameCopy.setOnClickListener {
            copyToClipBoard(viewModel.bankingInformationDTO.value!!.bankName)
        }
        binding.branchNameCopy.setOnClickListener {
            copyToClipBoard(viewModel.bankingInformationDTO.value!!.branch)
        }
        binding.accountNumberCopy.setOnClickListener {
            copyToClipBoard(viewModel.bankingInformationDTO.value!!.accountNumber)
        }
        binding.receiverCopy.setOnClickListener {
            copyToClipBoard(viewModel.bankingInformationDTO.value!!.accountNumber)
        }
    }

    private fun copyToClipBoard(text: CharSequence){
        val clipboard: ClipboardManager = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Banking Information Copy", text)
        clipboard.setPrimaryClip(clip)
    }
}