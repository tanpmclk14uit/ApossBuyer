package com.example.aposs_buyer.uicontroler.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentDetailProductBinding
import com.example.aposs_buyer.uicontroler.adapter.DetailProductImageViewPagerAdapter
import com.example.aposs_buyer.uicontroler.animation.DepthPageTransformer
import com.example.aposs_buyer.uicontroler.animation.ZoomOutPageTransformer
import com.example.aposs_buyer.viewmodel.DetailProductViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailProductFragment : Fragment() {

    private lateinit var binding: FragmentDetailProductBinding
    private val viewModel: DetailProductViewModel by activityViewModels()

    private val imagesAdapter = DetailProductImageViewPagerAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_product, container, false)
        val selectedProductId: Long? = arguments?.getLong("productID")
        binding.viewModel = viewModel
        if (selectedProductId != null) {
            viewModel.setSelectedProductId(selectedProductId)
        }
        binding.lifecycleOwner = this
        setBackButton()
        setShowAll()
        setUpViewPager()
        setUpIndicator()
        setUpLeftRightController()
        return binding.root
    }
    private fun setUpViewPager(){
        binding.images.setPageTransformer(ZoomOutPageTransformer())
        binding.images.adapter = imagesAdapter
    }

    private fun setBackButton() {
        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
    private fun setUpLeftRightController(){
        binding.goToLeftImage.setOnClickListener {
            if(binding.images.currentItem != 0){
                binding.images.currentItem -=1
            }else{
                binding.images.currentItem = viewModel.selectedProductImages.value!!.size-1
            }
        }
        binding.goToRightImage.setOnClickListener {
            if(binding.images.currentItem != viewModel.selectedProductImages.value!!.size-1){
                binding.images.currentItem +=1
            }else{
                binding.images.currentItem = 0
            }
        }
    }
    private fun setUpIndicator() {
        binding.indicator.setViewPager(binding.images)
    }
    private fun setShowAll() {
        binding.showAll.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.description.maxLines = 1000
            }else{
                binding.description.maxLines= 10
            }
        }
    }
}

