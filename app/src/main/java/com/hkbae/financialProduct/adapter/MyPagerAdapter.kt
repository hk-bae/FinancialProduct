package com.hkbae.financialProduct.adapter


import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.hkbae.financialProduct.view.RecommendFragment


class MyPagerAdapter(
    fragmentManager : FragmentManager,
    private val tabCount : Int
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {

        val fragment=RecommendFragment()

        when(position){
            0 -> fragment.productType=0
            1->  fragment.productType=1
        }
        Log.d("lifeCycle_test"," return fragment - ${fragment.productType}")
        return fragment
    }


}