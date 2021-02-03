package com.hkbae.financialProduct.service

import com.hkbae.financialProduct.model.FinancialProduct
import com.hkbae.financialProduct.model.UserInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserInfoAPI {

    @POST("/recommend/")
    fun getRecommendedList(
        @Body userInfo : UserInfo
    ) : Call<List<FinancialProduct>>

    companion object {
        const val SERVER_URL: String = "http://172.26.26.72:8080"
    }


}