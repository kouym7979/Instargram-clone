package com.example.insta.src.main.home.models

import com.example.insta.config.BaseResponse
import com.google.gson.annotations.SerializedName
import java.util.*

data class HomeFeedResponse(
    @SerializedName("data") val data:MutableList<Feed_data>
    ):BaseResponse()

data class Feed_data(
    @SerializedName("userNickNameIdx") val userNicknameIdx : Int,
    @SerializedName("userProfilePicture") val userProfilePicture : String?=null,
    @SerializedName("userNickName") val userNickName : String,
    @SerializedName("feedIdx") val feedIdx : Int,
    @SerializedName("likeCount") val likeCount : Int,
    @SerializedName("caption") val caption : String,
    @SerializedName("commentCount") val commentCount : Int,
    @SerializedName("mediaIdx") val mediaIdx : Int,
    @SerializedName("mediaURL") val mediaURL: String,
    @SerializedName("firstCommentIdx") val firstCommentIdx : Int,
    @SerializedName("firstUserNickNameIdx") val firstUserNicknameIdx:Int,
    @SerializedName("firstUserNickName") val firstUserNickname : String,
    @SerializedName("firstCommentText") val firstCommentText : String,
    @SerializedName("feedCreateDate") val feedCreateDate : String
)