package com.example.insta.src.main.profile

import com.example.insta.src.main.profile.models.ProfileResponse

interface ProfileFeedView {

    fun onGetProfileSuccess(response : ProfileResponse)

    fun onGetProfileFailure(message : String)
}