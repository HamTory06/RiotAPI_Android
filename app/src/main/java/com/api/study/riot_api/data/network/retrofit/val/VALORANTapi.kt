package com.api.study.riot_api.data.network.retrofit.`val`

import com.api.study.riot_api.data.network.retrofit.`val`.response.valStatusResponse.ValStatusResponse
import com.api.study.riot_api.data.network.retrofit.`val`.response.valrankedResponse.valRankedesponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VALORANTapi {

    @GET("/val/status/v1/platform-data")
    suspend fun get_val_status(
        @Query(value = "api_key") api_key: String
    ): ValStatusResponse

    @GET("/val/content/v1/contents")
    suspend fun get_val_contents(
        @Query(value = "api_key") api_key: String
    ): ValStatusResponse

    @GET("/val/ranked/v1/leaderboards/by-act/{actId}")
    suspend fun get_ranked(
        @Path(value = "actId") actId: String,
        @Query(value = "api_key") api_key: String
    ): valRankedesponse

    @GET("/val/match/v1/matches/{matchId}")
    suspend fun get_match(
        @Path(value = "matchId") matchId: String,
        @Query(value = "api_key") api_key: String
    )

    @GET("/val/match/v1/matchlists/by-puuid/{puuid}")
    suspend fun get_matchId(
        @Path(value = "puuid") puuid: String,
        @Query(value = "api_key") api_key: String
    )

}