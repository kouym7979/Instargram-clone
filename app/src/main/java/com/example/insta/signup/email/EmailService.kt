package com.example.insta.signup.email

import com.example.insta.config.ApplicationClass
import com.example.insta.signup.email.models.EmailSearch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmailService(val view : EmailFragmentView) {

    fun tryGetEmail(email : String){
        val EmailRetrofitInterface = ApplicationClass.sRetrofit.create(EmailRetrofitInterface::class.java)
        EmailRetrofitInterface.getEmail(email).enqueue(object : Callback<EmailSearch> {
            override fun onResponse(call: Call<EmailSearch>, response: Response<EmailSearch>) {
                view.onGetEmailSuccess(response.body() as EmailSearch)
            }

            override fun onFailure(call: Call<EmailSearch>, t: Throwable) {
                view.onGetEmailFailure(t.message ?: "통신 오류")
            }
        })
    }
}