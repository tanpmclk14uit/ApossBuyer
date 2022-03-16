package com.example.aposs_buyer.uicontroler.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.aposs_buyer.databinding.ActivityCartBinding
import com.example.aposs_buyer.responsitory.database.AccountDatabase
import com.example.aposs_buyer.uicontroler.adapter.CartAdapter
import com.example.aposs_buyer.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartActivity : CartAdapter.ChangeAmount, AppCompatActivity() {

    private lateinit var cartAdapter: CartAdapter
    private val viewModel: CartViewModel by viewModels()
    private lateinit var binding: ActivityCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setUpAppBar()
        // set up cart recycle view
        setUpCartsView()
        // set up check out button and total bill view
        setUpCheckOutBottomBar()
        setContentView(binding.root)
    }


    private fun setUpAppBar() {
        // set up notification
        binding.imgNotification.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }
        // set on back pressed
        binding.back.setOnClickListener {
            onBackPressed()
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
            val intent = Intent(this, CheckOutActivity::class.java)
            intent.putExtra("order", order)
            startActivity(intent)
        }
    }

    override fun onChangeAmount() {
        viewModel.reCalculateTotal()
    }


    override fun onPause() {
        super.onPause()
        viewModel.updateCart()
    }
}