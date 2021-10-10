package com.example.aposs_buyer.uicontroler.fragment

import android.content.Intent
import android.os.Bundle
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
import com.example.aposs_buyer.databinding.FragmentKindBinding
import com.example.aposs_buyer.model.HomeProduct
import com.example.aposs_buyer.uicontroler.activity.DetailProductActivity
import com.example.aposs_buyer.uicontroler.adapter.HomeProductAdapter
import com.example.aposs_buyer.uicontroler.adapter.KindAdapter
import com.example.aposs_buyer.viewmodel.KindViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KindFragment : Fragment(), HomeProductAdapter.FavoriteInterface, KindAdapter.OnClickListenerInterface
{

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
        binding.rcKind.adapter = KindAdapter(this, HomeProductAdapter.OnClickListener {
            val intent = Intent(this.context, DetailProductActivity::class.java)
            intent.putExtra("productID", it)
            startActivity(intent) }, this)
        binding.rcKind.layoutManager = LinearLayoutManager(binding.rcKind.context, LinearLayoutManager.VERTICAL, false)
        if (args.categoryId != -1L)
        {
            viewModel.setSelectedKindIdAndName(args.categoryId, args.categoryName)
            viewModel.setSelectedCategory()
        }
        binding.imgBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        return binding.root
    }

    override fun addToFavorite(product: HomeProduct) {
        viewModel.addToFavorite(product)
    }

    override fun removeFromFavorite(product: HomeProduct) {
        viewModel.removeFromFavorite(product)
    }

    override fun onClick(id: Long, name: String) {
        findNavController().navigate(KindFragmentDirections.actionKindFragmentToProductOfKindFragment(id, name))
    }


}