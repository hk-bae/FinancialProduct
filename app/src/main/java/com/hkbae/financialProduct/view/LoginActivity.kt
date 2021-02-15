package com.hkbae.financialProduct.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hkbae.financialProduct.MainActivity
import com.hkbae.financialProduct.databinding.ActivityLoginBinding
import com.hkbae.financialProduct.model.User
import com.hkbae.financialProduct.viewModel.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var model: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        model= ViewModelProvider(this).get(UserViewModel::class.java)
        val view=binding.root
        setContentView(view)

        initLoginActivity()

        if(model.liveData.value==null) {
            fetchUserData()
        }

    }

    private fun fetchUserData(){
        //sharedPreference에 저장된 로그인 기록이 있으면 자동 로그인
        val sharedPreference = getSharedPreferences("user", Context.MODE_PRIVATE)
        val id = sharedPreference.getString("id", null)
        var password = sharedPreference.getString("password", null)
        if (id != null && password != null) {
            model.login(id, password)
        }
    }

    private fun initLoginActivity(){
        val observer = Observer<User>{ user->
            if(user.id.isNotEmpty()){
                //로그인 성공 시 main 화면으로 이동

                    Log.d("login",user.id)
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)

                //유저 아이디,비밀번호를 sharedPreference에 저장
                val sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
                val editor=sharedPreferences.edit()

                editor.apply{
                    putString("id",user.id)
                    putString("password",user.password)
                    putString("name",user.name)
                    putString("born",user.born)
                    commit()
                }

                finish()

            }else{
                Toast.makeText(this@LoginActivity,"입력 정보를 확인해 주세요.", Toast.LENGTH_SHORT).show()
            }
        }
        model.liveData.observe(this,observer)


    }

    fun onClickLoginBtn(view: View) {

        //id와 pw받기
        val id=binding.userId.text.toString().trim()
        val password=binding.userPassword.text.toString().trim()

        if(id.isEmpty() || password.isEmpty()){
            Toast.makeText(this@LoginActivity,"입력 정보를 확인해 주세요.", Toast.LENGTH_SHORT).show()
        }else{
            model.login(id,password)
        }

    }

    //회원가입 화면으로 전환
    fun onClickRegisterBtn(view: View) {
        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
        startActivityForResult(intent,100)
    }

    //회원가입 성공 시 아이디 비밀번호에 자동 입력해주기
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==100 && resultCode== RESULT_OK){

        }
    }

}