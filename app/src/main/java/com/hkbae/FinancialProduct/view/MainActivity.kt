package com.hkbae.FinancialProduct

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.hkbae.FinancialProduct.adapter.MyPagerAdapter
import com.hkbae.FinancialProduct.databinding.ActivityLoginBinding
import com.hkbae.FinancialProduct.databinding.ActivityMainBinding
import com.hkbae.FinancialProduct.model.UserInfo
import com.hkbae.FinancialProduct.view.LoginActivity
import com.hkbae.FinancialProduct.viewModel.UserInfoViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var model : UserInfoViewModel
    private lateinit var pagerAdapter : MyPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        model = ViewModelProvider(this).get(UserInfoViewModel::class.java)
        val view=binding.root
        setContentView(view)

        init(view.context)

    }

    private fun init(context : Context){
        binding.tabLayout.addTab(tab_layout.newTab().setText("정기예금 추천"))
        binding.tabLayout.addTab(tab_layout.newTab().setText("적금 추천"))

        pagerAdapter= MyPagerAdapter(LayoutInflater.from(context))
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

    fun onClickRecommend(view: View) {

        //현재 선택한 탭에 따라 productType 결정
        val productType=binding.tabLayout.selectedTabPosition
        Log.d("main_test",productType.toString())


        val registeredView =pagerAdapter.registerdView

        val saveTermStr : String=registeredView.findViewById<EditText>(R.id.save_term).text.toString().trim()
        Log.d("main_test",saveTermStr+"안녕")

        val amountsStr : String =registeredView.findViewById<EditText>(R.id.amounts).text.toString().trim()//예치금 또는 월 납입금액
        val sexRadioGroup=registeredView.findViewById<RadioGroup>(R.id.sex_radio_group)

        if(saveTermStr.isEmpty()){
            Toast.makeText(this@MainActivity,"가입기간을 입력해 주세요.",Toast.LENGTH_LONG).show()
            return
        }else if(amountsStr.isEmpty()){
            Toast.makeText(this@MainActivity,"금액을 입력해 주세요.",Toast.LENGTH_LONG).show()
            return
        }else if(!sexRadioGroup.isSelected){
            Toast.makeText(this@MainActivity,"성별을 선택해 주세요.",Toast.LENGTH_LONG).show()
            return
        }





//            sexRadioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener{ group, checkedId ->
//                if(checkedId==R.id.male){
//                    sex=0 //남자
//                }else{
//                    sex=1//여자
//                }
//            })

        //sharedPreference에 저장된 born을 가져와서 나이 세팅
        val sharedPreferences=getSharedPreferences("user",Context.MODE_PRIVATE)
        val born =sharedPreferences.getString("born",null)
//        userInfo.setAge(born!!)

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
