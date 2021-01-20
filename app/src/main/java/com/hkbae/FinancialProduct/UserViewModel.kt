package com.hkbae.FinancialProduct

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel(){
    var liveData : MutableLiveData<User> = MutableLiveData()
}