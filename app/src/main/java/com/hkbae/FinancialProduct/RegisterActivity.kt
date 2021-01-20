package com.hkbae.FinancialProduct

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.hkbae.FinancialProduct.databinding.ActivityRegisterBinding
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private lateinit var model : UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegisterBinding.inflate(layoutInflater)
        model=ViewModelProvider(this).get(UserViewModel::class.java)
        val view= binding.root
        setContentView(view)

        initRegister()

    }

    private fun initRegister(){
        val registerInfo:String = "회원가입을 위한 필수정보를 입력해주세요."
        val ssb:SpannableStringBuilder= SpannableStringBuilder(registerInfo)
        ssb.setSpan(ForegroundColorSpan(Color.parseColor("#03A9F4")),9,13,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.registerInfoText.setText(ssb)

        binding.registerBtn.setOnClickListener {
            //회원가입 코드 구현

            val name:String = binding.userInputName.text.toString()
            val born:String = binding.userInputBirth.text.toString()
            val email:String = binding.userInputEmail.text.toString()
            val password:String = binding.userInputPassword.text.toString()
            val password2:String = binding.userInputPassword2.text.toString()
            Log.d("test",password+"=="+password2)

            if(password!=password2){
                Toast.makeText(this@RegisterActivity,"패스워드가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            }else{
                model.liveData.value=User(email,password,name,born)

                //서버에 request 필요
                Toast.makeText(this@RegisterActivity,"가입에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                finish()
            }

        }
    }
}