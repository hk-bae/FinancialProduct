package com.hkbae.financialProduct.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserApiManager{
    var retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(UserAPI.SERVER_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    var service : UserAPI = retrofit.create(UserAPI::class.java)

}