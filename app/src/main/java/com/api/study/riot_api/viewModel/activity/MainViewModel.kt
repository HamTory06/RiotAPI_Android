package com.api.study.riot_api.viewModel.activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.api.study.riot_api.data.model.MySharedPreferences
import com.api.study.riot_api.data.network.retrofit.API
import com.api.study.riot_api.data.network.retrofit.ClientRetrofit
import com.api.study.riot_api.data.network.retrofit.response.LOLVersionsResponse
import com.api.study.riot_api.data.network.retrofit.response.UserInformationResponse
import com.api.study.riot_api.data.network.retrofit.response.UserMatchesIdResponse
import com.api.study.riot_api.data.network.retrofit.response.user_matches_response.UserMatchesResponse
import com.api.study.riot_api.ui.activity.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

class MainViewModel : ViewModel() {

    companion object {
        const val api_key = "RGAPI-f9035c09-8617-4247-92e1-023c40f5b83b"
    }

    private var krRetrofitInstance: API = ClientRetrofit.krGetInstance().create(API::class.java)
    private var asiarRetrofitInstance: API = ClientRetrofit.AsiarGetInstance().create(API::class.java)
    private var ddragonGetInstance: API = ClientRetrofit.ddragonGetInstance().create(API::class.java)


    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    private val _userInformationData = MutableLiveData<UserInformationResponse>()
    val userInformationData: LiveData<UserInformationResponse>
        get() = _userInformationData

    private val _userMatchesId = MutableLiveData<UserMatchesIdResponse>()
    val userMatchId: LiveData<UserMatchesIdResponse>
        get() = _userMatchesId

    private val _userMatches = MutableLiveData<List<UserMatchesResponse>>()
    val userMatches: LiveData<List<UserMatchesResponse>>
        get() = _userMatches

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String>
        get() = _userName

    private val _recyclerView = MutableLiveData<ArrayList<UserMatchesResponse>>()
    val recycler: LiveData<ArrayList<UserMatchesResponse>>
        get() = _recyclerView


    private fun getVersions() {
        var data: LOLVersionsResponse
        CoroutineScope(Dispatchers.IO).async {
            data = ddragonGetInstance.get_lol_versions()
            MySharedPreferences(MainActivity.ApplicationContext()).version = data[0]
        }
    }

    fun setInputUserName(text: String) = viewModelScope.launch {
        getVersions()
        val data = ArrayList<UserMatchesResponse>()
        _userName.postValue(text)
        val userMatchesList = mutableListOf<UserMatchesResponse>()
        CoroutineScope(Dispatchers.IO).async {
            if (!_userName.value.isNullOrEmpty()) {
                val userData = async {
                    krRetrofitInstance.get_user_information_name(
                        text, api_key
                    )
                }

                Log.d("유저 정보",userData.await().toString())
                val puuid = userData.await().puuid
                MySharedPreferences(MainActivity.ApplicationContext()).puuid = puuid
                val userMatchesId = async {
                    asiarRetrofitInstance.get_user_matchesId(
                        puuid, api_key, 0, 10
                    )
                }
                userMatchesList.clear()
                for (userMatchesId in userMatchesId.await()) {
                    try {
                        val userMatches = async {
                            asiarRetrofitInstance.get_user_matches(
                                userMatchesId, api_key
                            )
                        }
                        data.add(userMatches.await())
                    } catch (e: Exception) {
                        Log.d("ERROR", e.message.toString())
                    }
                }
            }
            _recyclerView.postValue(data)
        }
    }


    fun getTimeAfterGameOver(gameEndTimestamp:Long): String{
        val endDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(gameEndTimestamp), ZoneId.systemDefault())

        // 현재 시간 얻기
        val currentDateTime = LocalDateTime.now()

        // 게임 종료 시간과 현재 시간의 차이 계산
        val duration = Duration.between(endDateTime, currentDateTime)

        val days = duration.toDays()
        val hours = duration.toHours() % 24
        val minutes = duration.toMinutes() % 60

        return if(days == 0L && hours == 0L){
            "${minutes.toInt()}분전"
        } else if(days == 0L){
            if(minutes < 30){
                "${hours.toInt()+1}시간전"
            } else {
                "${hours.toInt()}시간전"
            }
        } else {
            "${days.toInt()}일전"
        }
    }

}