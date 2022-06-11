package com.example.aposs_buyer.uicontroler.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentAboutUsBinding
import com.example.aposs_buyer.viewmodel.AboutUsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutUsFragment : Fragment() {

    private val viewModel: AboutUsViewModel by viewModels()
    private lateinit var binding: FragmentAboutUsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about_us, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.rlBackToBefore.setOnClickListener {
            requireActivity().onBackPressed()
        }
        return binding.root
    }
}