package com.example.insta.login.models

import com.example.insta.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("jwt") val jwt :String,
    @SerializedName("userNickNameIdx") val userNickNameIdx:String,
    @SerializedName("userProfilePicture") val userProfilePicture:String
):BaseResponse()
