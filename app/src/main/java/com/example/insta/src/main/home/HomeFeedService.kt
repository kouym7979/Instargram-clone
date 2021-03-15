package com.example.insta.src.main.home

import com.example.insta.config.ApplicationClass
import com.example.insta.src.main.home.models.FeedDeleteResponse
import com.example.insta.src.main.home.models.FeedLikeResponse
import com.example.insta.src.main.home.models.HomeFeedResponse
import com.example.insta.src.main.home.models.StoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFeedService(val view : HomeFeedView) {

    fun tryGetFeed(){
        val HomeFeedRetrofitInterface = ApplicationClass.sRetrofit.create(HomeFeedRetrofitInterface::class.java)
        HomeFeedRetrofitInterface.getFeed().enqueue(object : Callback<HomeFeedResponse> {
            override fun onResponse(call: Call<HomeFeedResponse>, response: Response<HomeFeedResponse>) {
                view.onGetFeedSuccess(response.body() as HomeFeedResponse)
            }

            override fun onFailure(call: Call<HomeFeedResponse>, t: Throwable) {
                view.onGetFeedFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryGetStory(userIdx:Int){
        val HomeFeedRetrofitInterface = ApplicationClass.sRetrofit.create(HomeFeedRetrofitInterface::class.java)
        HomeFeedRetrofitInterface.getStory(userIdx).enqueue(object : Callback<StoryResponse> {
            override fun onResponse(call: Call<StoryResponse>, response: Response<StoryResponse>) {
                view.onGetStorySuccess(response.body() as StoryResponse)
            }

            override fun onFailure(call: Call<StoryResponse>, t: Throwable) {
                view.onGetStoryFailure(t.message ?: "통신 오류")
            }
        })
    }


}