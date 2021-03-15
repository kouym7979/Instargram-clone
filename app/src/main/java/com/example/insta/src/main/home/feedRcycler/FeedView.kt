package com.example.insta.src.main.home.feedRcycler

import com.example.insta.src.main.home.models.FeedDeleteResponse
import com.example.insta.src.main.home.models.FeedLikeInqueryResponse
import com.example.insta.src.main.home.models.FeedLikeResponse


interface FeedView {

    fun onDeleteFeedSuccess(response : FeedDeleteResponse)

    fun onDeleteFeedFailure(message : String)

    fun onPostLikeSuccess(response : FeedLikeResponse)

    fun onPostLikeFailure(message : String)

    fun onGetLikeSuccess(response : FeedLikeInqueryResponse)

    fun onGetLikeFailure(message : String)

}