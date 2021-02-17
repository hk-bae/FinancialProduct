package com.hkbae.financialProduct.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.hkbae.financialProduct.model.FinancialProduct
import com.hkbae.financialProduct.model.UserInfo
import com.hkbae.financialProduct.repository.RecommendRepository
import io.reactivex.Single

class RecommendedListViewModel(application: Application) : AndroidViewModel(application) {
    var liveData : MutableLiveData<ArrayList<FinancialProduct>> = MutableLiveData()
    var userRepository : RecommendRepository = RecommendRepository()


    @SuppressLint("CheckResult")
    fun getRecommendedList(userInfo:UserInfo){

        var single : Single<List<FinancialProduct>> = Single.just(listOf())

        when(userInfo.productType){
            0-> single=userRepository.getRecommendedListDeposit(userInfo)
            1-> single=userRepository.getRecommendedListSavingsData(userInfo)
        }

        single.subscribe { itemList, throwable ->
            liveData.value= itemList as ArrayList<FinancialProduct>
            Log.d("Rx",throwable?.message ?: "정상 처리")
        }

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