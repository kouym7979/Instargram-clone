package com.example.insta.src.main.comment.models

import com.google.gson.annotations.SerializedName

data class CommentRequest(
    @SerializedName("commentText") val commentText:String,
    @SerializedName("userNickNameIdx") val userNickNameIdx : Int
)
