package com.api.study.riot_api.viewModel.activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.api.study.riot_api.data.model.MySharedPreferences
import com.api.study.riot_api.data.network.retrofit.lol.LOLapi
import com.api.study.riot_api.data.network.retrofit.client.ClientRetrofit
import com.api.study.riot_api.data.network.retrofit.lol.response.user_matches_response.UserMatchesResponse
import com.api.study.riot_api.data.network.retrofit.riot.response.RiotVersionsResponse
import com.api.study.riot_api.ui.activity.LOLBaseActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class LOLStatsSearchViewModel : ViewModel() {

    companion object {
        const val API_KEY = "RGAPI-f9035c09-8617-4247-92e1-023c40f5b83b"
    }

    private var krRetrofitInstance: LOLapi =
        ClientRetrofit.krGetInstance().create(LOLapi::class.java)
    private var asiarRetrofitInstance: LOLapi =
        ClientRetrofit.AsiarGetInstance().create(LOLapi::class.java)
    private var ddragonGetInstance: LOLapi =
        ClientRetrofit.ddragonGetInstance().create(LOLapi::class.java)


    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    private val _userInformationData =
        MutableLiveData<com.api.study.riot_api.data.network.retrofit.lol.response.UserInformationResponse>()
    val userInformationData: LiveData<com.api.study.riot_api.data.network.retrofit.lol.response.UserInformationResponse>
        get() = _userInformationData

    private val _userMatchesId =
        MutableLiveData<com.api.study.riot_api.data.network.retrofit.lol.response.UserMatchesIdResponse>()
    val userMatchId: LiveData<com.api.study.riot_api.data.network.retrofit.lol.response.UserMatchesIdResponse>
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
        var data: RiotVersionsResponse
        CoroutineScope(Dispatchers.IO).async {
            data = ddragonGetInstance.get_lol_versions()
            MySharedPreferences(LOLBaseActivity.ApplicationContext()).lolVersion = data[0]
        }
    }

    fun setInputUserName(text: String) = viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).async {
            getVersions()
            val data = ArrayList<UserMatchesResponse>()
            _userName.postValue(text)
            val userMatchesList = mutableListOf<UserMatchesResponse>()
            val userData = async {
                krRetrofitInstance.get_user_information_name(
                    text, API_KEY
                )
            }

            val puuid = userData.await().puuid
            MySharedPreferences(LOLBaseActivity.ApplicationContext()).lolpuuid = puuid
            Log.d("상태",MySharedPreferences(LOLBaseActivity.ApplicationContext()).lolpuuid.toString())
            val userMatchesId = async {
                asiarRetrofitInstance.get_user_matchesId(
                    puuid, API_KEY, 0, 10
                )
            }
            userMatchesList.clear()
            for (userMatchesId in userMatchesId.await()) {
                try {
                    val userMatches = async {
                        asiarRetrofitInstance.get_user_matches(
                            userMatchesId, API_KEY
                        )
                    }

                    data.add(userMatches.await())
                } catch (e: Exception) {
                    Log.d("ERROR", e.message.toString())
                }
            }
            _recyclerView.postValue(data)
        }
    }

    fun getTimeAfterGameOver(gameEndTimestamp: Long): String {
        val endDateTime =
            LocalDateTime.ofInstant(Instant.ofEpochMilli(gameEndTimestamp), ZoneId.systemDefault())

        // 현재 시간 얻기
        val currentDateTime = LocalDateTime.now()

        // 게임 종료 시간과 현재 시간의 차이 계산
        val duration = Duration.between(endDateTime, currentDateTime)

        val days = duration.toDays()
        val hours = duration.toHours() % 24
        val minutes = duration.toMinutes() % 60

        return if (days == 0L && hours == 0L) {
            "${minutes.toInt()}분전"
        } else if (days == 0L) {
            if (minutes >= 50) {
                "${hours.toInt() + 1}시간전"
            } else {
                "${hours.toInt()}시간전"
            }
        } else if(hours >= 12){
            "${days.toInt()+1}일전"
        } else {
            "${days.toInt()}일전"
        }
    }

}