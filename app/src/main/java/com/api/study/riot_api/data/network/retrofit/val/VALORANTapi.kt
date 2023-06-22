package com.api.study.riot_api.data.network.retrofit.`val`

import com.api.study.riot_api.data.network.retrofit.`val`.response.valStatusResponse.ValStatusResponse
import com.api.study.riot_api.data.network.retrofit.`val`.response.valrankedResponse.ValRankedesponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VALORANTapi {

    @GET("/val/status/v1/platform-data")
    suspend fun getValStatus(
        @Query(value = "api_key") api_key: String
    ): ValStatusResponse

    @GET("/val/content/v1/contents")
    suspend fun getValContents(
        @Query(value = "api_key") api_key: String
    ): ValStatusResponse

    @GET("/val/ranked/v1/leaderboards/by-act/{actId}")
    suspend fun getRanked(
        @Path(value = "actId") actId: String,
        @Query(value = "api_key") api_key: String
    ): ValRankedesponse

    @GET("/val/match/v1/matches/{matchId}")
    suspend fun getMatch(
        @Path(value = "matchId") matchId: String,
        @Query(value = "api_key") api_key: String
    )

    @GET("/val/match/v1/matchlists/by-puuid/{puuid}")
    suspend fun getMatchid(
        @Path(value = "puuid") puuId: String,
        @Query(value = "api_key") api_key: String
    )

}