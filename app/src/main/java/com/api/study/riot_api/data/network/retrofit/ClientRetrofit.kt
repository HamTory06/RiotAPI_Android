package com.api.study.riot_api.data.network.retrofit

import com.api.study.riot_api.data.network.retrofit.test.LoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClientRetrofit {
    const val krBaseUrl = "https://kr.api.riotgames.com/"
    const val asiarBaseUrl = "https://asia.api.riotgames.com/"


    val loggingInterceptor = LoggingInterceptor()

    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val krretrofit = Retrofit.Builder()
        .baseUrl(krBaseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun krGetInstance() : Retrofit {
        return krretrofit
    }

    fun AsiarGetInstance() : Retrofit {
        return asiaretrofit
    }

    val krApi: API = krretrofit.create(API::class.java)


    val asiaretrofit = Retrofit.Builder()
        .baseUrl(asiarBaseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val asiaApi: API = asiaretrofit.create(API::class.java)
}