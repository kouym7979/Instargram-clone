package com.example.insta.signup.birthday

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.insta.config.BaseActivity
import com.example.insta.databinding.ActivityBirthdayBinding
import com.example.insta.signup.agree.AgreeActivity
import java.time.LocalDate


class BirthdayActivity : BaseActivity<ActivityBirthdayBinding>(ActivityBirthdayBinding::inflate) {

    private var select_day: String? = null
    private var day:String?=null
    private var current_year: Int? = null
    private var userName : String?=null
    private var userEmail : String?=null
    private var userPassword:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val onlydate: LocalDate = LocalDate.now()

        current_year = onlydate.year
        binding.birthSpinner.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->

            select_day = String.format("%d년 %d월 %d일", year, monthOfYear + 1, dayOfMonth)
            Log.d("확인", "선택한 날짜는" + select_day)
            binding.birthdayText.text = select_day
            val select_year=select_day!!.substring(0,4)
            Log.d("확인","현재 년도:"+current_year+"선택한 년도"+select_year)
            binding.birthYear.text = (current_year!! - select_year.toInt()+1).toString()+"세"
            day=String.format("%d-%d-%d", year, monthOfYear + 1, dayOfMonth)

        }

        if(intent.hasExtra("email") && intent.hasExtra("name")&& intent.hasExtra("pwd"))
        {
            userEmail=intent.getStringExtra("email")
            userName=intent.getStringExtra("name")
            userPassword=intent.getStringExtra("pwd")
            Log.d("확인","생일 액티비티->이메일:"+userEmail+"이름:"+userName+"비밀번호"+userPassword)
        }
        binding.birthBtn.setOnClickListener {
            var intent = Intent(this, AgreeActivity::class.java)
            intent.putExtra("email",userEmail)
            intent.putExtra("name",userName)
            intent.putExtra("pwd",userPassword)
            intent.putExtra("birth",day)
            startActivity(intent)
        }
    }

}