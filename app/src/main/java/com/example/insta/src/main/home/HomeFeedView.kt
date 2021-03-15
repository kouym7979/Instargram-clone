package com.example.insta.src.main.home


import com.example.insta.src.main.home.models.HomeFeedResponse
import com.example.insta.src.main.home.models.StoryResponse


interface HomeFeedView {

    fun onGetFeedSuccess(response : HomeFeedResponse)

    fun onGetFeedFailure(message : String)

    fun onGetStorySuccess(response : StoryResponse)

    fun onGetStoryFailure(message : String)

}