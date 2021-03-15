package com.example.insta.signup.welcome

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.insta.config.BaseActivity
import com.example.insta.databinding.ActivityWelcomeBinding
import com.example.insta.signup.nickname.ChangenickActivity
import com.example.insta.signup.photo.AddphotoActivity

class WelcomeActivity:BaseActivity<ActivityWelcomeBinding>(ActivityWelcomeBinding::inflate),
    View.OnClickListener {

    private var userName : String?=null
    private var userEmail : String?=null
    private var userPassword:String?=null
    private var userBirth: String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(intent.hasExtra("email")&&intent.hasExtra("name")&&intent.hasExtra("pwd")&&intent.hasExtra("birth"))
        {
            userBirth=intent.getStringExtra("birth")
            userName=intent.getStringExtra("name")
            userEmail=intent.getStringExtra("email")
            userPassword=intent.getStringExtra("pwd")
        }
        Log.d("확인","웰컴 액티비티->이메일"+userEmail+"이름:"+userName+"비밀번호"+userPassword+"생일"+userBirth)
        binding.changeNick.setOnClickListener(this)
        binding.welcomeBtn.setOnClickListener(this)
        binding.signedId.text=userName
    }

    override fun onClick(v: View?) {
        when(v){
            binding.changeNick->{
                var intent = Intent(this, ChangenickActivity::class.java)
                intent.putExtra("email",userEmail)
                intent.putExtra("name",userName)
                intent.putExtra("pwd",userPassword)
                intent.putExtra("birth",userBirth)
                startActivity(intent)

            }
            binding.welcomeBtn->{
                var intent = Intent(this, ChangenickActivity::class.java)
                intent.putExtra("email",userEmail)
                intent.putExtra("name",userName)
                intent.putExtra("pwd",userPassword)
                intent.putExtra("birth",userBirth)
                startActivity(intent)
            }
        }
    }



}