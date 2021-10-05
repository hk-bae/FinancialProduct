package com.hkbae.financialProduct.service

import com.hkbae.financialProduct.model.Deposit
import com.hkbae.financialProduct.model.SavingsData
import com.hkbae.financialProduct.model.UserInfo
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface UserInfoAPI {

    @POST("/recommend/")
    fun getRecommendedListSavingsData(
        @Body userInfo : UserInfo
    ) : Single<List<SavingsData>>


    @POST("/recommend/")
    fun getRecommendedListDeposit(
        @Body userInfo : UserInfo
    ) : Single<List<Deposit>>

    companion object {
        const val SERVER_URL: String = "http://10.0.2.2:8080"
    }


}