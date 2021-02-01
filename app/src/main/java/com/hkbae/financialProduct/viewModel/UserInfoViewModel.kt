package com.hkbae.financialProduct.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.hkbae.financialProduct.model.UserInfo
import com.hkbae.financialProduct.repository.UserInfoRepository

class UserInfoViewModel (application : Application) : AndroidViewModel(application){
    var liveData : MutableLiveData<UserInfo> = MutableLiveData()
    var userRepository : UserInfoRepository = UserInfoRepository(liveData)

    init{
        liveData.value=UserInfo()
    }

    fun getRecommendedList(){
        userRepository.getRecommendedList()
    }

}