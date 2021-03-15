package com.example.insta.src.main.feed.models

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class PostFeedRequest(
    @SerializedName("userNickNameIdx") val userNickNameIdx : Int,
    @SerializedName("mediaList") val mediaList : ArrayList<MediaUrl>,
    @SerializedName("mediaIdx") val mediaIdx: Int,
    @SerializedName("caption") val caption : String,
    @SerializedName("feedCreateDate") val feedCreateDate : Date,
    @SerializedName("feedUpdateDate") val feedUpdateDate : Date
    )
