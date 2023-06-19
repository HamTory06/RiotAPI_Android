package com.api.study.riot_api.data.network.retrofit.client

import com.api.study.riot_api.data.network.retrofit.lol.LOLapi
import com.api.study.riot_api.data.network.retrofit.mainserver.API
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ClientRetrofit {
    const val krBaseURL = "https://kr.api.riotgames.com/"
    const val asiarBaseURL = "https://asia.api.riotgames.com/"
    const val ddragonBaseURL = "https://ddragon.leagueoflegends.com/"
    const val BASE_URL = "http://192.168.70.6:8080/api/"

    val loggingInterceptor = LoggingInterceptor()

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val krRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(krBaseURL)
//        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val krApi: LOLapi = krRetrofit.create(LOLapi::class.java)

    private val ddragonRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(ddragonBaseURL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val ddragonApi: LOLapi = ddragonRetrofit.create(LOLapi::class.java)


    private val asiaretrofit: Retrofit = Retrofit.Builder()
        .baseUrl(asiarBaseURL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val asiaLOLAPI: LOLapi = asiaretrofit.create(LOLapi::class.java)

    val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    val api: API = retrofit.create(API::class.java)

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