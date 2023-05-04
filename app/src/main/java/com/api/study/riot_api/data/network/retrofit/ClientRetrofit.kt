package com.api.study.riot_api.data.network.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ClientRetrofit {
    const val baseurl = "https://kr.api.riotgames.com/"
    val loggingInterceptor = LoggingInterceptor()

    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(baseurl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: API = retrofit.create(API::class.java)
}