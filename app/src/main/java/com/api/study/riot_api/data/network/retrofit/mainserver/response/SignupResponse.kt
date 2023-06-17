package com.api.study.riot_api.data.network.retrofit.mainserver.response

data class SignupResponse(
    val id: String,
    val lolName: String,
    val mail: String,
    val name: String,
    val password: String,
    val valName: String
)