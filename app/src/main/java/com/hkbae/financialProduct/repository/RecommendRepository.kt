package com.hkbae.financialProduct.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.hkbae.financialProduct.model.Deposit
import com.hkbae.financialProduct.model.FinancialProduct
import com.hkbae.financialProduct.model.SavingsData
import com.hkbae.financialProduct.model.UserInfo
import com.hkbae.financialProduct.service.UserInfoApiManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendRepository(var recommendedLiveData:MutableLiveData<ArrayList<FinancialProduct>>) {

    fun getRecommendedListSavingsData(userInfo : UserInfo){
        UserInfoApiManager.service.getRecommendedListSavingsData(userInfo).enqueue(object :
            Callback<List<SavingsData>>{
            override fun onResponse(
                call: Call<List<SavingsData>>,
                response: Response<List<SavingsData>>
            ) {
                if(response.isSuccessful){
                    //통신 성공
                    recommendedLiveData.value=response.body() as ArrayList<FinancialProduct>

                }else{
                    Log.d("recommend",response.code().toString())
                }
            }

            override fun onFailure(call: Call<List<SavingsData>>, t: Throwable) {
                Log.e("recommend",t.message)
            }

        })
    }

    fun getRecommendedListDeposit(userInfo : UserInfo){
        UserInfoApiManager.service.getRecommendedListDeposit(userInfo).enqueue(object :
            Callback<List<Deposit>>{
            override fun onResponse(call: Call<List<Deposit>>, response: Response<List<Deposit>>) {
                if(response.isSuccessful){
                    //통신 성공
                    recommendedLiveData.value=response.body() as ArrayList<FinancialProduct>

                }else{
                    Log.d("recommend",response.code().toString())
                }
            }

            override fun onFailure(call: Call<List<Deposit>>, t: Throwable) {
                Log.e("recommend",t.message)
            }

        })
    }


}