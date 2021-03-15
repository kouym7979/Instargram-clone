package com.example.insta.signup.email.models

import com.google.gson.annotations.SerializedName

data class EmailSearch (
    @SerializedName("isSuccess") val isSuccess :Boolean,
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message:String,
    @SerializedName("userEmail") val userEmail:String
    )