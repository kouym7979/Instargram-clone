package com.example.insta.login

import com.example.insta.login.models.LoginResponse

interface LoginActivityView {

    fun onPostLoginSuccess(response: LoginResponse)

    fun onPostLoginFailure(message:String)
}