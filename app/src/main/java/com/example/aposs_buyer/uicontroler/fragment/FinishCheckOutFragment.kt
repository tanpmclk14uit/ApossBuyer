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
import com.example.aposs_buyer.databinding.FragmentCheckOutBinding
import com.example.aposs_buyer.databinding.FragmentFinishCheckOutBinding
import com.example.aposs_buyer.uicontroler.activity.MainActivity

class FinishCheckOutFragment : Fragment() {

    private lateinit var binding: FragmentFinishCheckOutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_finish_check_out, container, false)
        binding.btnContinue.setOnClickListener {
            val intent = Intent(this.context, MainActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }
}