package com.api.study.riot_api.data.network.retrofit.lol

import com.api.study.riot_api.data.network.retrofit.lol.response.UserInformationResponse
import com.api.study.riot_api.data.network.retrofit.lol.response.UserMatchesIdResponse
import com.api.study.riot_api.data.network.retrofit.lol.response.user_matches_response.UserMatchesResponse
import com.api.study.riot_api.data.network.retrofit.riot.response.RiotVersionsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface LOLApi {

    @GET("/api/versions.json")
    suspend fun getLolVersions(): RiotVersionsResponse

    @GET("lol/summoner/v4/summoners/by-name/{username}")
    suspend fun getUserInformationName(
        @Path(value = "username") name: String,
        @Query(value = "api_key") api_key: String
    ): UserInformationResponse

    @GET("lol/summoner/v4/summoners/by-account/{accountid}")
    suspend fun getUserInformationAccountId(
        @Path(value = "accountid") accountid: String,
        @Query(value = "api_key") api_key: String
    ): UserInformationResponse

    @GET("lol/summoner/v4/summoners/by-puuid/{PUUID}")
    suspend fun getUserInformationPuuId(
        @Path(value = "PUUID") puuid: String,
        @Query(value = "api_key") api_key: String
    ): UserInformationResponse
    @GET("/lol/match/v5/matches/by-puuid/{PUUID}/ids")
    suspend fun getUserMatchesId(
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
    suspend fun getUserMatches(
        @Path(value = "matchId") matchId: String,
        @Query(value = "api_key") apt_key: String
    ): UserMatchesResponse


}