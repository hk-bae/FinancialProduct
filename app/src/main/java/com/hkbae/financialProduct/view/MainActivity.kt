package com.hkbae.financialProduct

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.tabs.TabLayout
import com.hkbae.financialProduct.adapter.MyPagerAdapter
import com.hkbae.financialProduct.databinding.ActivityMainBinding
import com.hkbae.financialProduct.view.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifeCycle_test","onCreate 호출됨")
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        init(view.context)

    }

    private fun init(context : Context){
        binding.tabLayout.addTab(tab_layout.newTab().setText("정기예금 추천"))
        binding.tabLayout.addTab(tab_layout.newTab().setText("적금 추천"))

        val pagerAdapter= MyPagerAdapter(supportFragmentManager,2)
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

    //로그아웃 기능
    fun onClickLogout(view: View) {
        val sharedPreferences = getSharedPreferences("user",Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()

        editor.apply{
            remove("id")
            remove("password")
            remove("born")
            remove("name")
            commit()
        }

        val intent = Intent(this@MainActivity,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
