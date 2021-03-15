package com.example.insta.src.main.profile



import com.example.insta.src.main.profile.models.ProfileResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call

interface ProfileFeedRetrofitInterface {

    @GET("users/{userIdx}/profiles")
    fun getProfile(@Path("userIdx") userIdx:Int): Call<ProfileResponse>
}