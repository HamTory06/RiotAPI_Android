package com.api.study.riot_api.data.model.dto.loginDto.Response

data class Token(
    val accessToken: String,
    val refreshToken: String
)