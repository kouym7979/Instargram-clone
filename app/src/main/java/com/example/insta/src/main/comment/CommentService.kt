package com.example.insta.src.main.comment

import com.example.insta.config.ApplicationClass
import com.example.insta.src.main.comment.models.CommentDeleteResponse
import com.example.insta.src.main.comment.models.CommentInqueryResponse
import com.example.insta.src.main.comment.models.CommentRequest
import com.example.insta.src.main.comment.models.CommentResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentService(val view : CommentView) {

    fun tryPostComment(feedIdx:Int,CommentRequest: CommentRequest){
        val CommentRetrofitInterface = ApplicationClass.sRetrofit.create(CommentRetrofitInterface::class.java)
        CommentRetrofitInterface.postComment(feedIdx,CommentRequest).enqueue(object:
            Callback<CommentResponse> {
            override fun onResponse(call: Call<CommentResponse>, response: Response<CommentResponse>
            ) {
                view.onPostCommentSuccess(response.body() as CommentResponse)
            }

            override fun onFailure(call: Call<CommentResponse>, t: Throwable) {
                view.onPostCommentFailure(t.message?:"통신 오류")
            }

        })
    }

    fun tryGetComment(feedIdx:Int){
        val CommentRetrofitInterface = ApplicationClass.sRetrofit.create(CommentRetrofitInterface::class.java)
        CommentRetrofitInterface.getComment(feedIdx).enqueue(object:
            Callback<CommentInqueryResponse> {
            override fun onResponse(call: Call<CommentInqueryResponse>, response: Response<CommentInqueryResponse>
            ) {
                view.onGetCommentSuccess(response.body() as CommentInqueryResponse)
            }

            override fun onFailure(call: Call<CommentInqueryResponse>, t: Throwable) {
                view.onGetCommentFailure(t.message?:"통신 오류")
            }

        })
    }
    fun tryDeleteComment(commentIdx:Int){
        val CommentRetrofitInterface = ApplicationClass.sRetrofit.create(CommentRetrofitInterface::class.java)
        CommentRetrofitInterface.deleteComment(commentIdx).enqueue(object:
            Callback<CommentDeleteResponse> {
            override fun onResponse(call: Call<CommentDeleteResponse>, response: Response<CommentDeleteResponse>
            ) {

                view.onDeleteCommentSuccess(response.body() as CommentDeleteResponse)
            }

            override fun onFailure(call: Call<CommentDeleteResponse>, t: Throwable) {
                view.onDeleteCommentFailure(t.message?:"통신 오류")
            }

        })
    }


}