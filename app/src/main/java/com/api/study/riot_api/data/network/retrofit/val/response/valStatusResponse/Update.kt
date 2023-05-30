package com.api.study.riot_api.data.network.retrofit.`val`.response.valStatusResponse

data class Update(
    val author: String,
    val created_at: String,
    val id: Int,
    val publish: Boolean,
    val publish_locations: List<String>,
    val translations: List<Translation>,
    val updated_at: String
)