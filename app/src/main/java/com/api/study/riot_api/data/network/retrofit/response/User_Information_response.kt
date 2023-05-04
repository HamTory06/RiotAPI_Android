package com.api.study.riot_api.data.network.retrofit.response

data class User_Information_response(
    val accountId: String,
    val id: String,
    val name: String,
    val profileIconId: Int,
    val puuid: String,
    val revisionDate: Long,
    val summonerLevel: Int
)