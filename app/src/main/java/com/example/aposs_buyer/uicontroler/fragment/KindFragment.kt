package com.example.aposs_buyer.uicontroler.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentKindBinding
import com.example.aposs_buyer.responsitory.database.AccountDatabase
import com.example.aposs_buyer.uicontroler.activity.CartActivity
import com.example.aposs_buyer.uicontroler.activity.DetailProductActivity
import com.example.aposs_buyer.uicontroler.activity.LoginActivity
import com.example.aposs_buyer.uicontroler.adapter.HomeProductAdapter
import com.example.aposs_buyer.viewmodel.KindViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KindFragment() : Fragment() {

    private lateinit var binding: FragmentKindBinding

    private val viewModel: KindViewModel by viewModels()
    private val args: KindFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kind, container, false)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = viewModel
        setUpFirstState()
        setUpAppBar()
        setUpProductList()
        return binding.root
    }

    private fun setUpAppBar() {
        // set up back button
        binding.imgBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        //set up cart button
        binding.cartLayout.setOnClickListener {
            if (isUserLoggedIn()) {
                startActivity(Intent(this.context, CartActivity::class.java))
            } else {
                startActivity(Intent(this.context, LoginActivity::class.java))
            }
        }
    }

    private fun isUserLoggedIn(): Boolean {
        return AccountDatabase.getInstance(this.requireContext()).accountDao.getAccount() != null
    }


    private fun setUpProductList() {
        //set up adapter
        binding.rcProducts.adapter = HomeProductAdapter(HomeProductAdapter.OnClickListener {
            // on item click
            val intent = Intent(this.context, DetailProductActivity::class.java)
            intent.putExtra("productID", it)
            startActivity(intent)
        })
    }

    private fun setUpFirstState() {
        if (args.kind.id != -1L) {
            viewModel.setSelectedKindIdAndName(args.kind.id, args.kind.name)
            viewModel.setSelectedProductKind()
        }
        // else handle exception here
    }
}