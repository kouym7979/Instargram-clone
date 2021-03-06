package com.example.insta.login

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import com.example.insta.login.models.LoginRequest
import com.example.insta.login.models.LoginResponse
import com.example.insta.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.example.insta.config.ApplicationClass.Companion.sSharedPreferences
import com.example.insta.config.BaseActivity
import com.example.insta.databinding.ActivityLoginBinding
import com.example.insta.signup.Final.EmailActivity
import com.example.insta.src.main.MainActivity
import com.google.android.material.snackbar.Snackbar


class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate),LoginActivityView{

    private var editor = sSharedPreferences.edit()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.loginBtn.setOnClickListener{
            val postRequest = LoginRequest(
                userEmail = binding.loginId.text.toString(),
                userPassword = binding.loginPwd.text.toString()
            )
            showLoadingDialog(this)
            LoginService(this).tryPostLogin(postRequest)


        }
        binding.loginSignup.setOnClickListener {
            startActivity(Intent(this, EmailActivity::class.java))
        }

        binding.loginPwd.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editCheck(s)
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        val content:String=binding.loginFind.text.toString()
        val spannableString = SpannableString(content)
        spannableString.setSpan( ForegroundColorSpan(Color.parseColor("#212957")), 18, 29, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.loginFind.text=spannableString

        val content2:String=binding.loginSignup.text.toString()
        val spannableString2=SpannableString(content2)
        spannableString2.setSpan( ForegroundColorSpan(Color.parseColor("#212957")), 11, 16, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.loginSignup.text=spannableString2
    }

    override fun onPostLoginSuccess(response: LoginResponse) {
        dismissLoadingDialog()
        response.message?.let{
            Log.d("??????", "????????? success???????????????" + it)
        }
        val state : Int =response.code

        if(state==1000) {

            editor.putString(X_ACCESS_TOKEN, response.jwt)
            editor.putInt("userNickNameIdx", response.userNickNameIdx.toInt())
            editor.putString("userProfilePicture",response.userProfilePicture)
            editor.commit()

            editor.apply()
            Log.d("jwt", response.jwt)
            Log.d("??????", "jwt?????????:" + sSharedPreferences.getString(X_ACCESS_TOKEN, ""))
            Log.d("??????", "user?????????:" + sSharedPreferences.getInt("userNickNameIdx", 0))
            Log.d("??????","?????? uri:"+ sSharedPreferences.getString("userProfilePicture",""))
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        if(state==3021)
            showCustomToast("???????????? ??????????????????.")
        if(state==3026)
            showCustomToast("??????????????? ??????????????????")
        if(state==3024)
            showCustomToast("??????????????? ??????????????????.")
    }

    override fun onPostLoginFailure(message: String) {
        dismissLoadingDialog()
        Log.d("??????", "????????? ???????????????" + message)
    }

    fun editCheck(s: CharSequence?){
        if (s.toString().length<6){
            binding.loginBtn.isEnabled=false
        }
        if(s.toString().length>=6)
        {
            binding.loginBtn.isEnabled = true
        }
    }


}