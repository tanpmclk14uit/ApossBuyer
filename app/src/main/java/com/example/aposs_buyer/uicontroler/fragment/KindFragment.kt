package com.example.aposs_buyer.uicontroler.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentKindBinding
import com.example.aposs_buyer.viewmodel.KindViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KindFragment : Fragment() {

    private lateinit var binding: FragmentKindBinding
    private val args: KindFragmentArgs by navArgs()
    private val viewModel: KindViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kind, container, false)
        binding.lifecycleOwner =  this
        binding.viewModel = viewModel

        if (args.categoryId != -1L)
        {
            viewModel.setSelectedKindID(args.categoryId)
        }
        return binding.root
    }

}