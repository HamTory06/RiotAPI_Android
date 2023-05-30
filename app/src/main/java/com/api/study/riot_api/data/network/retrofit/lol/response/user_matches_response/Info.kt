package com.api.study.riot_api.data.network.retrofit.lol.response.user_matches_response

data class Info(
    val gameCreation: Long,
    val gameDuration: Int,
    val gameEndTimestamp: Long,
    val gameId: Long,
    val gameMode: String,
    val gameName: String,
    val gameStartTimestamp: Long,
    val gameType: String,
    val gameVersion: String,
    val mapId: Int,
    val participants: List<com.api.study.riot_api.data.network.retrofit.lol.response.user_matches_response.Participant>,
    val platformId: String,
    val queueId: Int,
    val teams: List<com.api.study.riot_api.data.network.retrofit.lol.response.user_matches_response.Team>,
    val tournamentCode: String
)