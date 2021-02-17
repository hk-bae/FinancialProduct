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

    @SuppressLint("CheckResult")
    fun postUser(user:User){
        userRepository.getUserCountById(user.id) //id 중복 확인
            .subscribe { count,throwable ->
                if(count==0){ //중복된 id가 없는 경우
                    userRepository.postUser(user).subscribe { success,throwable -> //사용자 계정 생성
                        if(success==1){ //계정 생성 성공
                            liveData.value=user
                        }else{
                            Log.d("Rx",throwable?.message ?: "정상 처리")
                        }
                    }
                }else{ //중복된 id가 있는 경우
                    user.id = "-1"
                    liveData.value=user
                }

                Log.d("Rx",throwable?.message ?: "정상 처리")
            }
    }

}


