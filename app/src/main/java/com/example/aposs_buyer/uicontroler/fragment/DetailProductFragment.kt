package com.example.aposs_buyer.uicontroler.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.example.aposs_buyer.model.HomeProduct
import com.example.aposs_buyer.uicontroler.activity.CartSecondActivity
import com.example.aposs_buyer.uicontroler.adapter.*
import com.example.aposs_buyer.uicontroler.animation.ZoomOutPageTransformer
import com.example.aposs_buyer.utils.DialogType
import com.example.aposs_buyer.utils.LoadingState
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
        setUpBottomSheetDialog()
        onDetailProductChange()
        return binding.root
    }
    private fun setUpBottomSheetDialog(){
        callBottomSheet()
    }
    private fun callBottomSheet(){
        binding.addToCart.setOnClickListener {
            findNavController().navigate(DetailProductFragmentDirections.actionDetailProductFragmentToProductDetailDialogFragment(DialogType.CartDialog))

        }
        binding.buyNow.setOnClickListener{
            findNavController().navigate(DetailProductFragmentDirections.actionDetailProductFragmentToProductDetailDialogFragment(DialogType.CheckOutDialog))
        }
    }

    private fun setUpToNavigateCart(){
        binding.clCart.setOnClickListener {
            val intent = Intent(this.context, CartSecondActivity::class.java)
            startActivity(intent)
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
    private fun onDetailProductChange(){
        viewModel.productDetailLoadingState.observe(viewLifecycleOwner, {
            if(it == LoadingState.Loading){
                binding.detailProgress.visibility = View.VISIBLE
            }else{
                binding.detailProgress.visibility = View.GONE
                if(it == LoadingState.Fail){
                    Toast.makeText(this.requireContext(),"Loading fail", Toast.LENGTH_SHORT).show()
                }
            }
        })
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

