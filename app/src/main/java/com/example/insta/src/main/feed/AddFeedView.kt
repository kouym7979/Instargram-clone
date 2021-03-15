package com.example.insta.src.main.feed

import com.example.insta.src.main.feed.models.FeedResponse

interface AddFeedView {

    fun onPostFeedSuccess(response: FeedResponse)

    fun onPostFeedFailure(message: String)
}