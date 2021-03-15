package com.example.insta.src.main.search


import com.example.insta.src.main.search.models.SearchResponse

interface SearchView {

    fun onGetSearchSuccess(response : SearchResponse)

    fun onGetSearchFailure(message : String)

}