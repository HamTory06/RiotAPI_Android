package com.api.study.riot_api.data.network.retrofit.`val`.response.valStatusResponse

data class ValStatusResponse(
    val id: String,
    val incidents: List<Any>,
    val locales: List<String>,
    val maintenances: List<Maintenance>,
    val name: String
)