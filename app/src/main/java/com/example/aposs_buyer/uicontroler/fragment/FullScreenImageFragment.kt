package com.example.aposs_buyer.uicontroler.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentFullScreenImageBinding
import com.example.aposs_buyer.uicontroler.adapter.DetailProductImageViewPagerAdapter
import com.example.aposs_buyer.uicontroler.animation.ZoomOutPageTransformer
import com.example.aposs_buyer.viewmodel.DetailProductViewModel

class FullScreenImageFragment : Fragment() {

    private val args: FullScreenImageFragmentArgs by navArgs()
    private lateinit var binding: FragmentFullScreenImageBinding
    private val viewModel: DetailProductViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val currentPosition = args.position
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_full_screen_image,
            container,
            false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setUpViewPager(currentPosition)
        setUpIndicator()
        setUpBackButton()
        return binding.root
    }

    private fun setUpViewPager(currentPosition: Int) {
        val imagesAdapter =
            DetailProductImageViewPagerAdapter(DetailProductImageViewPagerAdapter.OnClickListener {
                //Do nothing
            })
        binding.images.setPageTransformer(ZoomOutPageTransformer())
        binding.images.adapter = imagesAdapter
        binding.images.currentItem = currentPosition
    }

    private fun setUpIndicator() {
        binding.indicator.setViewPager(binding.images)
    }
    private fun setUpBackButton(){
        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}