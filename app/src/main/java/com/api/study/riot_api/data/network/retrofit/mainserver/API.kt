package com.api.study.riot_api.data.network.retrofit.mainserver

import com.api.study.riot_api.data.network.retrofit.mainserver.request.SignupRequest
import com.api.study.riot_api.data.network.retrofit.mainserver.response.LoginResponse
import com.api.study.riot_api.data.network.retrofit.mainserver.response.SignupResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface API {

    @POST("/account/signup")
    fun postSignup(
        @Body body: SignupRequest
    ): Call<SignupResponse>

    @POST("/account/login")
    fun postLogin(
        @Query("id") id: String,
        @Query("password") password: String
    ): Call<LoginResponse>
}