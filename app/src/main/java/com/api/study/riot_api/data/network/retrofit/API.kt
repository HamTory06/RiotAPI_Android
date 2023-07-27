package com.api.study.riot_api.data.network.retrofit

import com.api.study.riot_api.data.model.dto.CheckSameIdDto
import com.api.study.riot_api.data.model.dto.LolUserDto
import com.api.study.riot_api.data.model.dto.MatchInformationDto
import com.api.study.riot_api.data.model.dto.MatchesId
import com.api.study.riot_api.data.model.dto.signupDto.request.SignupRequestDto
import com.api.study.riot_api.data.model.dto.UpdateUserDto
import com.api.study.riot_api.data.model.dto.loginDto.Request.LoginRequestDto
import com.api.study.riot_api.data.model.dto.loginDto.Response.LoginResponseDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface API {

    @PUT("/update/users/{id}")
    fun updateUser(
        @Header("Authorization") Authorization: String,
        @Path("id") id: String,
        @Body updateUserDto: UpdateUserDto
    ): Call<UpdateUserDto>

    @POST("/api/account/signup")
    fun signup(
        @Body signupDto: SignupRequestDto
    ): Call<Void>

    @POST("/api/account/login")
    fun login(
        @Body loginDto: LoginRequestDto
    ): Call<LoginResponseDto>

    @GET("/api/account/check/sameId")
    fun checkSameId(
        @Query("id") id: String
    ): Call<CheckSameIdDto>


    @DELETE("/api/account/delete-user/{id}")
    fun deleteUser(
        @Header("Authorization") Authorization: String,
        @Path("id") id: String
    )

    @GET("/api/riotAPI/lol/match/getMatchInformation/{matchId}")
    fun matchInformation(
        @Path("matchId") matchId: String
    ): Call<MatchInformationDto>

    @GET("/api/riotAPI/lol/match/getMatchId/{puuid}")
    fun matchId(
        @Path("puuid") puuid: String?,
        @Query("start") start: Int,
        @Query("count") count: Int
    ): Call<MatchesId>

    @GET("/api/riotAPI/lol/by-name/{username}")
    fun findUser(
        @Path("username") username: String
    ): Call<LolUserDto>
}