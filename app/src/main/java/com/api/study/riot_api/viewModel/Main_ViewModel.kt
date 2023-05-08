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
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.create

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


    fun setInputUserName(text: String) = viewModelScope.launch {
        _userName.postValue(text)
        val userMatchesList = mutableListOf<User_matches_response>()

        CoroutineScope(Dispatchers.IO).async {
            if (!_userName.value.isNullOrEmpty()) {
                val UserData = async {
                    krRetrofitInstance.get_user_information_name(
                        text, api_key
                    )
                }
                _userInformationData.postValue(UserData.await())
                Log.d("상태", UserData.await().toString())

                val puuid = UserData.await().puuid
                val UserMatchesId = async {
                    AsiarRetrofitInstance.get_user_matchesId(
                        puuid, api_key, 0, 10
                    )
                }

                _userMatchesId.postValue(UserMatchesId.await())
                Log.d("상태", UserMatchesId.await().toString())
                for (i in UserMatchesId.await()) {
                    Log.d("상태",i)
                    val UserMatches = async {
                        AsiarRetrofitInstance.get_user_matches(
                            i, api_key
                        )
                    }
                    Log.d("상태",UserMatches.await().toString())
                    userMatchesList.add(UserMatches.await())
                }
                _userMatches.value = userMatchesList
            }
        }
    }
}