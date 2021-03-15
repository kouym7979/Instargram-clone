package com.example.insta.signup.Final


import com.example.insta.signup.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface FinalRetrofitInterface {
/*
    @GET("/users")
    fun getUsers() : Call<UserResponse>*/

    @POST("/signup")
    fun postSignUp(@Body params:PostSignUpRequest):Call<SignUpResponse>


}