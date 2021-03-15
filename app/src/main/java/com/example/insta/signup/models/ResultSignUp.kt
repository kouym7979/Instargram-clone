package com.example.insta.signup.models

import com.google.gson.annotations.SerializedName

data class ResultSignUp(
    @SerializedName("userEmail") val userEmail : String,
    @SerializedName("jwt") val jwt : String
)
