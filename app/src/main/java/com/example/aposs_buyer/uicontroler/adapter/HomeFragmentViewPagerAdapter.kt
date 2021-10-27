package com.example.aposs_buyer.uicontroler.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.aposs_buyer.uicontroler.fragment.*

class HomeFragmentViewPagerAdapter(fragmentManager: FragmentManager,  lifecycle: Lifecycle):
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> HomeFragment()
            1-> FavoriteFragment()
            2-> MessageFragment()
            3-> CartFragment()
            else-> PersonFragment()
        }
    }
}