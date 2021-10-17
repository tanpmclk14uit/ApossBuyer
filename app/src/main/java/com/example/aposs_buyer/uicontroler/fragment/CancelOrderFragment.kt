package com.example.aposs_buyer.uicontroler.fragment

import android.os.Bundle
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
import com.example.aposs_buyer.viewmodel.CancelOrderViewModel


class CancelOrderFragment : Fragment() {

    private val viewModel: CancelOrderViewModel by viewModels()

    private val args: CancelOrderFragmentArgs by navArgs()

    private lateinit var binding: FragmentCancelOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_cancel_order, container, false)
        binding.viewModel = viewModel
        viewModel.setOrderId(args.id)
        onBackPress()
        setOnConfirmPress()
        return binding.root
    }
    private fun onBackPress(){
        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setOnConfirmPress(){
        binding.confirmButton.setOnClickListener {
            if(viewModel.cancelReason.value!!.isNotBlank()){
                viewModel.cancelOrder()
                findNavController().navigate(CancelOrderFragmentDirections.actionCancelOrderFragmentToCancelOrderConfirmFragment())
            }else{
                Toast.makeText(this.context, "Please fill reason", Toast.LENGTH_SHORT).show()
            }
        }
    }

}