package com.example.insta.src.main.feed


import com.example.insta.src.main.feed.models.FeedResponse
import com.example.insta.src.main.feed.models.PostFeedRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AddFeedRetrofitInterface {
    @POST("/feeds")
    fun postAddFeed(@Body params: PostFeedRequest): Call<FeedResponse>
}