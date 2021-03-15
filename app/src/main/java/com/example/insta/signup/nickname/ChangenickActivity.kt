package com.example.insta.signup.nickname

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.example.insta.config.BaseActivity
import com.example.insta.databinding.ActivityChangenickBinding
import com.example.insta.signup.nickname.models.ChangeNickCheck
import com.example.insta.signup.range.RangeActivity

class ChangenickActivity:BaseActivity<ActivityChangenickBinding>(ActivityChangenickBinding::inflate),ChangeNickView {
    private var userName : String?=null
    private var userEmail : String?=null
    private var userPassword:String?=null
    private var userBirth: String?=null
    private var userNickName :String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.changeBtn.setOnClickListener {

            userNickName=binding.changeNickEdit.text.toString()
            showLoadingDialog(this)
            ChangeNickService(this).tryGetNickname(userNickName!!)
        }


        binding.changeNickEdit.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               editCheck(s)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

    }

    override fun onStart() {
        super.onStart()

        if(intent.hasExtra("email")&&intent.hasExtra("name")&&intent.hasExtra("pwd")&&intent.hasExtra("birth"))
        {
            userBirth=intent.getStringExtra("birth")
            userName=intent.getStringExtra("name")
            userEmail=intent.getStringExtra("email")
            userPassword=intent.getStringExtra("pwd")
        }
    }
    fun editCheck(s: CharSequence?){
        if (s.toString().length<=5){
            binding.changeBtn.isEnabled=false
        }
        if(s.toString().length>=6)
        {
            binding.changeBtn.isEnabled = true
        }
    }

    override fun onGetNicknameSuccess(response: ChangeNickCheck) {
        response.message.let{
            Log.d("확인",it)
        }
        dismissLoadingDialog()

        if(response.code==1000) {

            val intent = Intent(this, RangeActivity::class.java)
            intent.putExtra("email", userEmail)
            intent.putExtra("name", userName)
            intent.putExtra("pwd", userPassword)
            intent.putExtra("birth", userBirth)
            intent.putExtra("nick", binding.changeNickEdit.text.toString())
            Log.d(
                "확인",
                "이름변경 액티비티->이메일" + userEmail + "이름:" + userName + "비밀번호" + userPassword + "생일" + userBirth + "닉네임" + userNickName
            )
            startActivity(intent)
        }
        else if(response.code!=1000){
            showCustomToast(response.message)

        }
    }

    override fun onGetNicknameFailure(message: String) {
        dismissLoadingDialog()
        Log.d("확인","통신 실패"+message)
    }
}