package com.example.insta.src.main

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.insta.R
import com.example.insta.config.ApplicationClass
import com.example.insta.config.BaseActivity
import com.example.insta.databinding.ActivityMainBinding
import com.example.insta.src.main.home.HomeFragment
import com.example.insta.src.main.movie.MovieFragment
import com.example.insta.src.main.profile.ProfileFragment
import com.example.insta.src.main.search.SearchFragment
import com.example.insta.src.main.shop.ShopFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        var profileUrl = ApplicationClass.sSharedPreferences.getString("userProfilePicture", "")
        if (profileUrl!!.contains("alt=")) {
            profileUrl = profileUrl.split("alt=").first()
            profileUrl = profileUrl.substring(0, profileUrl.length - 1)
            profileUrl += "?alt=media"
            Log.d("Url", profileUrl.toString())
        }
        /*binding.bottomUserImg.scaleType = ImageView.ScaleType.CENTER_CROP

        Glide.with(this).load(profileUrl)
            .apply(RequestOptions().circleCrop())
            .into(binding.bottomUserImg)*/

        supportFragmentManager.beginTransaction().add(R.id.main_frame, HomeFragment())
            .commitAllowingStateLoss()
        binding.bottomNav.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_main_btm_nav_home -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frame, HomeFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_btm_nav_search -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frame, SearchFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_btm_nav_movie -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frame, MovieFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_btm_nav_shop -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frame, ShopFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_btm_nav_profile -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frame, ProfileFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            }
        )
    }


}

