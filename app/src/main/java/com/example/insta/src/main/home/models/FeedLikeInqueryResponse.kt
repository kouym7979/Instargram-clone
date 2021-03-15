package com.example.insta.src.main.home.models

import com.example.insta.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class FeedLikeInqueryResponse(
    @SerializedName("data") val data:MutableList<like_data>
)

data class like_data(
    @SerializedName("userNickNameIdx") val userNickNameIdx:Int
):BaseResponse()
