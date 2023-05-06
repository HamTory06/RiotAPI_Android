package com.api.study.riot_api.data.repository

import com.api.study.riot_api.data.network.retrofit.response.User_Information_response
import com.api.study.riot_api.data.network.retrofit.response.User_matchesId_response
import com.api.study.riot_api.data.network.retrofit.response.user_matches_response.User_matches_response

interface RiotApiData {
    fun onRiotAPI_GetUser_Information(data: User_Information_response)

    fun onRiotAPI_GetUser_MatchesId(data:User_matchesId_response)

    fun onRiotAPI_GetUser_Matchse(data: User_matches_response)
}