package com.example.insta.src.main.comment.models

import com.example.insta.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class CommentResponse (
    @SerializedName("commentIdx") val commentIdx:Int
    ):BaseResponse()