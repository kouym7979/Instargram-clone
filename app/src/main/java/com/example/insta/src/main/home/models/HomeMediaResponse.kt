package com.example.insta.src.main.home.models

import com.example.insta.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class HomeMediaResponse (
    @SerializedName("mediaurl") val mediaurl : String,

):BaseResponse()