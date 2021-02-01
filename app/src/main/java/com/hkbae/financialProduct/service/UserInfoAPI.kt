package com.hkbae.financialProduct.service

import com.hkbae.financialProduct.model.FinancialProduct
import com.hkbae.financialProduct.model.UserInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface UserInfoAPI {

    @GET("/recommend/")
    fun getRecommendedList(
        @Body userInfo : UserInfo
    ) : Call<List<FinancialProduct>>

    companion object {
        const val SERVER_URL: String = "http://172.26.19.57:8080"
    }


}