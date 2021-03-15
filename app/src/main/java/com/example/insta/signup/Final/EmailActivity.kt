package com.example.insta.signup.Final


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.viewpager.widget.ViewPager
import com.example.insta.login.LoginActivity
import com.example.insta.config.BaseActivity
import com.example.insta.databinding.ActivityEmailBinding
import com.example.insta.signup.adapter.PageAdapter


class EmailActivity :BaseActivity<ActivityEmailBinding>(ActivityEmailBinding::inflate) {

    private val adapter by lazy {PageAdapter(supportFragmentManager)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.signupView.adapter=EmailActivity@adapter

        binding.signupView.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {

                binding.tabLayout.getTabAt(0)?.text="전화번호"
                binding.tabLayout.getTabAt(1)?.text="이메일"
            }

            override fun onPageScrollStateChanged(state: Int) {
                binding.tabLayout.getTabAt(0)?.text="전화번호"
                binding.tabLayout.getTabAt(1)?.text="이메일"
            }
        })

        binding.tabLayout.setupWithViewPager(binding.signupView)

        val content:String=binding.signupLogin.text.toString()
        val spannableString = SpannableString(content)
        spannableString.setSpan( ForegroundColorSpan(Color.parseColor("#212957")), 14, 19, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.signupLogin.text=spannableString

        binding.signupLogin.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

}