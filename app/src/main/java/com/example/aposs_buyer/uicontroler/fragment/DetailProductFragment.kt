package com.example.aposs_buyer.uicontroler.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentDetailProductBinding
import com.example.aposs_buyer.model.HomeProduct
import com.example.aposs_buyer.uicontroler.adapter.*
import com.example.aposs_buyer.uicontroler.animation.ZoomOutPageTransformer
import com.example.aposs_buyer.viewmodel.DetailProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailProductFragment : Fragment(), StringDetailPropertyAdapter.PropertyStringValueSelected,
    ColorDetailPropertyAdapter.PropertyColorValueSelected,
        HomeProductAdapter.FavoriteInterface
{
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
        binding.lifecycleOwner = this
        if (selectedProductId != -1L) {
            viewModel.setSelectedProductId(selectedProductId)
        }
        setBackButton()
        setUpProductProperty()
        setUpSameKindProduct()
        setUpRatingComponent()
        setUpToNavigateCart()
        return binding.root
    }
    private fun setUpToNavigateCart(){
        binding.cart.setOnClickListener {
            this.findNavController().navigate(DetailProductFragmentDirections.actionDetailProductFragmentToCartFragment2())
        }
    }
    private fun setUpRatingComponent(){
        val ratingAdapter = RatingAdapter()
        binding.ratings.adapter = ratingAdapter
        binding.showAllRating.setOnClickListener {
            this.findNavController().navigate(DetailProductFragmentDirections.actionDetailProductFragmentToProductRatingFragment())
        }
    }
    private fun setUpProductProperty(){
        setShowAll()
        setUpViewPager()
        setUpIndicator()
        setUpLeftRightController()
        val stringPropertyAdapter = StringPropertyAdapter(this)
        val colorPropertyAdapter = ColorPropertyAdapter(this)
        binding.stringProperty.adapter = stringPropertyAdapter
        binding.colorProperty.adapter = colorPropertyAdapter
    }
    private fun setUpSameKindProduct(){
        val productAdapter = HomeProductAdapter(this, HomeProductAdapter.OnClickListener{
            this.findNavController().navigate(DetailProductFragmentDirections.actionDetailProductFragmentSelf(it))
        })
        binding.sameKindProduct.adapter = productAdapter
    }

    private fun setUpViewPager() {
        val imagesAdapter = DetailProductImageViewPagerAdapter(DetailProductImageViewPagerAdapter.OnClickListener{
            this.findNavController().navigate(DetailProductFragmentDirections.actionDetailProductFragmentToFullScreenImageFragment(it))
        })
        binding.images.setPageTransformer(ZoomOutPageTransformer())
        binding.images.adapter = imagesAdapter
    }

    private fun setBackButton() {
        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setUpLeftRightController() {
        binding.goToLeftImage.setOnClickListener {
            if (binding.images.currentItem != 0) {
                binding.images.currentItem -= 1
            } else {
                binding.images.currentItem = viewModel.selectedProductImages.value!!.size - 1
            }
        }
        binding.goToRightImage.setOnClickListener {
            if (binding.images.currentItem != viewModel.selectedProductImages.value!!.size - 1) {
                binding.images.currentItem += 1
            } else {
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
            } else {
                binding.description.maxLines = 10
            }
        }
    }

    override fun notifySelectedStringValueChange(propertyId: Long) {
        viewModel.notifySelectedStringPropertyChange(propertyId)
    }

    override fun notifySelectedColorValueChange(propertyId: Long) {
        viewModel.notifySelectedColorPropertyChange(propertyId)
    }

    override fun addToFavorite(product: HomeProduct) {
        //add to favorite
    }

    override fun removeFromFavorite(product: HomeProduct) {
        //remove from favorite
    }


}

