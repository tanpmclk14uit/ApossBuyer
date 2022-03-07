package com.example.aposs_buyer.uicontroler.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentDetailOrderBinding
import com.example.aposs_buyer.uicontroler.activity.AddressActivity
import com.example.aposs_buyer.uicontroler.activity.RatingActivity
import com.example.aposs_buyer.uicontroler.adapter.BillingItemsAdapter
import com.example.aposs_buyer.uicontroler.adapter.OrderDeliveringStateAdapter
import com.example.aposs_buyer.uicontroler.dialog.YesNoOrderSuccessStatusDialog
import com.example.aposs_buyer.utils.OrderStatus
import com.example.aposs_buyer.viewmodel.OrderDetailViewModel
import com.example.aposs_buyer.viewmodel.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailOrderFragment : Fragment(), YesNoOrderSuccessStatusDialog.SuccessClick{

    private lateinit var binding: FragmentDetailOrderBinding
    private val viewModelOrders: OrderViewModel by activityViewModels()
    private val viewModel: OrderDetailViewModel by viewModels()
    private lateinit var orderDetailBillingItem: BillingItemsAdapter
    private lateinit var dialog: YesNoOrderSuccessStatusDialog

    private val args: DetailOrderFragmentArgs by navArgs()

    private lateinit var orderDeliveringStateAdapter: OrderDeliveringStateAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_detail_order, container, false )
        if(viewModelOrders.currentOrder != null){
            viewModel.setCurrentOrder(viewModelOrders.currentOrder!!)
        }else{
            if(args.id != -1L){
                viewModel.setDetailOrderById(args.id)
            }else{
                Toast.makeText(this.context, "Unknown error", Toast.LENGTH_SHORT).show()
            }
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        orderDetailBillingItem = BillingItemsAdapter()
        binding.billingItems.adapter = orderDetailBillingItem
        orderDeliveringStateAdapter = OrderDeliveringStateAdapter()
        binding.deliveringState.adapter = orderDeliveringStateAdapter
        dialog = YesNoOrderSuccessStatusDialog(requireActivity(), this)
        setStatusValue(viewModelOrders.currentOrder!!.status)
        setShowButtonByStatus(viewModelOrders.currentOrder!!.status)
        setBackPress()
        setEditAddress()
        setCancelOrder()
        setCartPress()
        onRatingClick()
        setSuccessOrder()
        return binding.root
    }
    private fun setCartPress(){
        binding.cart.setOnClickListener {
            // go to cart
        }
    }
    private fun onRatingClick(){
        binding.ratingNow.setOnClickListener {
            startActivity(Intent(this.context, RatingActivity::class.java))
        }
    }


    private fun setBackPress(){
        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
            viewModelOrders.loadDeliveringOrder()
        }
    }
    private fun setCancelOrder(){
        binding.cancel.setOnClickListener {
            findNavController().navigate(DetailOrderFragmentDirections.actionDetailOrderFragmentToCancelOrderFragment(viewModel.detailOrder.value!!.id))
        }
    }

    private fun setSuccessOrder()
    {
        binding.Success.setOnClickListener {
            dialog.loadingDialog()
        }
    }

    private fun setEditAddress(){
        binding.editAddress.setOnClickListener {
           startActivity(Intent(this.context, AddressActivity::class.java))
        }
    }


    private fun setShowButtonByStatus(status: OrderStatus){
        if(status == OrderStatus.Success){
            binding.ratingNow.visibility = View.VISIBLE
        }else{
            binding.ratingNow.visibility = View.GONE
        }
        if(status == OrderStatus.Pending || status == OrderStatus.Confirmed){
            binding.cancel.visibility = View.VISIBLE
            binding.editAddress.visibility = View.VISIBLE
        }else{
            binding.cancel.visibility = View.GONE
            binding.editAddress.visibility = View.GONE
        }

        if (status == OrderStatus.Delivering)
        {
            binding.Success.visibility = View.VISIBLE
        }else{
            binding.Success.visibility = View.GONE
        }

    }

    private fun setStatusValue(orderStatus: OrderStatus)
    {
        binding.statusString.text = orderStatus.toString()
        if (orderStatus == OrderStatus.Pending)
        {
            binding.statusIcon.setImageResource(R.drawable.ic_order_pending)
        } else if (orderStatus == OrderStatus.Confirmed)
        {
            binding.statusIcon.setImageResource(R.drawable.ic_order_confirm)
        } else if (orderStatus == OrderStatus.Delivering)
        {
            binding.statusIcon.setImageResource(R.drawable.ic_order_delivering)
        }else if (orderStatus == OrderStatus.Cancel)
        {
            binding.statusIcon.setImageResource(R.drawable.ic_order_cancel)
        } else {
            binding.statusIcon.setImageResource(R.drawable.ic_order_pass)
        }
    }

    override fun onSuccessClick() {
        viewModel.successOrder(args.id)
        setStatusValue(OrderStatus.Success)
        setShowButtonByStatus(OrderStatus.Success)
    }
}