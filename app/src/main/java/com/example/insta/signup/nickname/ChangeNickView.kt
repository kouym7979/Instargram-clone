package com.example.insta.signup.nickname


import com.example.insta.signup.nickname.models.ChangeNickCheck

interface ChangeNickView {

    fun onGetNicknameSuccess(response : ChangeNickCheck)

    fun onGetNicknameFailure(message : String)
}