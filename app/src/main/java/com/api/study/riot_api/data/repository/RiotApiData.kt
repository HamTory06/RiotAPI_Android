package com.api.study.riot_api.data.repository

import com.api.study.riot_api.data.network.retrofit.response.User_Information_response

interface RiotApiData {
    fun onRiotAPI_GetUser_Information(data: User_Information_response)
}