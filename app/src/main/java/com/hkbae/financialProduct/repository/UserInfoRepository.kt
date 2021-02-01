package com.hkbae.financialProduct.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.hkbae.financialProduct.model.FinancialProduct
import com.hkbae.financialProduct.model.UserInfo
import com.hkbae.financialProduct.service.UserInfoApiManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserInfoRepository(var userInfoLiveData : MutableLiveData<UserInfo>) {

    fun getRecommendedList(){
        UserInfoApiManager.service.getRecommendedList(userInfoLiveData.value!!).enqueue(object :
            Callback<List<FinancialProduct>>{
            override fun onResponse(
                call: Call<List<FinancialProduct>>,
                response: Response<List<FinancialProduct>>
            ) {
                if(response.isSuccessful){
                    //통신 성공
                    
                }else{
                    Log.d("recommend",response.code().toString())
                }
            }

            override fun onFailure(call: Call<List<FinancialProduct>>, t: Throwable) {
                Log.e("recommend",t.message)
            }

        })
    }
}