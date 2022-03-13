package com.example.aposs_buyer.uicontroler.fragment

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentAboutUsBinding
import com.example.aposs_buyer.uicontroler.adapter.DetailCategoryAdapter
import com.example.aposs_buyer.uicontroler.adapter.DetailCategoryViewPagerAdapter
import com.example.aposs_buyer.viewmodel.AboutUsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutUsFragment : Fragment() {

    private val viewModel: AboutUsViewModel by viewModels()
    private lateinit var binding: FragmentAboutUsBinding
    private var categoriesLeftToRight: Boolean = true
    private val categoriesRunnable: Runnable = Runnable() {
        kotlin.run {
            if (categoriesLeftToRight) {
                binding.imageViewPager.currentItem += 1

            } else {
                binding.imageViewPager.currentItem -= 1
            }

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about_us, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        val detailCategoryViewPagerAdapter =
            DetailCategoryViewPagerAdapter(DetailCategoryViewPagerAdapter.OnImageItemClickListener {

            })
        binding.imageViewPager.adapter = detailCategoryViewPagerAdapter
        binding.indicator.setViewPager(binding.imageViewPager)
        setUpViewPagerCallBack()
        binding.rlBackToBefore.setOnClickListener {
            requireActivity().onBackPressed()
        }
        return binding.root
    }

    private fun setUpViewPagerCallBack() {
        binding.imageViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                view!!.handler.removeCallbacks(categoriesRunnable)
                if (binding.imageViewPager.currentItem == viewModel.listImage.value!!.size - 1) {
                    categoriesLeftToRight = false
                } else {
                    if (binding.imageViewPager.currentItem == 0) {
                        categoriesLeftToRight = true
                    }
                }
                view!!.handler.postDelayed(categoriesRunnable, 4000)
            }
        })
    }
}