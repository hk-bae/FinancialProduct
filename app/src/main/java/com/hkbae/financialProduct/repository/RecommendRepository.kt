package com.hkbae.financialProduct.repository

import com.hkbae.financialProduct.model.FinancialProduct
import com.hkbae.financialProduct.model.UserInfo
import com.hkbae.financialProduct.service.UserInfoApiManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RecommendRepository() {

    val api = UserInfoApiManager.service

    fun getRecommendedListSavingsData(userInfo: UserInfo) : Single<List<FinancialProduct>> =
        api.getRecommendedListSavingsData(userInfo)
            .observeOn(AndroidSchedulers.mainThread())
            .map{itemList ->itemList as List<FinancialProduct>}
            .subscribeOn(Schedulers.io())


    fun getRecommendedListDeposit(userInfo:UserInfo) : Single<List<FinancialProduct>> =
        api.getRecommendedListDeposit(userInfo)
            .observeOn(AndroidSchedulers.mainThread())
            .map{itemList ->itemList as List<FinancialProduct>}
            .subscribeOn(Schedulers.io())


}