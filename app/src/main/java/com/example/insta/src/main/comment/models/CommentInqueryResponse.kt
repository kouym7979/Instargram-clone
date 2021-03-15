package com.example.insta.src.main.comment.models

import com.example.insta.config.BaseResponse
import com.google.gson.annotations.SerializedName


data class CommentInqueryResponse (
    @SerializedName("data") val data : MutableList<Comment>
        ):BaseResponse()
data class Comment(
    @SerializedName("userNickNameIdx") val userNickNameIdx:Int,
    @SerializedName("userNickName") val userNickName:String,
    @SerializedName("userProfilePicture") val userProfilePicture:String,
    @SerializedName("commentIdx") val commentIdx:Int,
    @SerializedName("commentText") val commmentText:String,
    @SerializedName("commentlikecount") val commentlikecount:Int,
    @SerializedName("commentCreateDate") val commentCreateDate : String,
)