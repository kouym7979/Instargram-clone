package com.example.insta.signup.models

import com.example.insta.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class SignUpResponse(

    @SerializedName("result") val result : ArrayList<ResultUser>
) : BaseResponse()
