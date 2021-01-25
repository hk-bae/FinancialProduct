package com.hkbae.FinancialProduct

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData


class UserViewModel(application : Application) : AndroidViewModel(application){

    var userRepository : UserRepository= UserRepository()
    var liveData : MutableLiveData<User> = userRepository.userLiveData


    fun getUserCountById(user:User){
        userRepository.getUserCountById(user)
    }

    fun postUser(){
        userRepository.postUser()
    }

//    fun postUser(user:User){
//        userRepository.postUser(user,getApplication())
//    }

}


