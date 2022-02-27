package com.example.aposs_buyer.uicontroler.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentHomeBinding
import com.example.aposs_buyer.model.HomeProduct
import com.example.aposs_buyer.model.RankingProduct
import com.example.aposs_buyer.uicontroler.activity.*
import com.example.aposs_buyer.uicontroler.adapter.CategoriesViewPagerAdapter
import com.example.aposs_buyer.uicontroler.adapter.HomeProductAdapter
import com.example.aposs_buyer.uicontroler.adapter.RankingViewPagerAdapter
import com.example.aposs_buyer.uicontroler.animation.DepthPageTransformer
import com.example.aposs_buyer.uicontroler.animation.ZoomOutPageTransformer
import com.example.aposs_buyer.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : RankingViewPagerAdapter.FavoriteInterface, Fragment(),
    CategoriesViewPagerAdapter.OnClickListener,
    ViewTreeObserver.OnScrollChangedListener {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    //set up auto slide category view pager
    private val mHandler: Handler = Handler()
    private var categoriesLeftToRight: Boolean = true
    private val categoriesRunnable: Runnable = Runnable() {
        kotlin.run {
            if (categoriesLeftToRight) {
                binding.imageViewPager.currentItem += 1

            } else {
                binding.imageViewPager.currentItem -= 1
            }

        }
    }

    //set up auto slide ranking view pager
    private var rankingLeftToRight: Boolean = true
    private val rankingRunnable: Runnable = Runnable {
        kotlin.run {
            if (rankingLeftToRight) {
                binding.rankingViewPager.currentItem += 1

            } else {
                binding.rankingViewPager.currentItem -= 1

            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewModel = viewModel
        binding.imageViewPager.adapter = CategoriesViewPagerAdapter(this)
        binding.rankingViewPager.adapter =
            RankingViewPagerAdapter(this, RankingViewPagerAdapter.OnClickListener {
                val intent = Intent(this.context, DetailProductActivity::class.java)
                intent.putExtra("productID", it)
                startActivity(intent)
            })
        binding.products.adapter = HomeProductAdapter(HomeProductAdapter.OnClickListener {
            val intent = Intent(this.context, DetailProductActivity::class.java)
            intent.putExtra("productID", it)
            startActivity(intent)
        })
        binding.tvShowAllCategory.setOnClickListener {
            val intent = Intent(this.context, CategoryActivity::class.java)
            startActivity(intent)
        }
        binding.searchBar.setOnClickListener {
            val intent = Intent(this.context, SearchActivity::class.java)
            startActivity(intent)
        }
        binding.search.setOnClickListener {
            val intent = Intent(this.context, SearchActivity::class.java)
            startActivity(intent)
        }
        binding.lnAboutUs.setOnClickListener {
            val intent = Intent(this.context, AboutUsActivity::class.java)
            startActivity(intent)
        }
        binding.clCategory.setOnClickListener {
            onNavigateToKind()
        }
        binding.lifecycleOwner = this
        setUpIndicator()
        setUpViewPagerCallBack()
        setUpViewPagerAnimation()
        setUpNotification()
        setUpNestedScrollView()
        return binding.root
    }

    private fun setUpNestedScrollView() {
        binding.scrollView.viewTreeObserver.addOnScrollChangedListener(this)
    }

    private fun setUpNotification() {
        binding.notification.setOnClickListener {
            val intent = Intent(this.context, NotificationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUpIndicator() {
        binding.indicator.setViewPager(binding.imageViewPager)
        binding.rankingIndicator.setViewPager(binding.rankingViewPager)
    }

    private fun setUpViewPagerCallBack() {
        binding.imageViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mHandler.removeCallbacks(categoriesRunnable)
                if (binding.imageViewPager.currentItem == viewModel.categories.value!!.size - 1) {
                    categoriesLeftToRight = false
                } else {
                    if (binding.imageViewPager.currentItem == 0) {
                        categoriesLeftToRight = true
                    }
                }
                mHandler.postDelayed(categoriesRunnable, 4000)
                viewModel.setUpDisplayCategory(binding.imageViewPager.currentItem)
            }
        })
        binding.rankingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mHandler.removeCallbacks(rankingRunnable)
                if (binding.rankingViewPager.currentItem == viewModel.rankingProducts.value!!.size - 1) {
                    rankingLeftToRight = false
                } else {
                    if (binding.rankingViewPager.currentItem == 0) {
                        rankingLeftToRight = true
                    }
                }
                mHandler.postDelayed(rankingRunnable, 4000)
                viewModel.setCurrentProductKind(binding.rankingViewPager.currentItem)
            }
        })
    }

    private fun setUpViewPagerAnimation() {
        binding.imageViewPager.setPageTransformer(DepthPageTransformer())
        binding.rankingViewPager.setPageTransformer(ZoomOutPageTransformer())
    }

    override fun onPause() {
        super.onPause()
        mHandler.removeCallbacks(categoriesRunnable)
        mHandler.removeCallbacks(rankingRunnable)
    }

    override fun onResume() {
        super.onResume()
        mHandler.postDelayed(categoriesRunnable, 4000)
        mHandler.postDelayed(rankingRunnable, 4000)
    }

    override fun addToFavorite(product: RankingProduct) {
        viewModel.addNewFavoriteProduct(product.id)
    }

    override fun removeFromFavorite(product: RankingProduct) {
        viewModel.removeFavoriteProduct(product.id)
    }

    fun onNavigateToKind() {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToKindFragment(
                viewModel.displayCategory.value!!.id,
                viewModel.displayCategory.value!!.name
            )
        )
    }

    override fun onClick() {
        onNavigateToKind()
    }

    override fun onScrollChanged() {
        val view: View = binding.scrollView.getChildAt(binding.scrollView.childCount - 1)
        val bottomDetector = view.bottom - (binding.scrollView.height + binding.scrollView.scrollY)
        if (bottomDetector <= 0) {
            viewModel.loadProducts()
        }
    }

}

