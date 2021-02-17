package com.hkbae.financialProduct.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.hkbae.financialProduct.model.User
import com.hkbae.financialProduct.repository.UserRepository


class UserViewModel(application : Application) : AndroidViewModel(application){

    var liveData : MutableLiveData<User> = MutableLiveData()
    private val userRepository : UserRepository = UserRepository()


    //로그인 가능한 아이디인지 확인
    @SuppressLint("CheckResult")
    fun login(id:String, password:String){
        userRepository.login(id,password)
            .subscribe { user,throwable->
                liveData.value=user
                Log.d("Rx",throwable?.message ?: "정상 처리")
            }
    }

    fun getUserCountById(user: User){
        userRepository.getUserCountById(user)
    }

    fun postUser(){
        userRepository.postUser()
    }

}


