package com.example.insta.signup.name

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.example.insta.R
import com.example.insta.config.BaseActivity
import com.example.insta.databinding.ActivityNameBinding
import com.example.insta.signup.birthday.BirthdayActivity

class NameActivity : BaseActivity<ActivityNameBinding>(ActivityNameBinding::inflate),
    View.OnClickListener {


    private var userName : String?=null
    private var userEmail : String?=null
    private var userPassword:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.signupPwd.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editCheck(s)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.signupName.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editCheck(s)
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })


        binding.signupContinueBtn.setOnClickListener(this)
        binding.signupNotsync.setOnClickListener(this)


    }

    override fun onStart() {
        super.onStart()
        if(intent.hasExtra("email")){
            userEmail=intent.getStringExtra("email")
        }
    }
    override fun onClick(v: View?) {

        when(v) {
            binding.signupContinueBtn ->{
                userName=binding.signupName.text.toString()
                userPassword=binding.signupPwd.text.toString()
                Log.d("확인","이름액티비티:"+"이름:"+binding.signupName.text.toString()+"비밀번호:"+binding.signupPwd.text.toString())
                val intent =Intent(this, BirthdayActivity::class.java)
                intent.putExtra("email",userEmail)
                intent.putExtra("name",binding.signupName.text.toString())
                intent.putExtra("pwd",binding.signupPwd.text.toString())
                startActivity(intent)
            }
            binding.signupNotsync->{
                val intent =Intent(this, BirthdayActivity::class.java)
                intent.putExtra("email",userEmail)
                intent.putExtra("name",userName)
                intent.putExtra("pwd",userPassword)
                startActivity(intent)
            }
        }
    }

    fun editCheck(s: CharSequence?){
        if (s.toString().length<6){
            binding.signupContinueBtn.isEnabled=false
            binding.signupNotsync.isEnabled=false

            binding.signupNotsync.setTextColor(ContextCompat.getColor(applicationContext,R.color.next))
        }
        if(s.toString().length>=6)
        {
            binding.signupNotsync.isEnabled = true

            binding.signupContinueBtn.isEnabled = true
            binding.signupNotsync.setTextColor(ContextCompat.getColor(applicationContext,R.color.next))
        }
    }


}