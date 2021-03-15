package com.example.insta.src.main.search

import com.example.insta.src.main.search.models.SearchResponse
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.*
interface SearchRetrofitInterface {

    @GET("/search/{touserIdx}")
    fun getSearch(@Path("touserIdx") touserIdx:Int): Call<SearchResponse>
}