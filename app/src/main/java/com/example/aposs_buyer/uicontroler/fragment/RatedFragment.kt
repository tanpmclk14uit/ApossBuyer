package com.example.aposs_buyer.uicontroler.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentRatedBinding
import com.example.aposs_buyer.uicontroler.activity.CartSecondActivity
import com.example.aposs_buyer.uicontroler.adapter.RatedAdapter
import com.example.aposs_buyer.viewmodel.RatedViewModel

class RatedFragment : Fragment() {

    private lateinit var binding:FragmentRatedBinding
    private val viewModel: RatedViewModel by viewModels()
    private val ratedAdapter = RatedAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_rated, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.rcRated.adapter = ratedAdapter
        binding.rcRated.layoutManager = LinearLayoutManager(binding.rcRated.context, LinearLayoutManager.VERTICAL, false)
        binding.imgBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.clCart.setOnClickListener {
            val intent = Intent(this.context, CartSecondActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }
}