package com.api.study.riot_api.data.network.retrofit

import com.api.study.riot_api.data.network.retrofit.response.LOLVersionsResponse
import com.api.study.riot_api.data.network.retrofit.response.UserInformationResponse
import com.api.study.riot_api.data.network.retrofit.response.UserMatchesIdResponse
import com.api.study.riot_api.data.network.retrofit.response.summoner_information_response.SummonerInformationResponse
import com.api.study.riot_api.data.network.retrofit.response.user_matches_response.UserMatchesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface API {
    @GET("lol/summoner/v4/summoners/by-name/{username}")
    suspend fun get_user_information_name(
        @Path(value = "username") name: String,
        @Query(value = "api_key") api_key: String
    ): UserInformationResponse

    @GET("lol/summoner/v4/summoners/by-account/{accountid}")
    suspend fun get_user_information_accountid(
        @Path(value = "accountid") accountid: String,
        @Query(value = "api_key") api_key: String

    ): UserInformationResponse

    @GET("lol/summoner/v4/summoners/by-puuid/{PUUID}")
    suspend fun get_user_information_puuid(
        @Path(value = "PUUID") puuid: String,
        @Query(value = "api_key") api_key: String
    ): UserInformationResponse
    @GET("/lol/match/v5/matches/by-puuid/{PUUID}/ids")
    suspend fun get_user_matchesId(
        @Path(value = "PUUID") puuid: String,
        @Query(value = "api_key") api_key: String,
        @Query(value = "start") start: Int,
        @Query(value = "count") count: Int,
//        @Query(value = "startTime") startTime: Long,
//        @Query(value = "endTime") endTime: Long,
//        @Query(value = "queue") queue: Int,
//        @Query(value = "type") type: String,
    ): UserMatchesIdResponse

    @GET("/lol/match/v5/matches/{matchId}")
    suspend fun get_user_matches(
        @Path(value = "matchId") matchId: String,
        @Query(value = "api_key") apt_key: String
    ): UserMatchesResponse

    @GET("/api/versions.json")
    suspend fun get_lol_versions(): LOLVersionsResponse

    @GET("/cdn/{version}/data/{local}/summoner.json")
    suspend fun get_information_summoner(
        @Path(value = "version") version: String,
        @Path(value = "local") local: String
    ): SummonerInformationResponse
}