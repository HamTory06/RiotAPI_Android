package com.api.study.riot_api.data.network.retrofit.client

import com.api.study.riot_api.data.network.retrofit.lol.LOLApi
import com.api.study.riot_api.data.network.retrofit.mainserver.API
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClientRetrofit {
    private const val krBaseURL = "https://kr.api.riotgames.com/"
    private const val asiarBaseURL = "https://asia.api.riotgames.com/"
    private const val ddragonBaseURL = "https://ddragon.leagueoflegends.com/"
    private const val BASE_URL = "http://192.168.70.6:8080/api/"

    private val loggingInterceptor = LoggingInterceptor()

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val krRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(krBaseURL)
//        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val krApi: LOLApi = krRetrofit.create(LOLApi::class.java)

    private val ddragonRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(ddragonBaseURL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val ddragonApi: LOLApi = ddragonRetrofit.create(LOLApi::class.java)


    private val asiaRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(asiarBaseURL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val asiaLOLAPI: LOLApi = asiaRetrofit.create(LOLApi::class.java)

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
        return asiaRetrofit
    }

    fun ddragonGetInstance() : Retrofit {
        return ddragonRetrofit
    }
}