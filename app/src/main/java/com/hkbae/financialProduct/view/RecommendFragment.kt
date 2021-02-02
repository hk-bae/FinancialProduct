package com.hkbae.financialProduct.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hkbae.financialProduct.R
import com.hkbae.financialProduct.databinding.RecommendInputBinding
import com.hkbae.financialProduct.model.BaseInfo
import com.hkbae.financialProduct.model.FinancialProduct
import com.hkbae.financialProduct.model.User
import com.hkbae.financialProduct.model.UserInfo
import com.hkbae.financialProduct.service.UserApiManager
import com.hkbae.financialProduct.service.UserInfoApiManager
import com.hkbae.financialProduct.viewModel.RecommendedListViewModel
import com.hkbae.financialProduct.viewModel.UserInfoViewModel
import com.hkbae.financialProduct.viewModel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendFragment(val productType : Int) : Fragment(),View.OnClickListener {

    private  var _binding : RecommendInputBinding?=null
    private val binding get() = _binding!!
    private lateinit var model : UserInfoViewModel
    private lateinit var availableCitiesDialog : AlertDialog
    private lateinit var conditionsDialog:AlertDialog
    private lateinit var  recommendedListViewModel : RecommendedListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = RecommendInputBinding.inflate(inflater,container,false)
        val view= binding.root
        model = ViewModelProvider(this).get(UserInfoViewModel::class.java)
        recommendedListViewModel=ViewModelProvider(this).get(RecommendedListViewModel::class.java)
        initDialog()
        init()

        return view
    }

    private fun init(){

        //productType에 따라 amounts 이름 설정
        when(productType){
            1-> binding.amountsName.setText("월 납입 금액")
            else -> binding.amountsName.setText("예치금")
        }

        //productType
        model.liveData.value?.productType=productType //정기예금=0, 적금=1

        //방문 가능 지역 선택 버튼 클릭 리스너
        binding.visitableArea.setOnClickListener {
            availableCitiesDialog.show()
        }

        //우대 조건 선택 버튼 클릭 리스터
        binding.preferentialCondition.setOnClickListener {
            conditionsDialog.show()
        }

        //추천 받기 버튼 리스너
        binding.recommendBtn.setOnClickListener(this)

        val observer = Observer<ArrayList<FinancialProduct>>{ user->
            val intent= Intent(binding.root.context,RecommendActivity::class.java)
            intent.putExtra("list",recommendedListViewModel.liveData.value)
            startActivity(intent)
        }

        recommendedListViewModel.liveData.observe(viewLifecycleOwner,observer)

    }

    override fun onClick(view : View){

        val saveTermStr : String=binding.saveTerm.text.toString().trim()
        val amountsStr : String =binding.amounts.text.toString().trim()//예치금 또는 월 납입금액
        val sexRadioGroup=binding.sexRadioGroup
        val joinPath=getJoinPath()



        if(saveTermStr.isEmpty()){
            Toast.makeText(view.context,"가입기간을 입력해 주세요.", Toast.LENGTH_LONG).show()
            return
        }
        else if(amountsStr.isEmpty()){
            Toast.makeText(view.context,"금액을 입력해 주세요.", Toast.LENGTH_LONG).show()
            return
        }
        else if(joinPath==null){
            Toast.makeText(view.context,"가입경로를 선택해 주세요.", Toast.LENGTH_LONG).show()
        }
        else {
            model.liveData.value?.months = saveTermStr.toInt()
            model.liveData.value?.amounts = amountsStr.toInt()
            model.liveData.value?.sex = when (sexRadioGroup.checkedRadioButtonId) {
                R.id.male -> 0
                else -> 1
            }
            model.liveData.value?.joinPath = joinPath

            //sharedPreference에 저장된 born을 가져와서 나이 세팅
            val sharedPreferences = activity?.getSharedPreferences("user", Context.MODE_PRIVATE)
            val born = sharedPreferences?.getString("born", null)
            model.setAge(born!!)

            recommendedListViewModel.getRecommendedList(model.liveData.value!!)
        }

        Log.d("recommend_test","productType : "+model.liveData.value?.productType)
        Log.d("recommend_test","months : "+model.liveData.value?.months)
        Log.d("recommend_test","amounts : "+model.liveData.value?.amounts)
        Log.d("recommend_test","age : "+model.liveData.value?.age)
        Log.d("recommend_test","sex : "+model.liveData.value?.sex)

        model.liveData.value?.joinPath?.forEach {
            Log.d("recommend_test","joinPath : : "+it)
        }

        model.liveData.value?.cities?.forEach {
            Log.d("recommend_test","cities : "+it.cityName)
        }

        model.liveData.value?.conditions?.forEach {
        Log.d("recommend_test","conditions : "+it)
        }

    }

    private fun initDialog(){

        //UserInfo에 저장된 CityName들을 배열로 만든다.
        val cities=UserInfo.City.values()
        val citiesStrArr:Array<CharSequence> =Array<CharSequence>(16) { "" }
        for(i in 0 until cities.size){
            citiesStrArr[i]=cities[i].cityName
        }

        //선택 확인을 위한 Boolean 배열
        val citiesBooleanArr : BooleanArray=BooleanArray(16)

        //다중 선택 팝업을 위한 다이얼로그
        availableCitiesDialog= AlertDialog.Builder(binding.root.context)
            .setMultiChoiceItems(citiesStrArr, citiesBooleanArr,DialogInterface.OnMultiChoiceClickListener {
                    dialog, which, isChecked -> citiesBooleanArr[which]=isChecked //Boolean 배열에 선택 여부 저장
            }).setTitle("방문 가능 지역")
            .setPositiveButton("확인",DialogInterface.OnClickListener { dialog, which ->
                val inputCities = hashSetOf<UserInfo.City>()
                for(i in 0 until citiesBooleanArr.size){
                    if(citiesBooleanArr[i]){
                        inputCities.add(cities[i]) //선택된 도시 추가
                        Log.d("recommend_test",cities[i].cityName)
                    }
                }
                model.liveData.value?.cities=inputCities
            })
            .setNegativeButton("취소",null)
            .create()


        val conditions = arrayOf<CharSequence>("기초 생활 수급자","소년 소녀 가장","북한 이탈 주민",
            "결혼 이민자","근로 장려금 수급자","한 부모 가족 지원자","차상위 계층 대상자")

        //선택 확인을 위한 Boolean 배열
        val conditionsBooleanArr : BooleanArray=BooleanArray(conditions.size)

        //다중 선택 팝업을 위한 다이얼로그
        conditionsDialog= AlertDialog.Builder(binding.root.context)
            .setMultiChoiceItems(conditions, conditionsBooleanArr,DialogInterface.OnMultiChoiceClickListener {
                    dialog, which, isChecked -> conditionsBooleanArr[which]=isChecked //Boolean 배열에 선택 여부 저장
            }).setTitle("우대 조건")
            .setPositiveButton("확인",DialogInterface.OnClickListener { dialog, which ->
                val inputConditions =arrayOfNulls<String>(conditions.size)
                model.liveData.value?.conditions=inputConditions
                for(i in 0 until conditionsBooleanArr.size){
                    if(conditionsBooleanArr[i]){
                        model.addConditions(i) //우대 조건 추가
                        Log.d("recommend_test",conditions[i].toString())
                    }
                }
            }).setNegativeButton("취소",null)
            .create()


    }


    private fun getJoinPath (): Array<String?>? {
        val mobile = binding.mobile
        val office=binding.office
        val internet=binding.internet
        val telephone=binding.telephone

        val joinPath =arrayOfNulls<String>(4)

        if(office.isChecked()) joinPath[0]="영업점"
        if(internet.isChecked()) joinPath[1]="인터넷"
        if(mobile.isChecked()) joinPath[2]="스마트폰"
        if(telephone.isChecked()) joinPath[3]="전화(텔레뱅킹)"

        var check : Boolean = false
        //하나라도 체크 되었으면 check = true
        for( i in 0 until joinPath.size-1){
            if(joinPath[i]!=null) check=true
        }

        if(check==false) return null
        else return joinPath

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}