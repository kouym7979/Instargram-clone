package com.example.insta.src.main.comment

import com.example.insta.src.main.comment.models.CommentDeleteResponse
import com.example.insta.src.main.comment.models.CommentInqueryResponse
import com.example.insta.src.main.comment.models.CommentRequest
import com.example.insta.src.main.comment.models.CommentResponse
import retrofit2.Call
import retrofit2.http.*

interface CommentRetrofitInterface {
    @POST("/feeds/{feedIdx}/comments")
    fun postComment(@Path("feedIdx")feedIdx:Int, @Body params: CommentRequest) : Call<CommentResponse>

    @GET("/feeds/{feedIdx}/comments")
    fun getComment(@Path("feedIdx")feedIdx:Int) : Call<CommentInqueryResponse>


    @DELETE("/feeds/comments/{commentIdx}")
    fun deleteComment(@Path("commentIdx")commentIdx:Int):Call<CommentDeleteResponse>
}