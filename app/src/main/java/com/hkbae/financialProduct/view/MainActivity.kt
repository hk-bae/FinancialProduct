package com.hkbae.financialProduct

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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

        initMainActivity()

    }

    private fun initMainActivity(){

        //toolbar
        setSupportActionBar(binding.toolbar)
        val actionbar = supportActionBar
        actionbar?.setDisplayShowTitleEnabled(false)

        val pagerAdapter= MyPagerAdapter(supportFragmentManager,2)
        binding.viewPager.adapter=pagerAdapter

        binding.tabLayout.apply {
            addTab(this.newTab().setText("정기예금 추천"))
            addTab(this.newTab().setText("적금 추천"))

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    binding.viewPager.currentItem=tab!!.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

            })
        }

        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_logout ->{
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

        return super.onOptionsItemSelected(item)
    }

}
