package com.example.aposs_buyer.uicontroler.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentCartBinding
import com.example.aposs_buyer.databinding.FragmentCartSecondBinding
import com.example.aposs_buyer.model.dto.TokenDTO
import com.example.aposs_buyer.responsitory.database.AccountDatabase
import com.example.aposs_buyer.uicontroler.activity.*
import com.example.aposs_buyer.uicontroler.adapter.CartAdapter
import com.example.aposs_buyer.utils.LoadingState
import com.example.aposs_buyer.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartSecondFragment : Fragment(), CartAdapter.ChangeAmount {

   // private val cartAdapter = CartAdapter(this, this)
    private val viewModel: CartViewModel by activityViewModels()
    private lateinit var binding: FragmentCartSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart_second, container, false)
//        checkLogin()
//        binding.lifecycleOwner = this
//        binding.viewModel = viewModel
//        //binding.rcCart.adapter = cartAdapter
//        binding.rcCart.layoutManager =
//            LinearLayoutManager(binding.rcCart.context, LinearLayoutManager.VERTICAL, false)
//        binding.btnGoToCheckOut.setOnClickListener {
//            if (viewModel.isHoldProductSuccess()) {
//                Log.d("checkoutBussiness", "change page")
//                findNavController().navigate(CartSecondFragmentDirections.actionCartSecondFragmentToCheckOutFragment())
//            } else {
//                Toast.makeText(
//                    this.context,
//                    "Current having not enough quantity",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
//        binding.imgBack.setOnClickListener {
//            requireActivity().onBackPressed()
//        }
//        binding.imgNotification.setOnClickListener {
//            val intent = Intent(this.context, NotificationActivity::class.java)
//            startActivity(intent)
//        }
//        viewModel.size.observe(viewLifecycleOwner, Observer {
//            if (it == 0 && viewModel.loadingStatus.value == LoadingState.Success) {
//                binding.emptyCart.visibility = View.VISIBLE
//                binding.loadingMessage.text = this.resources.getString(R.string.cart_empty_2)
//                binding.fullfillCart.visibility = View.GONE
//                binding.checkoutLayout.visibility = View.GONE
//                binding.loadingProgress.visibility = View.GONE
//            } else {
//                binding.emptyCart.visibility = View.GONE
//                binding.fullfillCart.visibility = View.VISIBLE
//                binding.checkoutLayout.visibility = View.VISIBLE
//            }
//        })
//        viewModel.choseSize.observe(viewLifecycleOwner, Observer {
//            binding.btnGoToCheckOut.isEnabled = it != 0
//        })
//
//        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
//        itemTouchHelper.attachToRecyclerView(binding.rcCart)
        return binding.root
    }

    override fun onChangeAmount() {
        TODO("Not yet implemented")
    }
//
//    var simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
//        ItemTouchHelper.SimpleCallback(
//            0,
//            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.DOWN or ItemTouchHelper.UP
//        ) {
//        override fun onMove(
//            recyclerView: RecyclerView,
//            viewHolder: RecyclerView.ViewHolder,
//            target: RecyclerView.ViewHolder
//        ): Boolean {
//            return false
//        }
//
//        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
//            //Remove swiped item from list and notify the RecyclerView
//            val position = viewHolder.adapterPosition
//            viewModel.removeItem(position)
//            //cartAdapter.notifyItemRemoved(position)
//           // cartAdapter.notifyItemRangeChanged(position, viewModel.lstCartItem.value!!.size)
//            viewModel.reCalculateTotal()
//        }
//    }
//
//    override fun onChangeAmount() {
//        viewModel.reCalculateTotal()
//    }
//
//    override fun onChose(position: Int) {
//        viewModel.setNewChose()
//        viewModel.reCalculateTotal()
//        viewModel.setChoseSize()
//        //cartAdapter.notifyItemChanged(position)
//    }
//
//    private fun checkLogin() {
//        val accountDao = AccountDatabase.getInstance(this.requireContext()).accountDao
//        val account = accountDao.getAccount()
//        if (account != null) {
//            onLoadingStateChange()
//            onCartListChange()
//            //viewModel.loadCartList()
//            viewModel.loadDefaultAddress()
//        } else {
//            val intent = Intent(this.context, LoginActivity::class.java)
//            startActivity(intent)
//            requireActivity().finish()
//        }
//    }
//
//    private fun onCartListChange() {
//        viewModel.lstCartItem.observe(viewLifecycleOwner) {
//           //cartAdapter.submitList(it)
//        }
//    }
//
//    @SuppressLint("SetTextI18n")
//    private fun onLoadingStateChange() {
//        viewModel.loadingStatus.observe(viewLifecycleOwner) {
//            if (it == LoadingState.Loading) {
//                binding.emptyCart.visibility = View.VISIBLE
//                binding.fullfillCart.visibility = View.GONE
//                binding.loadingProgress.visibility = View.VISIBLE
//                binding.loadingMessage.text = "Loading..."
//            } else if (it == LoadingState.Fail) {
//                binding.emptyCart.visibility = View.VISIBLE
//                binding.loadingMessage.text = "Loading fail..."
//                binding.fullfillCart.visibility = View.GONE
//                binding.loadingProgress.visibility = View.GONE
//                binding.checkoutLayout.visibility = View.GONE
//            }
//        }
//    }
//
//    override fun onPause() {
//        super.onPause()
//        viewModel.updateCart()
//    }
}