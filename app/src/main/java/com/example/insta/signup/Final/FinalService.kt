package com.example.insta.signup.Final

import com.example.insta.config.ApplicationClass
import com.example.insta.signup.models.PostSignUpRequest
import com.example.insta.signup.models.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FinalService(val view: FinalActivity) {


    fun tryPostSignUp(postSignUpRequest: PostSignUpRequest){
        val finalRetrofitInterface =ApplicationClass.sRetrofit.create(FinalRetrofitInterface::class.java)
        finalRetrofitInterface.postSignUp(postSignUpRequest).enqueue(object: Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>
            ) {
                view.onPostSignUpSuccess(response.body() as SignUpResponse)
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                view.onPostSignUpFailure(t.message?:"통신 오류")
            }

        })
    }



}