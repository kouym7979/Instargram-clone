package com.example.insta.login.models

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("userEmail") val userEmail :String,
    @SerializedName("userPassword") val userPassword:String
)
