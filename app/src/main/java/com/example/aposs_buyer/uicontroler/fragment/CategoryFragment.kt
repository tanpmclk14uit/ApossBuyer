package com.example.aposs_buyer.uicontroler.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentCategoryBinding
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
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        if (args.category.id != -1L) {
            viewModel.setSelectedKindIdAndName(args.category.id, args.category.name)
            viewModel.setSelectedCategory()
        }
        binding.rcKind.adapter = KindAdapter(KindAdapter.OnClickListener {
            findNavController().navigate(
                CategoryFragmentDirections.actionCategoryFragmentToKindFragment(
                    it
                )
            )
        })
        binding.rcKind.layoutManager =
            LinearLayoutManager(binding.rcKind.context, LinearLayoutManager.VERTICAL, false)

        binding.imgBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        return binding.root
    }

}