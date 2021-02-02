package com.hkbae.financialProduct.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.hkbae.financialProduct.model.FinancialProduct
import com.hkbae.financialProduct.model.UserInfo
import com.hkbae.financialProduct.repository.RecommendRepository

class RecommendedListViewModel(application: Application) : AndroidViewModel(application) {
    var liveData : MutableLiveData<ArrayList<FinancialProduct>> = MutableLiveData()
    var userRepository : RecommendRepository = RecommendRepository(liveData)


    fun getRecommendedList(userInfo:UserInfo){
        userRepository.getRecommendedList(userInfo)
    }

    //mode 0 : 기본금리순, mode 1 : 최고금리순, mode 2: 만기지급액 순 정렬
    fun sortList(mode : Int){

        when(mode){
            0->{ //기본 금리 순 정렬
                liveData.value=ArrayList<FinancialProduct>(liveData.value?.sortedByDescending{it.option.intr_rate}!!)
          }

            1->{ //최고 금리 순
                liveData.value=ArrayList<FinancialProduct>(liveData.value?.sortedByDescending{it.option.intr_rate2}!!)
            }
            2->{
                liveData.value=ArrayList<FinancialProduct>(liveData.value?.sortedByDescending{it.maturityPayment}!!)
            }
        }

    }
}