package com.example.insta.src.main.comment

import com.example.insta.src.main.comment.models.CommentDeleteResponse
import com.example.insta.src.main.comment.models.CommentInqueryResponse
import com.example.insta.src.main.comment.models.CommentResponse


interface CommentView {
    fun onPostCommentSuccess(response : CommentResponse)

    fun onPostCommentFailure(message : String)

    fun onGetCommentSuccess(response : CommentInqueryResponse)

    fun onGetCommentFailure(message : String)

    fun onDeleteCommentSuccess(response : CommentDeleteResponse)

    fun onDeleteCommentFailure(message : String)
}