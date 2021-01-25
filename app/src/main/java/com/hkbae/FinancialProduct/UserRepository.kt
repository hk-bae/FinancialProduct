package com.hkbae.FinancialProduct

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    var userLiveData : MutableLiveData<User> = MutableLiveData()

    //DB에 id가 일치하는 사용자가 존재하는지 확인
    fun getUserCountById(user :User) {

        UserApiManager.service.getUserCount(user.id).enqueue(object : Callback<Int> {

            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if (response.isSuccessful) {
                    var count = response.body()
                    if(count!=0){
                        user.id="-1" //중복 id 체크 표시
                    }
                    userLiveData.value=user
                }
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
            }
        })

    }

    //서버에 Post요청
    fun postUser() {

        UserApiManager.service.postUser(userLiveData.value!!).enqueue(object : Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if (response.isSuccessful) {
                }
            }
            override fun onFailure(call: Call<Int>, t: Throwable) {
            }
        })

    }



}
