package com.api.study.riot_api.data.network.retrofit

import com.api.study.riot_api.data.network.retrofit.response.User_Information_response
import com.api.study.riot_api.data.network.retrofit.response.User_matchesId_response
import com.api.study.riot_api.data.network.retrofit.response.user_matches_response.User_matches_response
import retrofit2.Call
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
    @GET("/lol/match/v5/matches/by-puuid/{PUUID}/ids")
    fun get_user_matchesId(
        @Path(value = "PUUID") puuid: String,
        @Query(value = "api_key") api_key: String,
        @Query(value = "start") start: Int,
        @Query(value = "count") count: Int,
//        @Query(value = "startTime") startTime: Long,
//        @Query(value = "endTime") endTime: Long,
//        @Query(value = "queue") queue: Int,
//        @Query(value = "type") type: String,
    ): Call<User_matchesId_response>

    @GET("/lol/match/v5/matches/{matchId}")
    fun get_user_matches(
        @Path(value = "matchId") matchId: String,
        @Query(value = "apt_key") apt_key: String
    ): Call<User_matches_response>
}