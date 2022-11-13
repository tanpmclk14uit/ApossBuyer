package com.example.aposs_buyer.uicontroler.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentOrderBinding
import com.example.aposs_buyer.responsitory.database.AccountDatabase
import com.example.aposs_buyer.uicontroler.activity.CartActivity
import com.example.aposs_buyer.uicontroler.activity.LoginActivity
import com.example.aposs_buyer.uicontroler.activity.RatingActivity
import com.example.aposs_buyer.uicontroler.adapter.OrderAdapter
import com.example.aposs_buyer.utils.OrderStatus
import com.example.aposs_buyer.viewmodel.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderFragment : Fragment(), OrderAdapter.OrderInterface {

    private lateinit var binding: FragmentOrderBinding

    private val viewModel: OrderViewModel by viewModels()

    private lateinit var orderAdapter: OrderAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // view inflater
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_order, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setAppBar()
        setOrdersRecycleView()
        setBottomBar()
        return binding.root
    }

    private fun setAppBar() {
        // set back button
        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }
        // set cart button
        binding.cart.setOnClickListener {
            if (isUserLoggedIn()) {
                startActivity(Intent(this.context, CartActivity::class.java))
            } else {
                startActivity(Intent(this.context, LoginActivity::class.java))
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setOrdersRecycleView() {
        // init adapter
        orderAdapter = OrderAdapter(OrderAdapter.OnClickListener {
            // on order item click
            findNavController().navigate(
                OrderFragmentDirections.actionOrderFragmentToDetailOrderFragment(
                    it
                )
            )
        }, this)
        // set adapter
        binding.orders.adapter = orderAdapter
        // tracking data change
        viewModel.orders.observe(viewLifecycleOwner) {
            orderAdapter.submitList(it)
            orderAdapter.notifyDataSetChanged()
        }
    }

    private fun isUserLoggedIn(): Boolean {
        return AccountDatabase.getInstance(this.requireContext()).accountDao.getAccount() != null
    }


    private fun setBottomBar() {
        binding.bottomBar.setOnItemSelectedListener {
            when (it.title.toString()) {
                "Đang chờ" -> {
                    viewModel.getAllOrderByStatus(OrderStatus.Pending)
                }
                "Xác nhận" -> {
                    viewModel.getAllOrderByStatus(OrderStatus.Confirmed)
                }
                "Đang giao" -> {
                    viewModel.getAllOrderByStatus(OrderStatus.Delivering)
                }
                "Thành công" -> {
                    viewModel.getAllOrderByStatus(OrderStatus.Success)
                }
                "Đã hủy" -> {
                    viewModel.getAllOrderByStatus(OrderStatus.Cancel)
                }
                else -> {
                    return@setOnItemSelectedListener false
                }
            }
            return@setOnItemSelectedListener true
        }
        binding.bottomBar.selectedItemId = binding.bottomBar.menu.getItem(0).itemId
    }

    override fun onCheckOutClick(orderId: Long) {
        findNavController().navigate(OrderFragmentDirections.actionOrderFragmentToOnlineCheckOutInformationFragment(orderId))
    }

}