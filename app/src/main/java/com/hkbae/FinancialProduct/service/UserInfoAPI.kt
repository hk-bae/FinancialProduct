package com.hkbae.FinancialProduct.service

import com.hkbae.FinancialProduct.model.FinancialProduct
import com.hkbae.FinancialProduct.model.User
import com.hkbae.FinancialProduct.model.UserInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface UserInfoAPI {

    @GET("/recommend/")
    fun getRecommendedList(
        @Body userInfo : UserInfo
    ) : Call<List<FinancialProduct>>

    companion object {
        const val SERVER_URL: String = "http://172.26.19.57:8080"
    }


}