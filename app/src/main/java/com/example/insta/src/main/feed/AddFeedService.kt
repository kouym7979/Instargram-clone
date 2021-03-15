package com.example.insta.src.main.feed

import com.example.insta.config.ApplicationClass
import com.example.insta.src.main.feed.caption.AddcaptionActivity
import com.example.insta.src.main.feed.models.FeedResponse
import com.example.insta.src.main.feed.models.PostFeedRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddFeedService (val view : AddcaptionActivity){

    fun tryPostFeed(postFeedRequest: PostFeedRequest){
        val AddFeedRetrofitInterface = ApplicationClass.sRetrofit.create(AddFeedRetrofitInterface::class.java)
        AddFeedRetrofitInterface.postAddFeed(postFeedRequest).enqueue(object:
            Callback<FeedResponse> {
            override fun onResponse(call: Call<FeedResponse>, response: Response<FeedResponse>
            ) {
                view.onPostFeedSuccess(response.body() as FeedResponse)
            }

            override fun onFailure(call: Call<FeedResponse>, t: Throwable) {
                view.onPostFeedFailure(t.message?:"통신 오류")
            }

        })
    }
}