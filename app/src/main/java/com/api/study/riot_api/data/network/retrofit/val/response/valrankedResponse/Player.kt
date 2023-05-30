package com.api.study.riot_api.data.network.retrofit.`val`.response.valrankedResponse

data class Player(
    val competitiveTier: Int,
    val gameName: String,
    val leaderboardRank: Int,
    val numberOfWins: Int,
    val puuid: String,
    val rankedRating: Int,
    val tagLine: String
)