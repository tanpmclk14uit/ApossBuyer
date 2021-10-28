package com.example.aposs_buyer.uicontroler.fragment

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentCheckOutDialogBinding
import com.example.aposs_buyer.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import android.text.Spanned


import android.text.style.ForegroundColorSpan

import android.text.SpannableStringBuilder
import androidx.core.content.ContextCompat
import androidx.core.text.set
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aposs_buyer.uicontroler.adapter.CheckOutDialogAdapter


@AndroidEntryPoint
class CheckOutDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentCheckOutDialogBinding
    private val viewModel: CartViewModel by activityViewModels()
    private val checkOutAdapter=  CheckOutDialogAdapter()

//    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_check_out_dialog, container, false)
        binding.lifecycleOwner= this
        binding.viewModel = viewModel
        binding.rcCheckOutConfirm.adapter = checkOutAdapter
        binding.rcCheckOutConfirm.layoutManager = LinearLayoutManager(binding.rcCheckOutConfirm.context, LinearLayoutManager.VERTICAL, false)
        setTextColor()
        return binding.root
    }

    private fun setTextColor()
    {
        var text = "By placing your order you agree to our terms and conditions, privacy and returns policies and consent to some of your data being stored by Aposs which may be used to make future shopping experiences better for you."
        val ssb = SpannableStringBuilder(text)

        val fcsLightGreen = ForegroundColorSpan(ContextCompat.getColor(this.requireContext(), R.color.light_green))
        val fcsGray =  ForegroundColorSpan(ContextCompat.getColor(this.requireContext(), R.color.edit_user_detail_color))


        ssb.setSpan(fcsLightGreen, 39, 89, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ssb.setSpan(fcsGray, 69, 72, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvPolicy.text = ssb
    }
}