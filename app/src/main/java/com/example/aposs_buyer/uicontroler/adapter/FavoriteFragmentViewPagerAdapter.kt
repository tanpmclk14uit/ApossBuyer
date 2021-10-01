package com.example.aposs_buyer.uicontroler.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.aposs_buyer.uicontroler.fragment.AllFavoriteFragment
import com.example.aposs_buyer.uicontroler.fragment.AvailableFavoriteFragment

class FavoriteFragmentViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllFavoriteFragment()
            else -> AvailableFavoriteFragment()
        }
    }

}