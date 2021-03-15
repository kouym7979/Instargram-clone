package com.example.insta.signup.nickname.models

import com.google.gson.annotations.SerializedName

data class ChangeNickCheck(
    @SerializedName("isSuccess") val isSuccess :Boolean,
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message:String,
    @SerializedName("userNicknames") val userNicknames:String
)
