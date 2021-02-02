package com.hkbae.financialProduct.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.hkbae.financialProduct.model.UserInfo
import java.text.SimpleDateFormat
import java.util.*

class UserInfoViewModel (application : Application) : AndroidViewModel(application){
    var liveData : MutableLiveData<UserInfo> = MutableLiveData()

    init{
        liveData.value=UserInfo()
    }


    fun addJoinPath(position:Int){
        when(position){
            0-> liveData.value?.joinPath?.set(0, "영업점")
            1-> liveData.value?.joinPath?.set(1, "인터넷")
            2-> liveData.value?.joinPath?.set(2, "스마트폰")
            3-> liveData.value?.joinPath?.set(3, "전화(텔레뱅킹)")
        }
    }

    fun addAvailableCities(city: UserInfo.City){
        liveData.value?.cities?.add(city)
    }

    fun addConditions(position :Int){
        when(position){

            0-> liveData.value?.conditions?.set(0,"기초생활수급자")
            1-> liveData.value?.conditions?.set(1,"소년소녀가장")
            2-> liveData.value?.conditions?.set(2,"북한이탈주민")
            3-> liveData.value?.conditions?.set(3,"결혼이민자")
            4-> liveData.value?.conditions?.set(4,"근로장려금")
            5-> liveData.value?.conditions?.set(5,"한부모가족지원")
            6-> liveData.value?.conditions?.set(6,"차상위계층")
        }
    }

    //나이가 YYYY.MM.DD 형식으로 들어오면 이를 이용해 만 나이를 계산
    fun setAge(born : String){

        val date= Date(System.currentTimeMillis())
        val sdf = SimpleDateFormat("yyyy.mm.dd")
        val today=sdf.format(date)

        val todayList=today.split(".")
        val bornList = born.split(".")

        var age : Int = (todayList[0].toInt() - bornList[0].toInt())

        if(todayList[1].toInt() > bornList[1].toInt()){ //생일이 지나지 않은 경우
            liveData.value?.age=age-1
        }else if(todayList[1].toInt() < bornList[1].toInt()){ //생일이 지난 경우
            liveData.value?.age=age
        }else{
            if(todayList[2].toInt() > bornList[2].toInt()){
                liveData.value?.age=age-1
            }else{
                liveData.value?.age=age
            }
        }

    }

}