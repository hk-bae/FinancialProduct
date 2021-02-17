package com.hkbae.financialProduct.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object UserInfoApiManager {

        var retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(UserInfoAPI.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        var service : UserInfoAPI = retrofit.create(UserInfoAPI::class.java)


}