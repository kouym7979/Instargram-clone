package com.example.insta.src.main.feed.models

import com.google.gson.annotations.SerializedName

data class FeedResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code :Int,
    @SerializedName("message") val message: String
)
