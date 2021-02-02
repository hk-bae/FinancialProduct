package com.hkbae.financialProduct.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hkbae.financialProduct.R
import com.hkbae.financialProduct.adapter.RecyclerViewAdapter
import com.hkbae.financialProduct.databinding.ActivityLoginBinding
import com.hkbae.financialProduct.databinding.ActivityRecommendBinding
import com.hkbae.financialProduct.databinding.ActivityRegisterBinding
import com.hkbae.financialProduct.model.FinancialProduct
import com.hkbae.financialProduct.viewModel.RecommendedListViewModel
import kotlinx.android.synthetic.main.activity_recommend.*

class RecommendActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRecommendBinding
    private lateinit var model : RecommendedListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRecommendBinding.inflate(layoutInflater)
        model=ViewModelProvider(this).get(RecommendedListViewModel::class.java)
        val view = binding.root
        setContentView(view)

        model.liveData.observe(this, Observer<List<FinancialProduct>>{

            val adapter =RecyclerViewAdapter(model.liveData.value!!,this)

            binding.recyclerView.adapter=adapter
            binding.recyclerView.layoutManager=LinearLayoutManager(this@RecommendActivity)

        })

        init()

    }

    private fun init(){

        model.liveData.value= intent.getSerializableExtra("list") as ArrayList<FinancialProduct>?
        model.sortList(0) //초기 기본 금리 순 정렬

        binding.sortButton.setOnCheckedChangeListener{group: RadioGroup?, checkedId: Int ->
            when(checkedId){
                R.id.intr_rate -> model.sortList(0)
                R.id.intr_rate2 -> model.sortList(1)
                R.id.maturity_payment -> model.sortList(2)
            }
        }


    }

    fun onClickBackButton(view: View) {
        super.onBackPressed()
    }
}