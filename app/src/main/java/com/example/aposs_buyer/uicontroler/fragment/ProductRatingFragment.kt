package com.example.aposs_buyer.uicontroler.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentProductRatingBinding
import com.example.aposs_buyer.uicontroler.adapter.RatingAdapter
import com.example.aposs_buyer.viewmodel.DetailProductViewModel

class ProductRatingFragment : Fragment() {

    private lateinit var binding: FragmentProductRatingBinding

    private val viewModel: DetailProductViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_product_rating, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setUpFilter()
        setUpRatingComponent()
        setBackButton()
        setUpNavigateToCart()
        return binding.root
    }

    private fun setUpNavigateToCart(){
        binding.cart.setOnClickListener {
            findNavController().navigate(ProductRatingFragmentDirections.actionProductRatingFragmentToCartFragment2())
        }
    }
    private fun setUpRatingComponent() {
        val ratingAdapter = RatingAdapter()
        binding.ratings.adapter = ratingAdapter
    }


    private fun setUpFilter() {
        val statusValue = resources.getStringArray(R.array.filter)
        val arrayAdapter = ArrayAdapter(
            this.requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            statusValue
        )
        binding.filter.setAdapter(arrayAdapter)
        binding.filter.setText(statusValue[0], false)
        viewModel.selectedProductRatingFilter.value = viewModel.selectedProductRating.value
        viewModel.selectedProductTotalReviewFilter.value = viewModel.selectedProductTotalReview.value
        filter()
    }

    private fun filter() {
        //filter database to
    }

    private fun setBackButton() {
        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

}