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
                 liveData.value=liveData.value?.sortedWith(Comparator<FinancialProduct>{ p1,p2->
                     when{
                         p2.option.intr_rate>p1.option.intr_rate -> 1
                         p2.option.intr_rate<p1.option.intr_rate -> -1
                         else->0
                     }
                 }) as ArrayList<FinancialProduct>
            }
            1->{ //최고 금리 순
                liveData.value=liveData.value?.sortedWith(Comparator<FinancialProduct>{ p1,p2->
                    when{
                        p2.option.intr_rate2>p1.option.intr_rate2 -> 1
                        p2.option.intr_rate2<p1.option.intr_rate2 -> -1
                        else->0
                    }
                }) as ArrayList<FinancialProduct>
            }
            2->{
                liveData.value=liveData.value?.sortedWith(Comparator<FinancialProduct>{ p1,p2->
                    when{
                        p2.maturityPayment>p1.maturityPayment -> 1
                        p2.maturityPayment<p1.maturityPayment -> -1
                        else->0
                    }
                }) as ArrayList<FinancialProduct>
            }
        }

    }
}