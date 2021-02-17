package com.hkbae.financialProduct.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.hkbae.financialProduct.service.UserApiManager
import com.hkbae.financialProduct.model.User
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository{

    val api= UserApiManager.service

    fun login(id:String, password: String) : Single<User> =
        api.login(id,password)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())


//    fun login_tmp(id:String, password:String){
//
//        //로그인 성공시 userLiveData의 value를 user로 변경.
//            UserApiManager.service.login(id,password).enqueue(object: Callback<User>{
//                override fun onResponse(call: Call<User>, response: Response<User>) {
//                if(response.isSuccessful){
//                    userLiveData.value=response.body()
//                    Log.d("login_test",userLiveData.value?.id)
//                    Log.d("login_test","통신 성공")
//                }else{
//                    Log.e("login_test",response.code().toString())
//                }
//            }
//
//            override fun onFailure(call: Call<User>, t: Throwable) {
//                    Log.e("login_test",t.message)
//            }
//
//        })
//    }

    fun getUserCountById(user:User){}

//    //DB에 id가 일치하는 사용자가 존재하는지 확인
//    fun getUserCountById_tmp(user : User) {
//
//        UserApiManager.service.getUserCount(user.id).enqueue(object : Callback<Int> {
//
//            override fun onResponse(call: Call<Int>, response: Response<Int>) {
//                if (response.isSuccessful) {
//                    var count = response.body()
//                    if(count!=0){
//                        user.id="-1" //중복 id 체크 표시
//                    }
//                    userLiveData.value=user
//                }
//            }
//
//            override fun onFailure(call: Call<Int>, t: Throwable) {
//            }
//        })
//
//    }

    fun postUser(){}

//    //서버에 Post요청
//    fun postUser_tmp() {
//
//        UserApiManager.service.postUser(userLiveData.value!!).enqueue(object : Callback<Int> {
//            override fun onResponse(call: Call<Int>, response: Response<Int>) {
//                if (response.isSuccessful) {
//                }
//            }
//            override fun onFailure(call: Call<Int>, t: Throwable) {
//            }
//        })
//
//    }



}
