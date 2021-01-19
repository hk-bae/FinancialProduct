package com.hkbae.FinancialProduct

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.hkbae.FinancialProduct.databinding.ActivityLoginBinding
import com.hkbae.FinancialProduct.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        init(view.context)

    }

    private fun init(context : Context){
        binding.tabLayout.addTab(tab_layout.newTab().setText("정기예금 추천"))
        binding.tabLayout.addTab(tab_layout.newTab().setText("적금 추천"))

        val pagerAdapter=MyPagerAdapter(LayoutInflater.from(context))
        binding.viewPager.adapter=pagerAdapter
        binding.tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager.currentItem=tab!!.position
            }
        })

        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
    }
}

class MyPagerAdapter(
    val layoutInflater: LayoutInflater
) : PagerAdapter(){

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view ==`object` as View
    }

    override fun getCount(): Int {
        return 2
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        when(position){
            0->{
                val view = layoutInflater.inflate(R.layout.deposit_recommend,container,false)
                container.addView(view)
                return view
            }
            1->{

                val view = layoutInflater.inflate(R.layout.savings_product_recommend,container,false)
                container.addView(view)
                return view
            }
            else->{

                val view = layoutInflater.inflate(R.layout.deposit_recommend,container,false)
                container.addView(view)
                return view
            }
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}