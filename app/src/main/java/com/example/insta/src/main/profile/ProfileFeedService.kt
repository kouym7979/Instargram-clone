package com.example.insta.src.main.profile

import com.example.insta.config.ApplicationClass
import com.example.insta.src.main.profile.models.ProfileResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFeedService(val view : ProfileFeedView) {

    fun tryGetProfile(userIdx:Int){
        val ProfileFeedRetrofitInterface = ApplicationClass.sRetrofit.create(ProfileFeedRetrofitInterface::class.java)
        ProfileFeedRetrofitInterface.getProfile(userIdx).enqueue(object : Callback<ProfileResponse> {
            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                view.onGetProfileSuccess(response.body() as ProfileResponse)
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                view.onGetProfileFailure(t.message ?: "통신 오류")
            }
        })
    }
}