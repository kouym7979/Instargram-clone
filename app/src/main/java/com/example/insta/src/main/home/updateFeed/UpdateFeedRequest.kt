package com.example.insta.src.main.home.updateFeed

import com.google.gson.annotations.SerializedName

data class UpdateFeedRequest(
    @SerializedName("feedIdx") val feedIdx:Int,
    @SerializedName("caption") val caption :String
)
