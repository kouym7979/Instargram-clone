package com.example.insta.src.main.home.feedRcycler

import com.example.insta.config.ApplicationClass
import com.example.insta.src.main.feed.models.PostFeedRequest
import com.example.insta.src.main.home.HomeFeedRetrofitInterface
import com.example.insta.src.main.home.models.FeedDeleteResponse
import com.example.insta.src.main.home.models.FeedLikeInqueryResponse
import com.example.insta.src.main.home.models.FeedLikeRequest
import com.example.insta.src.main.home.models.FeedLikeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedService(val view : FeedView) {

    fun tryDeleteFeed(feedIdx : Int){
        val HomeFeedRetrofitInterface = ApplicationClass.sRetrofit.create(HomeFeedRetrofitInterface::class.java)
        HomeFeedRetrofitInterface.deleteFeed(feedIdx).enqueue(object :
            Callback<FeedDeleteResponse> {
            override fun onResponse(call: Call<FeedDeleteResponse>, response: Response<FeedDeleteResponse>) {
                view.onDeleteFeedSuccess(response.body() as FeedDeleteResponse)
            }

            override fun onFailure(call: Call<FeedDeleteResponse>, t: Throwable) {
                view.onDeleteFeedFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryPostLike(feedIdx:Int,feedLikeRequest: FeedLikeRequest){
        val HomeFeedRetrofitInterface = ApplicationClass.sRetrofit.create(HomeFeedRetrofitInterface::class.java)
        HomeFeedRetrofitInterface.likeFeed(feedIdx,feedLikeRequest).enqueue(object : Callback<FeedLikeResponse> {
            override fun onResponse(call: Call<FeedLikeResponse>, response: Response<FeedLikeResponse>) {
                view.onPostLikeSuccess(response.body() as FeedLikeResponse)
            }

            override fun onFailure(call: Call<FeedLikeResponse>, t: Throwable) {
                view.onPostLikeFailure(t.message ?: "통신 오류")
            }
        })
    }
    fun tryGetLike(feedIdx:Int){
        val HomeFeedRetrofitInterface = ApplicationClass.sRetrofit.create(HomeFeedRetrofitInterface::class.java)
        HomeFeedRetrofitInterface.getLike(feedIdx).enqueue(object : Callback<FeedLikeInqueryResponse> {
            override fun onResponse(call: Call<FeedLikeInqueryResponse>, response: Response<FeedLikeInqueryResponse>) {
                view.onGetLikeSuccess(response.body() as FeedLikeInqueryResponse)
            }

            override fun onFailure(call: Call<FeedLikeInqueryResponse>, t: Throwable) {
                view.onGetLikeFailure(t.message ?: "통신 오류")
            }
        })
    }
}