package com.example.aposs_buyer.uicontroler.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentHomeBinding
import com.example.aposs_buyer.uicontroler.activity.*
import com.example.aposs_buyer.uicontroler.adapter.CategoriesViewPagerAdapter
import com.example.aposs_buyer.uicontroler.adapter.HomeProductAdapter
import com.example.aposs_buyer.uicontroler.adapter.RankingViewPagerAdapter
import com.example.aposs_buyer.uicontroler.animation.DepthPageTransformer
import com.example.aposs_buyer.uicontroler.animation.ZoomOutPageTransformer
import com.example.aposs_buyer.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(),
    ViewTreeObserver.OnScrollChangedListener {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // set up binding inflater
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        // set up app bar: search, notification, logo button
        setUpAppBarSection()
        // set up categories, show all button
        setUpCategoriesSection()
        // set up ranking
        setUpRankingSection()
        // set up more product
        setUpViewMoreProductSection()
        return binding.root
    }

    private fun setUpViewMoreProductSection() {
        // set up product adapter
        binding.products.adapter = HomeProductAdapter(HomeProductAdapter.OnClickListener {
            val intent = Intent(this.context, DetailProductActivity::class.java)
            intent.putExtra("productID", it)
            startActivity(intent)
        })
        // set up scroll view to implement lazy loading
        binding.scrollView.viewTreeObserver.addOnScrollChangedListener(this)

    }

    private fun setUpAppBarSection() {
        // set up search bar
        binding.search.setOnClickListener {
            val intent = Intent(this.context, SearchActivity::class.java)
            startActivity(intent)
        }
        // set up notification button
        binding.notification.setOnClickListener {
            val intent = Intent(this.context, NotificationActivity::class.java)
            startActivity(intent)
        }
        // set up logo button
        binding.lnAboutUs.setOnClickListener {
            val intent = Intent(this.context, AboutUsActivity::class.java)
            startActivity(intent)
        }
    }

    //set up auto slide category view pager
    private var categoriesLeftToRight: Boolean = true
    private val categoriesRunnable: Runnable = Runnable {
        kotlin.run {
            if (categoriesLeftToRight) {
                binding.categoriesViewPager.currentItem += 1
            } else {
                binding.categoriesViewPager.currentItem -= 1
            }

        }
    }

    private fun setUpCategoriesSection() {
        //set up categories adapter
        binding.categoriesViewPager.adapter =
            CategoriesViewPagerAdapter(CategoriesViewPagerAdapter.OnClickListener { id, name ->
                // go to kind
            })
        // set up categories indicator
        binding.indicator.setViewPager(binding.categoriesViewPager)
        // set up show all categories button
        binding.tvShowAllCategory.setOnClickListener {
            val intent = Intent(this.context, CategoriesActivity::class.java)
            startActivity(intent)
        }
        // set up categories transform animation
        binding.categoriesViewPager.setPageTransformer(DepthPageTransformer())
        // set up automation slide
        binding.categoriesViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                view?.handler?.removeCallbacks(categoriesRunnable)
                if (binding.categoriesViewPager.currentItem == viewModel.categories.value!!.size - 1) {
                    categoriesLeftToRight = false
                } else {
                    if (binding.categoriesViewPager.currentItem == 0) {
                        categoriesLeftToRight = true
                    }
                }
                view?.handler?.postDelayed(categoriesRunnable, 4000)
                viewModel.setUpDisplayCategory(binding.categoriesViewPager.currentItem)
            }
        })
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

    private fun setUpRankingSection() {
        // set up raking adapter
        binding.rankingViewPager.adapter =
            RankingViewPagerAdapter(RankingViewPagerAdapter.OnClickListener {
                val intent = Intent(this.context, DetailProductActivity::class.java)
                intent.putExtra("productID", it)
                startActivity(intent)
            })
        // set up raking indicator
        binding.rankingIndicator.setViewPager(binding.rankingViewPager)
        // set up raking auto slide
        binding.rankingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                view?.handler?.removeCallbacks(rankingRunnable)
                if (binding.rankingViewPager.currentItem == viewModel.rankingProducts.value!!.size - 1) {
                    rankingLeftToRight = false
                } else {
                    if (binding.rankingViewPager.currentItem == 0) {
                        rankingLeftToRight = true
                    }
                }
                view?.handler?.postDelayed(rankingRunnable, 4000)
                viewModel.setCurrentProductKind(binding.rankingViewPager.currentItem)
            }
        })
        // set up raking animation
        binding.rankingViewPager.setPageTransformer(ZoomOutPageTransformer())
    }

    override fun onPause() {
        super.onPause()
        view?.handler?.removeCallbacks(categoriesRunnable)
        view?.handler?.removeCallbacks(rankingRunnable)
    }

    override fun onResume() {
        super.onResume()
        view?.handler?.postDelayed(categoriesRunnable, 4000)
        view?.handler?.postDelayed(rankingRunnable, 4000)
    }

    override fun onScrollChanged() {
        // lazy load implementation
        val view: View = binding.scrollView.getChildAt(binding.scrollView.childCount - 1)
        val bottomDetector = view.bottom - (binding.scrollView.height + binding.scrollView.scrollY)
        if (bottomDetector <= 0) {
            viewModel.loadProducts()
        }
    }

}

