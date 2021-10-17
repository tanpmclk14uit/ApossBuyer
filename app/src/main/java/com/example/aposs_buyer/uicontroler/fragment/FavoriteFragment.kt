package com.example.aposs_buyer.uicontroler.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.example.aposs_buyer.R
import com.example.aposs_buyer.databinding.FragmentFavoriteBinding
import com.example.aposs_buyer.uicontroler.activity.AboutUsActivity
import com.example.aposs_buyer.uicontroler.adapter.FavoriteFragmentViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        binding.lifecycleOwner = this
        binding.viewPager.adapter = FavoriteFragmentViewPagerAdapter(parentFragmentManager, binding.lifecycleOwner!!.lifecycle)
        binding.lnAboutUs.setOnClickListener {
            val intent = Intent(this.context, AboutUsActivity::class.java)
            startActivity(intent)
        }
        TabLayoutMediator(binding.tab, binding.viewPager
        ) { tab, position ->
            if(position == 0){
                tab.text = "All items"
            }else{
                tab.text ="Available"
            }
        }.attach()
        return binding.root
    }
}