package com.api.study.riot_api.data.network.retrofit

import com.api.study.riot_api.data.network.retrofit.test.LoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ClientRetrofit {
    const val krBaseURL = "https://kr.api.riotgames.com/"
    const val asiarBaseURL = "https://asia.api.riotgames.com/"
    const val ddragonBaseURL = "https://ddragon.leagueoflegends.com/"


    val loggingInterceptor = LoggingInterceptor()

    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val krRetrofit = Retrofit.Builder()
        .baseUrl(krBaseURL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val krApi: API = krRetrofit.create(API::class.java)


    val ddragonRetrofit = Retrofit.Builder()
        .baseUrl(ddragonBaseURL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val ddragonApi: API = ddragonRetrofit.create(API::class.java)


    val asiaretrofit = Retrofit.Builder()
        .baseUrl(asiarBaseURL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val asiaApi: API = asiaretrofit.create(API::class.java)


    fun krGetInstance() : Retrofit {
        return krRetrofit
    }

    fun AsiarGetInstance() : Retrofit {
        return asiaretrofit
    }

    fun ddragonGetInstance() : Retrofit {
        return ddragonRetrofit
    }
}