package com.hkbae.FinancialProduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        register_btn.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        login_btn.setOnClickListener {
            //일단 메인화면으로 이동
            val intent = Intent(this@LoginActivity,MainActivity::class.java)
            startActivity(intent)
        }
    }
}