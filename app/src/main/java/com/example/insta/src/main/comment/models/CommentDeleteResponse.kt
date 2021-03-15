package com.example.insta.src.main.comment.models

import com.google.gson.annotations.SerializedName

data class CommentDeleteResponse(
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message : String

)
