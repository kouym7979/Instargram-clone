package com.example.insta.src.main.home.media


import com.example.insta.src.main.home.models.HomeMediaResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MediaRetrofitInterface {

    @GET("/feeds/{feedIdx}/mediaurl")
    fun getMedia(@Path("feedIdx") feedIdx:Int): Call<HomeMediaResponse>
}