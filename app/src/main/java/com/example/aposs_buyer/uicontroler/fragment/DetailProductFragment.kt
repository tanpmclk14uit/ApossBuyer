package com.example.aposs_buyer.uicontroler.fragment

import android.annotation.SuppressLint
import android.content.Intent
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
import com.example.aposs_buyer.databinding.FragmentDetailProductBinding
import com.example.aposs_buyer.model.PropertyValue
import com.example.aposs_buyer.responsitory.database.AccountDatabase
import com.example.aposs_buyer.uicontroler.activity.CartActivity
import com.example.aposs_buyer.uicontroler.activity.CheckCompatibilityActivity
import com.example.aposs_buyer.uicontroler.activity.LoginActivity
import com.example.aposs_buyer.uicontroler.adapter.*
import com.example.aposs_buyer.uicontroler.animation.ZoomOutPageTransformer
import com.example.aposs_buyer.utils.DialogType
import com.example.aposs_buyer.utils.LoadingStatus
import com.example.aposs_buyer.viewmodel.DetailProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailProductFragment : Fragment(), StringDetailPropertyAdapter.PropertyStringValueSelected,
    ColorDetailPropertyAdapter.PropertyColorValueSelected {
    private val args: DetailProductFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailProductBinding
    private val viewModel: DetailProductViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_product, container, false)
        val selectedProductId: Long = args.id
        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        if (selectedProductId != -1L) {
            viewModel.setSelectedProductId(selectedProductId)
        }
        setUpAppBar()
        setUpProductProperty()
        setUpSameKindProduct()
        setUpBottomSheetDialog()
        onDetailProductChange()

        return binding.root
    }

    private fun setUpAppBar() {
        // set up back button
        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }
        // set up cart button
        binding.clCart.setOnClickListener {
            if (isUserLoggedIn()) {
                startActivity(Intent(this.context, CartActivity::class.java))
            } else {
                startActivity(Intent(this.context, LoginActivity::class.java))
            }
        }
    }

    private fun isUserLoggedIn(): Boolean {
        return AccountDatabase.getInstance(this.requireContext()).accountDao.getAccount() != null
    }

    private fun setUpBottomSheetDialog() {
        callBottomSheet()
    }

    private fun callBottomSheet() {
        binding.addToCart.setOnClickListener {
            if (isUserLoggedIn()) {
                findNavController().navigate(
                    DetailProductFragmentDirections.actionDetailProductFragmentToProductDetailDialogFragment(
                        DialogType.CartDialog
                    )
                )
            } else {
                startActivity(Intent(this.context, LoginActivity::class.java))
            }


        }
        binding.buyNow.setOnClickListener {
            if (isUserLoggedIn()) {
                findNavController().navigate(
                    DetailProductFragmentDirections.actionDetailProductFragmentToProductDetailDialogFragment(
                        DialogType.CheckOutDialog
                    )
                )
            } else {
                startActivity(Intent(this.context, LoginActivity::class.java))
            }

        }
    }


    private fun setUpProductProperty() {
        setShowAll()
        setUpViewPager()
        setUpIndicator()
        val stringPropertyAdapter = StringPropertyAdapter(this)
        val colorPropertyAdapter = ColorPropertyAdapter(this)
        binding.stringProperty.adapter = stringPropertyAdapter
        binding.colorProperty.adapter = colorPropertyAdapter
    }

    private fun setUpSameKindProduct() {
        val productAdapter = HomeProductAdapter(HomeProductAdapter.OnClickListener {
            this.findNavController()
                .navigate(DetailProductFragmentDirections.actionDetailProductFragmentSelf(it))
        })
        binding.sameKindProduct.adapter = productAdapter
    }

    private fun setUpViewPager() {
        val imagesAdapter =
            DetailProductImageViewPagerAdapter(DetailProductImageViewPagerAdapter.OnClickListener {
                this.findNavController().navigate(
                    DetailProductFragmentDirections.actionDetailProductFragmentToFullScreenImageFragment(
                        it
                    )
                )
            })
        binding.images.setPageTransformer(ZoomOutPageTransformer())
        binding.images.adapter = imagesAdapter
    }


    private fun setUpIndicator() {
        binding.indicator.setViewPager(binding.images)
    }

    private fun setShowAll() {
        binding.showAll.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.description.maxLines = 1000
            } else {
                binding.description.maxLines = 10
            }
        }
    }

    private fun onDetailProductChange() {
        viewModel.productDetailLoadingState.observe(viewLifecycleOwner) {
            if (it == LoadingStatus.Loading) {
                binding.detailProgress.visibility = View.VISIBLE
            } else {
                binding.detailProgress.visibility = View.GONE
                if (it == LoadingStatus.Fail) {
                    Toast.makeText(this.requireContext(), "Loading fail", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun notifySelectedColorValueChange(propertyValue: PropertyValue) {
        viewModel.notifySelectedPropertyChange(propertyValue)
    }

    override fun notifySelectedStringValueChange(propertyValue: PropertyValue) {
        viewModel.notifySelectedPropertyChange(propertyValue)
    }
}

