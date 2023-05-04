package com.api.study.riot_api.data.network.retrofit

import com.api.study.riot_api.data.network.retrofit.response.User_Information_response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface API {
    @GET("lol/summoner/v4/summoners/by-name/{username}")
    fun get_user_information_name(
        @Path(value = "username") name: String,
        @Query(value = "api_key") api_key: String
    ): Call<User_Information_response>

    @GET("lol/summoner/v4/summoners/by-account/{accountid}")
    fun get_user_information_accountid(
        @Path(value = "accountid") accountid: String,
        @Query(value = "api_key") api_key: String

    ): Call<User_Information_response>

    @GET("lol/summoner/v4/summoners/by-puuid/{PUUID}")
    fun get_user_information_puuid(
        @Path(value = "PUUID") puuid: String,
        @Query(value = "api_key") api_key: String
    ): Call<User_Information_response>
}