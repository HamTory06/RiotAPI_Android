package com.api.study.riot_api.data.network.retrofit.riot

import com.api.study.riot_api.data.network.retrofit.riot.response.RiotUserPuuIdResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RiotApi {


    @GET("/riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}")
    suspend fun getUserPuuId(
        @Path(value = "gameName") gameName: String,
        @Path(value = "tagLine") tagLine: String,
        @Query(value = "api_key") api_key: String
    ): RiotUserPuuIdResponse

    @GET("/riot/account/v1/accounts/by-puuid/{puuid}")
    suspend fun getUserPuuId(
        @Path(value = "puuid") puuid: String,
        @Query(value = "api_key") api_key: String
    ): RiotUserPuuIdResponse


}