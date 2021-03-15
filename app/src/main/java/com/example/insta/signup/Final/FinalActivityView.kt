package com.example.insta.signup.Final


import com.example.insta.signup.models.SignUpResponse


interface FinalActivityView {


    fun onPostSignUpSuccess(response: SignUpResponse)

    fun onPostSignUpFailure(message: String)



}