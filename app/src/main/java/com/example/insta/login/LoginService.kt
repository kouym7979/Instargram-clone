package com.example.insta.login

import com.example.insta.login.models.LoginRequest
import com.example.insta.login.models.LoginResponse
import com.example.insta.config.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService (val view : LoginActivity) {

    fun tryPostLogin(loginRequest: LoginRequest){
        val finalRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        finalRetrofitInterface.postLogin(loginRequest).enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>
            ) {
                view.onPostLoginSuccess(response.body() as LoginResponse)
            }

            override fun onFailure(call: Call<LoginResponse>, t:
            Throwable) {
                view.onPostLoginFailure(t.message?:"통신 오류")
            }

        })
    }
}