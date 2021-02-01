package com.hkbae.financialProduct.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.hkbae.financialProduct.view.RecommendFragment


class MyPagerAdapter(
    fragmentManager : FragmentManager,
    val tabCount : Int
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            1-> return RecommendFragment(1)//적금
            else -> return RecommendFragment(0) //예금
        }
    }


}