package com.api.study.riot_api.data.network.retrofit.`val`

import com.api.study.riot_api.data.network.retrofit.`val`.response.valStatusResponse.ValStatusResponse
import com.api.study.riot_api.data.network.retrofit.`val`.response.valrankedResponse.valRankedesponse
import retrofit2.http.GET
import retrofit2.http.Path

interface VALORANTapi {

    @GET("/val/status/v1/platform-data")
    suspend fun get_val_status(): ValStatusResponse

    @GET("/val/ranked/v1/leaderboards/by-act/{actId}")
    suspend fun get_val_ranked(
        @Path(value = "actId") actId: String
    )

    @GET("/val/content/v1/contents")
    suspend fun get_val_contents(): ValStatusResponse

    @GET("/val/ranked/v1/leaderboards/by-act/{actId}")
    suspend fun get_ranked(
        @Path(value = "actId") actId:String
    ): valRankedesponse

}