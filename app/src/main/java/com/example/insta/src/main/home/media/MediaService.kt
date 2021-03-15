package com.example.insta.src.main.home.media

import com.example.insta.config.ApplicationClass
import com.example.insta.src.main.home.models.HomeMediaResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MediaService(val view : MediaView) {
    fun tryGetMedia(feedIdx : Int){
        val MediaRetrofitInterface = ApplicationClass.sRetrofit.create(MediaRetrofitInterface::class.java)
        MediaRetrofitInterface.getMedia(feedIdx).enqueue(object : Callback<HomeMediaResponse> {
            override fun onResponse(call: Call<HomeMediaResponse>, response: Response<HomeMediaResponse>) {
                view.onGetMediaSuccess(response.body() as HomeMediaResponse)
            }

            override fun onFailure(call: Call<HomeMediaResponse>, t: Throwable) {
                view.onGetMediaFailure(t.message ?: "통신 오류")
            }
        })
    }
}