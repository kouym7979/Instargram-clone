package com.example.insta.login

import com.example.insta.login.models.LoginRequest
import com.example.insta.login.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRetrofitInterface {
    @POST("/login")
    fun postLogin(@Body params: LoginRequest): Call<LoginResponse>
}