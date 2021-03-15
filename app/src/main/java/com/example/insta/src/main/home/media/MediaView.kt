package com.example.insta.src.main.home.media

import com.example.insta.src.main.home.models.HomeMediaResponse

interface MediaView {

    fun onGetMediaSuccess(response : HomeMediaResponse)

    fun onGetMediaFailure(message : String)
}