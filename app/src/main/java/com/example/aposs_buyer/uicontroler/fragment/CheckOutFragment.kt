package com.example.aposs_buyer.uicontroler.fragment

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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentCheckOutBinding
import com.example.aposs_buyer.responsitory.database.AccountDatabase
import com.example.aposs_buyer.uicontroler.activity.AddressActivity
import com.example.aposs_buyer.uicontroler.activity.SearchActivity
import com.example.aposs_buyer.uicontroler.adapter.CheckOutAdapter
import com.example.aposs_buyer.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckOutFragment : Fragment() {


    private lateinit var binding: FragmentCheckOutBinding
    private val viewModel: CartViewModel by activityViewModels()
    private val checkOutAdapter = CheckOutAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_check_out, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        checkLogin()
//        if(args.item != null){
//            viewModel.lstCartItem.value!!.clear()
//            viewModel.choseList.value!!.clear()
//            viewModel.lstCartItem.value!!.add(args.item!!)
//            viewModel.choseList.value!!.add(args.item!!)
//            viewModel.reCalculateTotal()
//        }
        binding.rcCheckOut.adapter = checkOutAdapter
        binding.rcCheckOut.layoutManager = LinearLayoutManager(binding.rcCheckOut.context, LinearLayoutManager.VERTICAL, false)
        binding.imgBack.setOnClickListener {
            viewModel.reduceHold()
            requireActivity().onBackPressed()
        }
        binding.btnConfirm.setOnClickListener {
            if (viewModel.defaultAddress.value!!.id == 0L && viewModel.defaultAddress.value!!.ward.name == "") {
                Toast.makeText(this.context, "Please choose default address", Toast.LENGTH_SHORT).show()
            }
            else {
                viewModel.addNewOrder()
                // thanh toan thanh cong, di den trang thong bao
            }
        }
        binding.clCart.setOnClickListener {

        }
        binding.imgCart2.setOnClickListener {

        }
        binding.imgEditAddress.setOnClickListener {
            val intent = Intent(this.context, AddressActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }
    private fun checkLogin(){
        val accountDao = AccountDatabase.getInstance(this.requireContext()).accountDao
        val account = accountDao.getAccount()
        if(account != null){
            //viewModel.tokenDTO = TokenDTO(accessToken = account.accessToken, account.tokenType, account.refreshToken)
        }else{
            val intent = Intent(this.context, SearchActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }
    override fun onResume() {
        super.onResume()
        Log.d("onResume", "Resuming")
        viewModel.loadDefaultAddress()
    }
}