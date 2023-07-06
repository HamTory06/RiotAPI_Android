package com.api.study.riot_api.data.model.dto

data class MatchInformationDto(
    val dataVersion: String,
    val gameCreation: Int,
    val gameDuration: Int,
    val gameEndTimestamp: Int,
    val gameMode: String,
    val gameName: String,
    val gameStartTimestamp: Int,
    val gameType: String,
    val gameVersion: String,
    val mapId: Int,
    val matchId: String
)