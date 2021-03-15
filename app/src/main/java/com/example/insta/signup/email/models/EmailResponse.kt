package com.example.insta.signup.email.models

import com.google.gson.annotations.SerializedName

data class EmailResponse(
    @SerializedName("isSuccess") val isSuccess :Boolean,
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message:String,
    @SerializedName("result") val result : ArrayList<EmailSearch>
)
