package com.example.insta.signup.email

import retrofit2.Call
import com.example.insta.signup.email.models.EmailSearch
import retrofit2.http.GET
import retrofit2.http.Path

interface EmailRetrofitInterface {

    @GET("/signup-emails/{userEmail}")
    fun getEmail(@Path("userEmail")  userEmail:String): Call<EmailSearch>

}
