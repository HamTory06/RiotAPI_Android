package com.api.study.riot_api.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.api.study.riot_api.data.network.retrofit.API
import com.api.study.riot_api.data.network.retrofit.ClientRetrofit
import com.api.study.riot_api.data.network.retrofit.response.User_Information_response
import com.api.study.riot_api.data.network.retrofit.response.User_matchesId_response
import com.api.study.riot_api.data.network.retrofit.response.user_matches_response.User_matches_response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class Main_ViewModel : ViewModel() {

    companion object {
        const val api_key = "RGAPI-f9035c09-8617-4247-92e1-023c40f5b83b"
    }


    var krRetrofitInstance: API = ClientRetrofit.krGetInstance().create(API::class.java)
    var AsiarRetrofitInstance: API = ClientRetrofit.AsiarGetInstance().create(API::class.java)


    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    private val _userInformationData = MutableLiveData<User_Information_response>()
    val userInformationData: LiveData<User_Information_response>
        get() = _userInformationData

    private val _userMatchesId = MutableLiveData<User_matchesId_response>()
    val userMatchId: LiveData<User_matchesId_response>
        get() = _userMatchesId

    private val _userMatches = MutableLiveData<List<User_matches_response>>()
    val userMatches: LiveData<List<User_matches_response>>
        get() = _userMatches

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String>
        get() = _userName

    private val _recyclerView = MutableLiveData<ArrayList<User_matches_response>>()
    val recycler: LiveData<ArrayList<User_matches_response>>
        get() = _recyclerView


    fun setInputUserName(text: String) = viewModelScope.launch {
        val data = ArrayList<User_matches_response>()
        _userName.postValue(text)
        val userMatchesList = mutableListOf<User_matches_response>()
        CoroutineScope(Dispatchers.IO).async {
            if (!_userName.value.isNullOrEmpty()) {
                val UserData = async {
                    krRetrofitInstance.get_user_information_name(
                        text, api_key
                    )
                }
                Log.d("user_information", UserData.await().toString())

                val puuid = UserData.await().puuid
                val userMatchesId = async {
                    AsiarRetrofitInstance.get_user_matchesId(
                        puuid, api_key, 0, 10
                    )
                }
                userMatchesList.clear()
                Log.d("UserMatchesId", userMatchesId.await().toString())
                for (userMatchesId in userMatchesId.await()) {
                    Log.d("for문", userMatchesId)
                    try {
                        val userMatches = async {
                            AsiarRetrofitInstance.get_user_matches(
                                userMatchesId, api_key
                            )
                        }
                        Log.d("챔피언프로필",userMatches.await().info.participants[0].championId.toString())
                        data.add(userMatches.await())
                        Log.d("상태", userMatches.await().toString())
                    } catch (e: Exception) {
                        Log.d("ERROR", e.message.toString())
                    }
                }
            }
            _recyclerView.postValue(data)
        }
    }
}