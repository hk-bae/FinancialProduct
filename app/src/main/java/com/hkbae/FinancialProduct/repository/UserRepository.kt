package com.hkbae.FinancialProduct.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.hkbae.FinancialProduct.service.UserApiManager
import com.hkbae.FinancialProduct.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(var userLiveData : MutableLiveData<User>) {

    fun login(id:String, password:String){

        //로그인 성공시 userLiveData의 value를 user로 변경.
        UserApiManager.service.login(id,password).enqueue(object: Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful){
                    userLiveData.value=response.body()
                    Log.d("login_test",userLiveData.value?.id)
                    Log.d("login_test","통신 성공")
                }else{
                    Log.e("login_test",response.code().toString())
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.e("login_test",t.message)
            }

        })
    }

    //DB에 id가 일치하는 사용자가 존재하는지 확인
    fun getUserCountById(user : User) {

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
