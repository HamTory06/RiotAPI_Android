package com.api.study.riot_api.data.network.retrofit.client

import com.api.study.riot_api.data.network.retrofit.API
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClientRetrofit {
    private const val BASE_URL = "http://192.168.0.9:8080/api/"

    private val loggingInterceptor = LoggingInterceptor()

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    val api: API = retrofit.create(API::class.java)
}