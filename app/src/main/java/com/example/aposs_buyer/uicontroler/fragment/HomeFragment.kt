package com.example.aposs_buyer.uicontroler.fragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentHomeBinding
import com.example.aposs_buyer.uicontroler.adapter.CategoriesViewPagerAdapter
import com.example.aposs_buyer.uicontroler.animation.DepthPageTransformer
import com.example.aposs_buyer.uicontroler.animation.ZoomOutPageTransformer
import com.example.aposs_buyer.viewmodel.HomeViewModel

class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }
    private val categoriesHandler: Handler = Handler()

    private var leftToRight: Boolean = true
    private val categoriesRunnable: Runnable = Runnable() {
        kotlin.run {
            if(leftToRight){
                binding.imageViewPager.currentItem +=1
                if(binding.imageViewPager.currentItem == viewModel.categories.value!!.size-1){
                    leftToRight = false
                }
            }else{
                binding.imageViewPager.currentItem -=1
                if(binding.imageViewPager.currentItem == 0){
                    leftToRight = true
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewModel = viewModel
        binding.imageViewPager.adapter = CategoriesViewPagerAdapter()
        binding.lifecycleOwner = this
        setUpIndicator()
        setUpViewPagerCallBack()
        binding.imageViewPager.setPageTransformer(DepthPageTransformer())
        return binding.root
    }

    private fun setUpIndicator() {
        binding.indicator.setViewPager(binding.imageViewPager)
    }
    private fun setUpViewPagerCallBack(){
        binding.imageViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                categoriesHandler.removeCallbacks(categoriesRunnable)
                categoriesHandler.postDelayed(categoriesRunnable, 4000)
                viewModel.setUpDisplayCategory(binding.imageViewPager.currentItem)
            }
        })
    }

    override fun onPause() {
        super.onPause()
        categoriesHandler.removeCallbacks(categoriesRunnable)
    }

    override fun onResume() {
        super.onResume()
        categoriesHandler.postDelayed(categoriesRunnable, 4000)
    }

}

