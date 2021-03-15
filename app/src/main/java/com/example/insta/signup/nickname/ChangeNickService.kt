package com.example.insta.signup.nickname

import com.example.insta.config.ApplicationClass
import com.example.insta.signup.nickname.models.ChangeNickCheck
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangeNickService(val view:ChangenickActivity) {
    fun tryGetNickname(nickname : String){
        val ChangeRetrofitInterface = ApplicationClass.sRetrofit.create(ChangeRetrofitInterface::class.java)
        ChangeRetrofitInterface.getNickname(nickname).enqueue(object : Callback<ChangeNickCheck> {
            override fun onResponse(call: Call<ChangeNickCheck>, response: Response<ChangeNickCheck>) {
                view.onGetNicknameSuccess(response.body() as ChangeNickCheck)
            }

            override fun onFailure(call: Call<ChangeNickCheck>, t: Throwable) {
                view.onGetNicknameFailure(t.message ?: "통신 오류")
            }
        })
    }
}