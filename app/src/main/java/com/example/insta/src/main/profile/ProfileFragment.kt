package com.example.insta.src.main.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.insta.R
import com.example.insta.config.ApplicationClass
import com.example.insta.config.BaseFragment
import com.example.insta.databinding.FragmentProfileBinding
import com.example.insta.src.main.profile.adapter.profileAdapter
import com.example.insta.src.main.profile.models.ProfileResponse
import com.example.insta.src.main.profile.models.user_data

class ProfileFragment:BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::bind, R.layout.fragment_profile),ProfileFeedView {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var user_data:MutableList<user_data>

        var adapter = profileAdapter(fragmentManager!!)

        /*ProfileFeedService(this).tryGetProfile(
            ApplicationClass.sSharedPreferences.getInt(
                "userNickNameIdx",
                0))*/
        binding.feedCount.text="6"
        binding.followingCount.text="316"
        binding.followerCount.text="248"
        binding.profileUserNick.text="Liam"
        var profileUrl= ApplicationClass.sSharedPreferences.getString("userProfilePicture", "")
        if(profileUrl!!.contains("alt=")){
            profileUrl=profileUrl.split("alt=").first()
            profileUrl=profileUrl.substring(0,profileUrl.length-1)
            profileUrl+="?alt=media"
            Log.d("Url",profileUrl.toString())
        }

        binding.userImg.scaleType = ImageView.ScaleType.CENTER_CROP

        Glide.with(this).load(profileUrl).apply(
            RequestOptions().circleCrop()
        ).into(binding.userImg)


        binding.profileView.adapter= ProfileFragment@adapter

        binding.profileView.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                when(position){
                    0->{
                        binding.tabLayout.getTabAt(0)?.setIcon(R.drawable.profile_btn1_yes)
                        binding.tabLayout.getTabAt(1)?.setIcon(R.drawable.profile_btn2_no)
                    }
                    1->{
                        binding.tabLayout.getTabAt(0)?.setIcon(R.drawable.profile_btn1_no)
                        binding.tabLayout.getTabAt(1)?.setIcon(R.drawable.profile_btn2_yes)
                    }

                }


            }

            override fun onPageScrollStateChanged(state: Int) {
                /*when(state){
                    0->{
                        binding.tabLayout.getTabAt(0)?.setIcon(R.drawable.profile_btn1_yes)
                        binding.tabLayout.getTabAt(1)?.setIcon(R.drawable.profile_btn2_no)
                    }
                    1->{
                        binding.tabLayout.getTabAt(0)?.setIcon(R.drawable.profile_btn1_no)
                        binding.tabLayout.getTabAt(1)?.setIcon(R.drawable.profile_btn2_yes)
                    }

                }*/
            }
        })

        binding.tabLayout.setupWithViewPager(binding.profileView)


    }

    override fun onGetProfileSuccess(response: ProfileResponse) {
        response.message.let {

            Log.d("확인",it.toString())
            dismissLoadingDialog()
        }
        //binding.followerCount.text=response.followerCount.toString()
        //binding.followingCount.text=response.followingCount.toString()
        //binding.feedCount.text=response.feedCount.toString()

        //binding.profileUserNick.text=response.user.
    }

    override fun onGetProfileFailure(message: String) {
        Log.d("확인",message)
    }
}