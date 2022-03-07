package com.example.aposs_buyer.uicontroler.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentRatingBinding
import com.example.aposs_buyer.uicontroler.adapter.AddingRatingImageAdapter
import com.example.aposs_buyer.viewmodel.RatingViewModel
import dagger.hilt.android.AndroidEntryPoint

import android.content.Intent
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK

import android.net.Uri
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.aposs_buyer.model.Image
import com.example.aposs_buyer.model.RateImage


@AndroidEntryPoint
class RatingFragment : Fragment() {

    private lateinit var binding: FragmentRatingBinding
    private val viewModel: RatingViewModel by viewModels()
    private val args: RatingFragmentArgs by navArgs()
    private val addingRatingImageAdapter = AddingRatingImageAdapter()
    private val PICK_IMAGE = 100
    var imageUri: Uri? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rating, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.setCurrentRatingItem(args.currentRateItem)
        binding.rcRateImg.adapter = addingRatingImageAdapter
        binding.rcRateImg.layoutManager = LinearLayoutManager(binding.rcRateImg.context, LinearLayoutManager.HORIZONTAL, false)
        binding.lnAddImg.setOnClickListener {
            openGallery()
        }
        binding.buttonAddImage.setOnClickListener {
            openGallery()
        }
        binding.btnSaveRate.setOnClickListener {
            Toast.makeText(this.context, "Save success", Toast.LENGTH_SHORT).show()
            findNavController().navigate(RatingFragmentDirections.actionRatingFragmentToRatedFragment())
        }
        binding.imgBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.clCart.setOnClickListener {
            // go to cart
        }
        return binding.root
    }


    private fun openGallery() {
        val galleryIntent: Intent = Intent()
        galleryIntent.action = Intent.ACTION_GET_CONTENT
        galleryIntent.type = "image/*"
        startActivityForResult(galleryIntent, 2)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            imageUri = data.data!!
            viewModel.onAddImage(RateImage(0, Image(imageUri.toString())))
            binding.buttonAddImage.visibility = View.GONE
            binding.lnAddImg.visibility = View.VISIBLE
            checkValidToAdd()
            setDisplayAmount()
            addingRatingImageAdapter.submitList(viewModel.listChoseImage.value)
            addingRatingImageAdapter.notifyDataSetChanged()
        }
    }

    private fun checkValidToAdd()
    {
        if (viewModel.listChoseImage.value!!.size == 3) {
            binding.lnAddImg.isEnabled = false
            binding.lnAddImg.visibility = View.GONE
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setDisplayAmount()
    {
        binding.tvAmount.text = "${viewModel.listChoseImage.value!!.size}/3"
    }
}