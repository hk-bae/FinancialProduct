package com.hkbae.financialProduct.view

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hkbae.financialProduct.model.User
import com.hkbae.financialProduct.viewModel.UserViewModel
import com.hkbae.financialProduct.databinding.ActivityRegisterBinding
import kotlinx.android.synthetic.main.activity_register.*
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private lateinit var model : UserViewModel
    private lateinit var callbackMethod : DatePickerDialog.OnDateSetListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegisterBinding.inflate(layoutInflater)
        model=ViewModelProvider(this).get(UserViewModel::class.java)
        val view= binding.root
        setContentView(view)

        initRegisterActivity()


        
    }

    private fun initRegisterActivity(){
        val registerInfo:String = "회원가입을 위한 필수정보를 입력해주세요."
        val ssb:SpannableStringBuilder= SpannableStringBuilder(registerInfo)
        ssb.setSpan(ForegroundColorSpan(Color.parseColor("#03A9F4")),9,13,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.registerInfoText.text=ssb

        //toolbar 설정
        setSupportActionBar(binding.toolbar)
        val actionBar=supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        initDateListener()

        val observer = Observer<User>{ user->
            if(user.id=="-1"){ //id가 중복인 경우
                Toast.makeText(this@RegisterActivity,"아이디가 중복됩니다.", Toast.LENGTH_SHORT).show()
            }else{ //가입 성공
                Toast.makeText(this@RegisterActivity,"가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show()

                //가입 성공한 사용자 정보를 함께 보낸다.
                val resultIntent = Intent()
                resultIntent.putExtra("user",user)
                setResult(Activity.RESULT_OK,resultIntent)
                finish()
            }
            Log.d("test","bye : ${user.id}")
            Log.d("test","name :${user.name}")
            Log.d("test","born :${user.born}")
            Log.d("test","pw :${user.password}")
        }

        model.liveData.observe(this, observer)

     }


    //회원정보 입력 후 다음 버튼 클릭 리스너
    fun onClickRegisterBtn(view: View) {

        val name:String = binding.userInputName.text.toString().trim()
        val born:String = binding.userInputBirthBtn.text.toString().trim()
        val id:String = binding.userInputId.text.toString().trim()
        val password:String = binding.userInputPassword.text.toString().trim()
        val password2:String = binding.userInputPassword2.text.toString().trim()



        if(name.isEmpty() || name.length>20){ //name check
            Toast.makeText(this@RegisterActivity,"이름을 확인해 주세요.", Toast.LENGTH_SHORT).show()
        }else if(born.isEmpty()){ //born check
            Toast.makeText(this@RegisterActivity,"생년월일을 확인해 주세요.",Toast.LENGTH_SHORT).show()
        }else if(id.isEmpty()||id.length>20){ //id check
            Toast.makeText(this@RegisterActivity,"아이디를 확인해 주세요.", Toast.LENGTH_SHORT).show()
        }else if(password!=password2 || password.isEmpty()||password.length>20){ //password check
            Toast.makeText(this@RegisterActivity,"패스워드를 확인해 주세요.", Toast.LENGTH_SHORT).show()
        }else{
            model.postUser(User(id,password,name,born)) //id 중복 체크
        }
    }


    //날짜 선택 시 User의 born에 저장
    fun initDateListener(){
        callbackMethod = DatePickerDialog.OnDateSetListener{view, year, month, dayOfMonth ->
            var born :String = "${year}.${month+1}.${dayOfMonth}"
            binding.userInputBirthBtn.text=born
        }
    }

    //생년월일 선택 버튼
    fun onClickBirthBtn(view: View) {


        val today = Calendar.getInstance().time
        val year:Int =SimpleDateFormat("yyyy",Locale.getDefault()).format(today).toInt()
        val month:Int =SimpleDateFormat("mm",Locale.getDefault()).format(today).toInt()
        val day:Int =SimpleDateFormat("dd",Locale.getDefault()).format(today).toInt()

        val dialog : DatePickerDialog = DatePickerDialog(this,callbackMethod,year,month,day)

        dialog.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            android.R.id.home-> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
