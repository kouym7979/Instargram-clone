package com.example.insta.src.main.home.updateFeed

import com.example.insta.src.main.home.models.FeedUpdateResponse

interface UpdateFeedView {

    fun onUpdateFeedSuccess(response : FeedUpdateResponse)

    fun onUpdateFeedFailure(message : String)
}