package com.example.insta.src.main.search.models

import com.example.insta.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("UserList") val userList:MutableList<Search_data>
):BaseResponse()

data class Search_data(
    @SerializedName("userNickNameIdx") val userNickNameIdx:Int,
    @SerializedName("userNickName") val userNickName:String,
    @SerializedName("userIntroduce") val userIntroduce :String,
    @SerializedName("userProfilePicture") val userProfilePicture:String
)
