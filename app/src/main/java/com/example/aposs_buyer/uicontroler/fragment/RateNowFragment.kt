package com.example.aposs_buyer.uicontroler.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentRateNowBinding
import com.example.aposs_buyer.model.RateNowItem
import com.example.aposs_buyer.responsitory.database.AccountDatabase
import com.example.aposs_buyer.uicontroler.activity.CartActivity
import com.example.aposs_buyer.uicontroler.activity.LoginActivity
import com.example.aposs_buyer.uicontroler.adapter.RateNowAdapter
import com.example.aposs_buyer.viewmodel.RateNowViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RateNowFragment : Fragment(), RateNowAdapter.OnItemClick {

    private lateinit var binding: FragmentRateNowBinding
    private val viewModel: RateNowViewModel by viewModels()
    private val rateNowAdapter = RateNowAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rate_now, container, false)
        binding.viewModel = viewModel
        binding.rcRateNow.adapter = rateNowAdapter
        binding.rcRateNow.layoutManager =
            LinearLayoutManager(binding.rcRateNow.context, LinearLayoutManager.VERTICAL, false)
        binding.imgBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.clCart.setOnClickListener {
            if (isUserLoggedIn()) {
                startActivity(Intent(this.context, CartActivity::class.java))
            } else {
                startActivity(Intent(this.context, LoginActivity::class.java))
            }
        }
        return binding.root
    }

    private fun isUserLoggedIn(): Boolean {
        return AccountDatabase.getInstance(this.requireContext()).accountDao.getAccount() != null
    }

    override fun onItemClick(rateNowItem: RateNowItem) {
        findNavController().navigate(
            RateNowFragmentDirections.actionRateNowFragmentToRatingFragment(
                rateNowItem
            )
        )
    }
}