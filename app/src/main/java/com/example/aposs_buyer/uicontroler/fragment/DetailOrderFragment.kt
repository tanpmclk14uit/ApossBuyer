package com.example.aposs_buyer.uicontroler.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentDetailOrderBinding
import com.example.aposs_buyer.responsitory.database.AccountDatabase
import com.example.aposs_buyer.uicontroler.activity.CartActivity
import com.example.aposs_buyer.uicontroler.activity.LoginActivity
import com.example.aposs_buyer.uicontroler.activity.OrderActivity
import com.example.aposs_buyer.uicontroler.activity.RatingActivity
import com.example.aposs_buyer.uicontroler.adapter.BillingItemsAdapter
import com.example.aposs_buyer.uicontroler.adapter.OrderDeliveringStateAdapter
import com.example.aposs_buyer.uicontroler.dialog.LoadingDialog
import com.example.aposs_buyer.uicontroler.dialog.YesNoOrderSuccessStatusDialog
import com.example.aposs_buyer.utils.LoadingStatus
import com.example.aposs_buyer.utils.OrderStatus
import com.example.aposs_buyer.viewmodel.OrderDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailOrderFragment : Fragment(), YesNoOrderSuccessStatusDialog.SuccessClick {

    private lateinit var binding: FragmentDetailOrderBinding
    private val viewModel: OrderDetailViewModel by activityViewModels()
    private lateinit var orderDetailBillingItem: BillingItemsAdapter
    private lateinit var dialog: YesNoOrderSuccessStatusDialog
    private lateinit var loadingDialog: LoadingDialog
    private val args: DetailOrderFragmentArgs by navArgs()
    companion object DetailOrderContext{
        const val context ="Order"
    }

    private lateinit var orderDeliveringStateAdapter: OrderDeliveringStateAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_detail_order,
            container,
            false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        // set current order
        viewModel.setCurrentOrder(args.currentOrder!!)
        // set up dialog confirm received order
        dialog = YesNoOrderSuccessStatusDialog(requireActivity(), this)
        loadingDialog = LoadingDialog(requireActivity())
        setUpAppBar()
        setUpAddress()
        setUpBillingItems()
        setUpTrackingOrder()
        setUpBottomBar()
        return binding.root
    }

    private fun setUpBillingItems() {
        // set up adapter
        orderDetailBillingItem = BillingItemsAdapter()
        binding.billingItems.adapter = orderDetailBillingItem
    }

    private fun setUpAppBar() {
        // set back button
        binding.back.setOnClickListener {
            startActivity(Intent(requireContext(), OrderActivity::class.java))
            requireActivity().finish()
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

    private fun setUpAddress() {
        binding.editAddress.setOnClickListener {
            findNavController().navigate(DetailOrderFragmentDirections.actionDetailOrderFragmentToChooseAddressFragment(DetailOrderContext.context))
        }
    }

    private fun setUpTrackingOrder() {
        // set up status icon
        setStatusValue(viewModel.detailOrder.value!!.status)
        // set up state tracking adapter
        orderDeliveringStateAdapter = OrderDeliveringStateAdapter()
    }

    private fun setUpBottomBar() {
        // tracking button
        setShowButtonByStatus(viewModel.detailOrder.value!!.status)
        // set cancel order button
        binding.cancel.setOnClickListener {
            findNavController().navigate(
                DetailOrderFragmentDirections.actionDetailOrderFragmentToCancelOrderFragment(
                    viewModel.detailOrder.value!!.id
                )
            )
        }
        // set rating order button
        binding.ratingNow.setOnClickListener {
            startActivity(Intent(this.context, RatingActivity::class.java))
        }
        // set delivered order button
        binding.received.setOnClickListener {
            // open confirm dialog for user
            dialog.loadingDialog()
        }
        // tracking change order status
        viewModel.loadStatus.observe(viewLifecycleOwner) {
            if (it == LoadingStatus.Loading) {
                loadingDialog.startLoading()
            } else {
                loadingDialog.dismissDialog()
                if (it == LoadingStatus.Success) {
                    Toast.makeText(
                        requireContext(),
                        "Received order success",
                        Toast.LENGTH_SHORT
                    ).show()
                    setStatusValue(OrderStatus.Success)
                    setShowButtonByStatus(OrderStatus.Success)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Received order fail!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun isUserLoggedIn(): Boolean {
        return AccountDatabase.getInstance(this.requireContext()).accountDao.getAccount() != null
    }


    private fun setShowButtonByStatus(status: OrderStatus) {
        if (status == OrderStatus.Success) {
            binding.ratingNow.visibility = View.VISIBLE
        } else {
            binding.ratingNow.visibility = View.GONE
        }
        if (status == OrderStatus.Pending || status == OrderStatus.Confirmed) {
            binding.cancel.visibility = View.VISIBLE
            binding.editAddress.visibility = View.VISIBLE
        } else {
            binding.cancel.visibility = View.GONE
            binding.editAddress.visibility = View.GONE
        }

        if (status == OrderStatus.Delivering) {
            binding.received.visibility = View.VISIBLE
        } else {
            binding.received.visibility = View.GONE
        }

    }

    private fun setStatusValue(orderStatus: OrderStatus) {
        binding.statusString.text = orderStatus.toString()
        when (orderStatus) {
            OrderStatus.Pending -> {
                binding.statusIcon.setImageResource(R.drawable.ic_order_pending)
            }
            OrderStatus.Confirmed -> {
                binding.statusIcon.setImageResource(R.drawable.ic_order_confirm)
            }
            OrderStatus.Delivering -> {
                binding.statusIcon.setImageResource(R.drawable.ic_order_delivering)
            }
            OrderStatus.Cancel -> {
                binding.statusIcon.setImageResource(R.drawable.ic_order_cancel)
            }
            else -> {
                binding.statusIcon.setImageResource(R.drawable.ic_order_pass)
            }
        }
    }

    override fun onSuccessClick() {
        viewModel.receivedOrder()
    }
}