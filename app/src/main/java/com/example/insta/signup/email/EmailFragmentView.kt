package com.example.insta.signup.email


import com.example.insta.signup.email.models.EmailSearch

interface EmailFragmentView {

    fun onGetEmailSuccess(response : EmailSearch)

    fun onGetEmailFailure(message : String)
}