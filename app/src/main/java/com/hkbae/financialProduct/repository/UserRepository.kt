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

    fun getUserCountById(id : String) : Single<Int> =
        api.getUserCountById(id)
            .observeOn(AndroidSchedulers.mainThread())
             .subscribeOn(Schedulers.io())

    fun postUser(user : User) : Single<Int> =
        api.postUser(user)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

}
