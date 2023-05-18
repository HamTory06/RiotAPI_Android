package com.api.study.riot_api.data.network.retrofit.response.summoner_information_response

data class SummonerBoost(
    val cooldown: List<Int>,
    val cooldownBurn: String,
    val cost: List<Int>,
    val costBurn: String,
    val costType: String,
    val datavalues: Datavalues,
    val description: String,
    val effect: List<List<Double>>,
    val effectBurn: List<String>,
    val id: String,
    val image: Image,
    val key: String,
    val maxammo: String,
    val maxrank: Int,
    val modes: List<String>,
    val name: String,
    val range: List<Int>,
    val rangeBurn: String,
    val resource: String,
    val summonerLevel: Int,
    val tooltip: String,
    val vars: List<Any>
)