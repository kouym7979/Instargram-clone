package com.example.insta.signup.models

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName
import java.util.*

data class PostSignUpRequest(
    @SerializedName("userEmail") val userEmail : String,
    @SerializedName("userName") val userName : String,
    @SerializedName("userPassword") val userPassword : String,
    @SerializedName("userBirth") val userBirth : Date,
    @SerializedName("userNickName") val userNickName: String,
    @SerializedName("userDisclosureScope") val userDisclosureScope : Int,
    @SerializedName("userProfilePicture") val userProfilePicture : String?,
    @SerializedName("profileCreateDate") val profileCreateDate : Date
    )
