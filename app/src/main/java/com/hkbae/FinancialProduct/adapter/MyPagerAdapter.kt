package com.hkbae.FinancialProduct.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.hkbae.FinancialProduct.R


class MyPagerAdapter(
    val layoutInflater: LayoutInflater
) : PagerAdapter(){

    lateinit var registerdView : View

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
                registerdView=view
                return view
            }
            1->{

                val view = layoutInflater.inflate(R.layout.savings_product_recommend,container,false)
                container.addView(view)
                registerdView=view
                return view
            }
            else->{

                val view = layoutInflater.inflate(R.layout.deposit_recommend,container,false)
                container.addView(view)
                registerdView=view
                return view
            }
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }


}