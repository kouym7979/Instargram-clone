package com.example.insta.src.main.home.models

import com.google.gson.annotations.SerializedName

data class FeedLikeRequest(
    @SerializedName("userNickNameIdx") val userNickNameIdx:Int
)
