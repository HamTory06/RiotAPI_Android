package com.api.study.riot_api.data.network.retrofit.riot

import com.api.study.riot_api.data.network.retrofit.riot.response.RiotUserPuuidResponse
import com.api.study.riot_api.data.network.retrofit.riot.response.RiotVersionsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RiotApi {
    @GET("/api/versions.json")
    suspend fun get_lol_versions(): RiotVersionsResponse

    @GET("/riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}")
    suspend fun get_user_puuid(
        @Path(value = "gameName") gameName: String,
        @Path(value = "tagLine") tagLine: String
    ): RiotUserPuuidResponse

    @GET("/riot/account/v1/accounts/by-puuid/{puuid}")
    suspend fun get_user_puuid(
        @Path(value = "puuid") puuid: String
    ): RiotUserPuuidResponse


}