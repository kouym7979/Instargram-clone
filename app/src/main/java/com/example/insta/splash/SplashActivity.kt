package com.example.insta.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.insta.login.LoginActivity
import com.example.insta.config.BaseActivity
import com.example.insta.databinding.ActivitySplashBinding

class SplashActivity :BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        },2000)

    }
}