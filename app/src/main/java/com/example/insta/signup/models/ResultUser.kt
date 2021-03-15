package com.example.insta.signup.models

import com.google.gson.annotations.SerializedName

data class ResultUser(
    @SerializedName("userName") val userName : String,
    @SerializedName("userEmail") val userEmail:String

)
