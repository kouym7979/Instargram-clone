package com.example.insta.src.main.search

import com.example.insta.config.ApplicationClass
import com.example.insta.src.main.search.models.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchService(val view : SearchFragment) {

    fun tryGetSearch(touseridx:Int){
        val SearchRetrofitInterface = ApplicationClass.sRetrofit.create(SearchRetrofitInterface::class.java)
        SearchRetrofitInterface.getSearch(touseridx).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                view.onGetSearchSuccess(response.body() as SearchResponse)
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                view.onGetSearchFailure(t.message ?: "통신 오류")
            }
        })
    }
}