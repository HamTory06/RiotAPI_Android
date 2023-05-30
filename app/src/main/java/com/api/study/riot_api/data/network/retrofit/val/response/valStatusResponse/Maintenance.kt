package com.api.study.riot_api.data.network.retrofit.`val`.response.valStatusResponse

data class Maintenance(
    val archive_at: String,
    val created_at: String,
    val id: Int,
    val incident_severity: Any,
    val maintenance_status: String,
    val platforms: List<String>,
    val titles: List<Title>,
    val updated_at: Any,
    val updates: List<Update>
)