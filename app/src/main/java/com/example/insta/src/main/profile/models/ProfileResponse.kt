package com.example.insta.src.main.profile.models

import com.example.insta.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("user") val user:MutableList<user_data>,
    @SerializedName("feedCount") val feedCount: Int,
    @SerializedName("followingCount") val followingCount: Int,
    @SerializedName("followerCount") val followerCount:Int,
    @SerializedName("feed")val feed:MutableList<feed_data>
): BaseResponse()

data class feed_data(

    @SerializedName("feedIdx") val feedIdx : Int,
    @SerializedName("mediaURL") val mediaURL:String,
    @SerializedName("mediaCount") val mediaCount:Int,
)

data class user_data(
    @SerializedName("userNickNameIdx") val userNickNameIdx:Int,
    @SerializedName("userNickName") val userNickName:String,
    @SerializedName("userProfilePicture") val userProfilePicture : String,
    @SerializedName("userSite") val userSite : String,
    @SerializedName("userIntroduce") val userIntroduce:String,
)
data class followerCount(
    @SerializedName("followerCount")   val followCount:Int
)
data class feedCount(
    @SerializedName("feedCount") val feedCount:Int
)
data class followingCount(
    @SerializedName("followingCount") val followingCount : Int,
)

