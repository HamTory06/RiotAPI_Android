package com.api.study.riot_api.data.network.retrofit.riot

import com.api.study.riot_api.data.network.retrofit.riot.response.RiotUserPuuidResponse
import com.api.study.riot_api.data.network.retrofit.riot.response.RiotVersionsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RiotApi {


    @GET("/riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}")
    suspend fun get_user_puuid(
        @Path(value = "gameName") gameName: String,
        @Path(value = "tagLine") tagLine: String,
        @Query(value = "api_key") api_key: String

    ): RiotUserPuuidResponse

    @GET("/riot/account/v1/accounts/by-puuid/{puuid}")
    suspend fun get_user_puuid(
        @Path(value = "puuid") puuid: String,
        @Query(value = "api_key") api_key: String
    ): RiotUserPuuidResponse


}