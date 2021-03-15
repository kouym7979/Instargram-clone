package com.example.insta.src.main.home


import com.example.insta.src.main.home.models.*
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Body
import retrofit2.http.POST

interface HomeFeedRetrofitInterface {

    @GET("/feeds")
    fun getFeed(): Call<HomeFeedResponse>

    @DELETE("/feeds/{feedIdx}")
    fun deleteFeed(@Path("feedIdx") feedIdx : Int) :Call<FeedDeleteResponse>

    @FormUrlEncoded
    @PATCH("/feeds/{feedIdx}")
    fun updateFeed(@Path("feedIdx") feedIdx : Int
                    ,@Field("caption") caption: String) : Call<FeedUpdateResponse>

    @POST("/feeds/{feedIdx}/likes")
    fun likeFeed(@Path("feedIdx") feedIdx: Int,@Body params : FeedLikeRequest) : Call<FeedLikeResponse>

    @GET("/stories-feeds/{userIdx}")
    fun getStory(@Path("userIdx") userIdx:Int) : Call<StoryResponse>

    @GET("/feeds/{feedIdx}/likes")
    fun getLike(@Path("feedIdx") feedIdx:Int) :Call<FeedLikeInqueryResponse>
}