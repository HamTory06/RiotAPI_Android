package com.api.study.riot_api.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.api.study.riot_api.data.network.retrofit.Communication
import com.api.study.riot_api.data.network.retrofit.response.User_Information_response
import com.api.study.riot_api.data.network.retrofit.response.User_matchesId_response
import com.api.study.riot_api.data.network.retrofit.response.user_matches_response.User_matches_response
import com.api.study.riot_api.data.repository.RiotApiData

class Main_ViewModel() : ViewModel() {
    private val repository = Communication()

    private val _userInformationData = MutableLiveData<User_Information_response>()
    val userInformationData: LiveData<User_Information_response>
        get() = _userInformationData

    private val _userMatchesId = MutableLiveData<User_matchesId_response>()
    val userMatchId: LiveData<User_matchesId_response>
        get() = _userMatchesId

    private val _userMatches = MutableLiveData<User_matches_response>()
    val userMatches: LiveData<User_matches_response>
        get() = _userMatches

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String>
        get() = _userName


    private val riotApiData = object : RiotApiData {
        override fun onRiotAPI_GetUser_Information(data: User_Information_response) {
            _userInformationData.value = data
            Log.d("onRiotAPI_GetUser_Information", _userInformationData.value.toString())
        }

        override fun onRiotAPI_GetUser_MatchesId(data: User_matchesId_response) {
            TODO("MatchesId가져옴")
            Log.d("onRiotAPI_GetUser_MatchesId",data.toString())
            _userMatchesId.value = data
        }

        override fun onRiotAPI_GetUser_Matchse(data: User_matches_response) {
            TODO("MatchesId사용해서 Matches가져옴")
            Log.d("onRiotAPI_GetUser_Matchse",data.toString())
            _userMatches.value = data
        }
    }

    fun setInputUserName(text: String) {
        _userName.value = text

        if (!_userName.value.isNullOrEmpty()) {
            repository.GetUserInformation(text, riotApiData)
        }
        if(_userInformationData.value.toString().isNullOrEmpty()){
            Log.d("_userInformationData.value.toString().isNullOrEmpty()","nonnull")
            repository.GetUserMatchesId(userInformationData.value?.puuid.toString(),riotApiData)
        }
    }
}