package com.hkbae.FinancialProduct.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.hkbae.FinancialProduct.model.UserInfo
import com.hkbae.FinancialProduct.repository.UserInfoRepository

class UserInfoViewModel (application : Application) : AndroidViewModel(application){
    var liveData : MutableLiveData<UserInfo> = MutableLiveData()
    var userRepository : UserInfoRepository = UserInfoRepository(liveData)

    fun getRecommendedList(userInfo : UserInfo){
        userRepository.getRecommendedList(userInfo)
    }

}