package com.api.study.riot_api.data.network.retrofit.client

import com.api.study.riot_api.data.network.retrofit.lol.LOLapi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
//        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val krApi: LOLapi = krRetrofit.create(LOLapi::class.java)

    val ddragonRetrofit = Retrofit.Builder()
        .baseUrl(ddragonBaseURL)
//        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val ddragonApi: LOLapi = ddragonRetrofit.create(LOLapi::class.java)


    val asiaretrofit = Retrofit.Builder()
        .baseUrl(asiarBaseURL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val asiaLOLAPI: LOLapi = asiaretrofit.create(LOLapi::class.java)


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