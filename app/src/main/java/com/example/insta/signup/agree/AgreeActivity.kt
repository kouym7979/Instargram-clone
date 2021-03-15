package com.example.insta.signup.agree

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import com.example.insta.R
import com.example.insta.config.BaseActivity
import com.example.insta.databinding.ActivityAgreeBinding
import com.example.insta.signup.welcome.WelcomeActivity

class AgreeActivity : BaseActivity<ActivityAgreeBinding>(ActivityAgreeBinding::inflate) {

    private var userName : String?=null
    private var userEmail : String?=null
    private var userPassword:String?=null
    private var userBirth: String?=null
    private var check1 :Boolean=false
    private var check2 :Boolean=false
    private var check3 :Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.allCheck.setOnClickListener { onCheckedChanged(binding.allCheck) }


        binding.agreeNext.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            intent.putExtra("email",userEmail)
            intent.putExtra("name",userName)
            intent.putExtra("pwd",userPassword)
            intent.putExtra("birth",userBirth)
            startActivity(intent)
        }

        if(check1==true && check2 ==true && check3==true) {
            binding.allCheck.isChecked = true
            binding.agreeNext.isEnabled=true
        }
        else{
            binding.allCheck.isChecked = false
            binding.agreeNext.isEnabled=false
        }
    }

    private fun onCheckedChanged(compoundButton: CompoundButton){
        when(compoundButton.id){
            R.id.all_check->{
                if(binding.allCheck.isChecked){
                    binding.dataCheck.isChecked=true
                    binding.serviceCheck.isChecked=true
                    binding.locationCheck.isChecked=true
                    binding.agreeNext.isEnabled=true
                }
                else{
                    binding.dataCheck.isChecked=false
                    binding.serviceCheck.isChecked=false
                    binding.locationCheck.isChecked=false
                    binding.agreeNext.isEnabled=false
                }
            }
            R.id.location_check->{
                if(binding.locationCheck.isChecked) check1=true
                else check1=false
            }
            R.id.data_check->{
                if(binding.dataCheck.isChecked) check2=true
                else check2=false
            }
            R.id.service_check->{
                if(binding.serviceCheck.isChecked) check3=true
                else check3=false
            }

        }
    }

    override fun onStart() {
        super.onStart()
        if(intent.hasExtra("email")&&intent.hasExtra("name")&&intent.hasExtra("pwd")&&intent.hasExtra("birth"))
        {
            userBirth=intent.getStringExtra("birth")
            userName=intent.getStringExtra("name")
            userEmail=intent.getStringExtra("email")
            userPassword=intent.getStringExtra("pwd")
            Log.d("확인","동의 액티비티->이메일"+userEmail+"이름:"+userName+"비밀번호"+userPassword+"생일"+userBirth)
        }
    }
}