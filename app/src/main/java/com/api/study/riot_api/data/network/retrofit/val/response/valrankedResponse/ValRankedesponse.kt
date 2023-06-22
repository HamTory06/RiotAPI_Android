package com.api.study.riot_api.data.network.retrofit.`val`.response.valrankedResponse


data class ValRankedesponse(
    val actId: String,
    val immortalStartingIndex: Int,
    val immortalStartingPage: Int,
    val players: List<Player>,
    val query: String,
    val shard: String,
    val startIndex: Int,
    val tierDetails: TierDetails,
    val topTierRRThreshold: Int,
    val totalPlayers: Int
)