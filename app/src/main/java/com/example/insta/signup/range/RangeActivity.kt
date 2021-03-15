package com.example.insta.signup.range

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import com.example.insta.R
import com.example.insta.config.BaseActivity
import com.example.insta.databinding.ActivityRangeBinding
import com.example.insta.signup.photo.AddphotoActivity

class RangeActivity :BaseActivity<ActivityRangeBinding>(ActivityRangeBinding::inflate) {
    private var userName : String?=null
    private var userEmail : String?=null
    private var userPassword:String?=null
    private var userBirth: String?=null
    private var userNickName :String?=null
    private var userDiscloserScope :String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.rangeBtn.setOnClickListener {
            var intent = Intent(this, AddphotoActivity::class.java)
            intent.putExtra("email",userEmail)
            intent.putExtra("name",userName)
            intent.putExtra("pwd",userPassword)
            intent.putExtra("birth",userBirth)
            intent.putExtra("nick",userNickName)
            intent.putExtra("scope",userDiscloserScope)
            Log.d("확인","최종 액티비티->이메일"+userEmail+"이름:"+userName+"비밀번호"+userPassword+"생일"+userBirth+"닉네임"+userNickName+"범위:"+userDiscloserScope)
            startActivity(intent)

        }
        binding.closeCheck.setOnClickListener{onChecked(binding.closeCheck)}
        binding.openCheck.setOnClickListener { onChecked(binding.openCheck) }


    }

    private fun onChecked(compoundButton: CompoundButton){
        when(compoundButton.id){
            R.id.close_check->{
                if(binding.closeCheck.isChecked)
                {
                    binding.openCheck.isChecked=false
                    userDiscloserScope="0"
                    binding.rangeBtn.isEnabled=true
                }
            }
            R.id.open_check->{
                if(binding.openCheck.isChecked ){
                    binding.closeCheck.isChecked=false
                    userDiscloserScope="1"
                    binding.rangeBtn.isEnabled=true
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if(intent.hasExtra("email")&&intent.hasExtra("name")&&intent.hasExtra("pwd")
            &&intent.hasExtra("birth") && intent.hasExtra("nick"))
        {
            userBirth=intent.getStringExtra("birth")
            userName=intent.getStringExtra("name")
            userEmail=intent.getStringExtra("email")
            userPassword=intent.getStringExtra("pwd")
            userNickName=intent.getStringExtra("nick")
        }
    }
}