package com.example.aposs_buyer.uicontroler.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentOrderBinding
import com.example.aposs_buyer.uicontroler.adapter.OrderAdapter
import com.example.aposs_buyer.viewmodel.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderFragment : Fragment() {

    private lateinit var binding: FragmentOrderBinding

    private val viewModel: OrderViewModel by viewModels()

    private lateinit var orderAdapter: OrderAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_order, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        orderAdapter = OrderAdapter()
        binding.orders.adapter = orderAdapter
        setBottomBar()
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setBottomBar(){
        binding.bottomBar.setOnItemSelectedListener {
            when(it.title.toString()){
                "Pending" ->{
                    viewModel.setCurrentOrders(viewModel.loadPendingOrder())
                }
                "Confirmed" ->{
                    viewModel.setCurrentOrders(viewModel.loadConfirmedOrder())
                }
                "Delivering" ->{
                    viewModel.setCurrentOrders(viewModel.loadDeliveringOrder())
                }
                "Success" ->{
                    viewModel.setCurrentOrders(viewModel.loadSuccessOrder())
                }
                "Cancel"->{
                    viewModel.setCurrentOrders(viewModel.loadCancelOrder())
                }
            }
            orderAdapter.submitList(viewModel.currentListOrder.value)
            binding.orders.adapter!!.notifyDataSetChanged()
            return@setOnItemSelectedListener true
        }
    }

}