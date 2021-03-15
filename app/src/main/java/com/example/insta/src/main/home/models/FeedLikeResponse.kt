package com.example.insta.src.main.home.models

import com.google.gson.annotations.SerializedName

data class FeedLikeResponse(
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message : String

)
