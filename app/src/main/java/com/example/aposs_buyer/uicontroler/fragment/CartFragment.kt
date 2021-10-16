package com.example.aposs_buyer.uicontroler.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentCartBinding
import com.example.aposs_buyer.uicontroler.adapter.CartAdapter
import com.example.aposs_buyer.viewmodel.CartViewModel
import com.example.aposs_buyer.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import android.widget.Toast

import android.app.ListActivity
import android.content.Intent

import androidx.recyclerview.widget.RecyclerView

import androidx.recyclerview.widget.ItemTouchHelper
import com.example.aposs_buyer.model.Notification
import com.example.aposs_buyer.uicontroler.activity.NotificationActivity


@AndroidEntryPoint
class CartFragment : CartAdapter.ChangeAmount, Fragment(), CartAdapter.OnChoose {

    private val cartAdapter = CartAdapter(this, this)
    private val viewModel: CartViewModel by viewModels()
    private lateinit var binding: FragmentCartBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.rcCart.adapter = cartAdapter
        binding.rcCart.layoutManager = LinearLayoutManager(binding.rcCart.context, LinearLayoutManager.VERTICAL, false)

        viewModel.size.observe(viewLifecycleOwner, Observer {
            if (it==0) {
                binding.emptyCart.visibility = View.VISIBLE
                binding.fullfillCart.visibility = View.GONE
            }
            else
            {
                binding.emptyCart.visibility = View.GONE
                binding.fullfillCart.visibility = View.VISIBLE
            }
        })

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.rcCart)
        setUpNotification()
        return binding.root
    }

    private fun setUpNotification(){
        binding.notification.setOnClickListener {
            val intent = Intent(this.context, NotificationActivity::class.java)
            startActivity(intent)
        }
    }


    var simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
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
            val position = viewHolder.adapterPosition
            viewModel.removeItem(position)
            cartAdapter.notifyItemRemoved(position)
            cartAdapter.notifyItemRangeChanged(position, viewModel.lstCartItem.value!!.size)
            viewModel.reCalculateTotal()
        }
    }

    override fun onChangeAmount() {
        viewModel.reCalculateTotal()
    }

    override fun onChose(position: Int) {
        Log.i("viewModel", viewModel.lstCartItem.value!![position].isChoose.toString())
        cartAdapter.notifyItemChanged(position)
    }
}