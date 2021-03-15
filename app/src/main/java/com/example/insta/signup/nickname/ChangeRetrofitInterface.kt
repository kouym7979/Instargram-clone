package com.example.insta.signup.nickname

import com.example.insta.signup.nickname.models.ChangeNickCheck
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ChangeRetrofitInterface {

    @GET("/signup-nicknames/{userNickName}")
    fun getNickname(@Path("userNickName")  userNicknames:String): Call<ChangeNickCheck>
}