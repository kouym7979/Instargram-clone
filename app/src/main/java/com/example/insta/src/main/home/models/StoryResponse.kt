package com.example.insta.src.main.home.models

import com.example.insta.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class StoryResponse(
   @SerializedName("data") val data:MutableList<story_data>
):BaseResponse()

data class story_data(
    @SerializedName("userProfilePicture") val userProfilePicture:String,
    @SerializedName("userNickNameIdx") val userNickNameIdx:Int,
    @SerializedName("userNickName") val userNickName:String
)
