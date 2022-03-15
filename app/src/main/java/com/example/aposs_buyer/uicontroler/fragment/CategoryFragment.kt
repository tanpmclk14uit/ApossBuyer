package com.example.aposs_buyer.uicontroler.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentCategoryBinding
import com.example.aposs_buyer.responsitory.database.AccountDatabase
import com.example.aposs_buyer.uicontroler.activity.CartActivity
import com.example.aposs_buyer.uicontroler.activity.LoginActivity
import com.example.aposs_buyer.uicontroler.adapter.KindAdapter
import com.example.aposs_buyer.viewmodel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private val args: CategoryFragmentArgs by navArgs()
    private val viewModel: CategoryViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = viewModel
        setUpFirstState()
        setUpAppBar()
        setUpKindsList()
        return binding.root
    }

    private fun setUpFirstState() {
        if (args.category.id != -1L) {
            viewModel.setSelectedKindIdAndName(args.category.id, args.category.name)
            viewModel.setSelectedCategory()
        }
        // else handle error here
    }

    private fun setUpAppBar() {
        // set up back button
        binding.imgBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        // set up cart button
        binding.cartLayout.setOnClickListener {
            // go to cart
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

    private fun setUpKindsList() {
        // set up kind adapter
        binding.rcKind.adapter = KindAdapter(KindAdapter.OnClickListener {
            // when user click on kind item
            findNavController().navigate(
                CategoryFragmentDirections.actionCategoryFragmentToKindFragment(
                    it
                )
            )
        })
    }

}