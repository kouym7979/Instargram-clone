package com.example.insta.src.main.home.updateFeed

import com.example.insta.config.ApplicationClass
import com.example.insta.src.main.home.HomeFeedRetrofitInterface
import com.example.insta.src.main.home.models.FeedUpdateResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateFeedService(val view : UpdateFeedView) {

    fun tryUpdateFeed(feedIdx : Int,caption:String){
        val HomeFeedRetrofitInterface = ApplicationClass.sRetrofit.create(HomeFeedRetrofitInterface::class.java)
        HomeFeedRetrofitInterface.updateFeed(feedIdx,caption).enqueue(object :
            Callback<FeedUpdateResponse> {
            override fun onResponse(call: Call<FeedUpdateResponse>, response: Response<FeedUpdateResponse>) {
                view.onUpdateFeedSuccess(response.body() as FeedUpdateResponse)
            }

            override fun onFailure(call: Call<FeedUpdateResponse>, t: Throwable) {
                view.onUpdateFeedFailure(t.message ?: "통신 오류")
            }
        })
    }
}