package com.example.aposs_buyer.uicontroler.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentCancelOrderBinding
import com.example.aposs_buyer.databinding.FragmentCancelOrderConfirmBinding
import com.example.aposs_buyer.uicontroler.activity.MainActivity

class CancelOrderSuccessFragment : Fragment() {

    private lateinit var binding: FragmentCancelOrderConfirmBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_cancel_order_confirm,
            container,
            false
        )
        setOnBackPress()
        setContinueButton()
        return binding.root
    }

    private fun setOnBackPress() {
        binding.back.setOnClickListener {
            findNavController().navigate(CancelOrderSuccessFragmentDirections.actionCancelOrderConfirmFragmentToOrderFragment())
        }
    }

    private fun setContinueButton() {
        binding.continueShopping.setOnClickListener {
            startActivity(Intent(this.context, MainActivity::class.java))
        }
    }

}