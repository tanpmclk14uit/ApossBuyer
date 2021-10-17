package com.example.aposs_buyer.uicontroler.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentOrderBinding
import com.example.aposs_buyer.uicontroler.adapter.OrderAdapter
import com.example.aposs_buyer.viewmodel.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderFragment : Fragment() {

    private lateinit var binding: FragmentOrderBinding

    private val viewModel: OrderViewModel by activityViewModels()

    private lateinit var orderAdapter: OrderAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_order, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        orderAdapter = OrderAdapter(OrderAdapter.OnClickListener{
            toOrderDetail(it.id)
            viewModel.setCurrentOrder(it)
        })
        binding.orders.adapter = orderAdapter
        setBottomBar()
        onCurrentOrderChange()
        setBackPress()
        return binding.root
    }
    private fun setBackPress(){
        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
    private fun toOrderDetail(orderId: Long){
        findNavController().navigate(OrderFragmentDirections.actionOrderFragmentToDetailOrderFragment(orderId))
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onCurrentOrderChange(){
        viewModel.currentListOrder.observe(this.viewLifecycleOwner, {
            orderAdapter.submitList(viewModel.currentListOrder.value)
            binding.orders.adapter!!.notifyDataSetChanged()
            if(viewModel.currentListOrder.value!!.isEmpty()){
                binding.emptyOrder.visibility = View.VISIBLE
            }else{
                binding.emptyOrder.visibility = View.GONE
            }
        })
    }

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

            return@setOnItemSelectedListener true
        }
    }

}