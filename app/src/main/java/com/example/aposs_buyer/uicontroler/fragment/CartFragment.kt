package com.example.aposs_buyer.uicontroler.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentCartBinding
import com.example.aposs_buyer.uicontroler.activity.AboutUsActivity
import com.example.aposs_buyer.uicontroler.activity.CheckOutActivity
import com.example.aposs_buyer.uicontroler.activity.NotificationActivity
import com.example.aposs_buyer.uicontroler.activity.SearchActivity
import com.example.aposs_buyer.uicontroler.adapter.CartAdapter
import com.example.aposs_buyer.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CartFragment : CartAdapter.ChangeAmount, Fragment() {

    private lateinit var cartAdapter: CartAdapter
    private val viewModel: CartViewModel by activityViewModels()
    private lateinit var binding: FragmentCartBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = viewModel
        // set up app bar: search, logo button, notification button
        setUpAppBar()
        // set up cart recycle view
        setUpCartsView()
        // set up check out button and total bill view
        setUpCheckOutBottomBar()

        return binding.root
    }

    private fun setUpAppBar() {
        // set up search bar
        binding.search.setOnClickListener {
            val intent = Intent(this.context, SearchActivity::class.java)
            startActivity(intent)
        }
        // set up notification
        binding.imgNotification.setOnClickListener {
            val intent = Intent(this.context, NotificationActivity::class.java)
            startActivity(intent)
        }
        // set up logo button
        binding.lnAboutUs.setOnClickListener {
            val intent = Intent(this.context, AboutUsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUpCartsView() {
        //set up cart adapter
        cartAdapter = CartAdapter(this, CartAdapter.OnClickListener {
            if (it.isChoose) {
                viewModel.choseList.value!!.add(it)
            } else {
                viewModel.choseList.value!!.remove(it)
            }
            viewModel.reCalculateTotal()
            viewModel.trackingEnableCheckOutButton()
        })
        binding.rcCart.adapter = cartAdapter
        // set up swipe cart item to delete
        val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.DOWN or ItemTouchHelper.UP
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                //Remove swiped item from list and notify the RecyclerView
                val position = viewHolder.layoutPosition
                cartAdapter.notifyItemRemoved(position)
                cartAdapter.notifyItemRangeChanged(position, viewModel.lstCartItem.value!!.size)
                viewModel.removeItem(position)
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.rcCart)
    }

    private fun setUpCheckOutBottomBar() {
        // set up button check out
        binding.btnGoToCheckOut.setOnClickListener {
            val order = viewModel.makeNewOrder()
            val intent = Intent(this.context, CheckOutActivity::class.java)
            intent.putExtra("order", order)
            startActivity(intent)
        }
    }

    override fun onChangeAmount() {
        viewModel.reCalculateTotal()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadCartList()
    }

    override fun onPause() {
        super.onPause()
        viewModel.updateCart()
    }
}