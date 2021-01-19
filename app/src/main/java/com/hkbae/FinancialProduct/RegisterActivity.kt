package com.hkbae.FinancialProduct

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.Toast
import com.hkbae.FinancialProduct.databinding.ActivityRegisterBinding
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegisterBinding.inflate(layoutInflater)
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
            //다시 로그인 화면으로 이동
            finish()
        }
    }
}