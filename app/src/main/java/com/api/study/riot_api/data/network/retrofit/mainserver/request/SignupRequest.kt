package com.api.study.riot_api.data.network.retrofit.mainserver.request

data class SignupRequest(
    val id: String,
    val mail: String,
    val name: String,
    val password: String
)