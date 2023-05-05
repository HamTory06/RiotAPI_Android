package com.api.study.riot_api.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.api.study.riot_api.data.network.retrofit.ClientRetrofit
import com.api.study.riot_api.data.network.retrofit.Communication
import com.api.study.riot_api.data.network.retrofit.response.User_Information_response
import com.api.study.riot_api.data.repository.RiotApiData

class Main_ViewModel() : ViewModel() {
    private val repository = Communication()

    private val _userInformationData = MutableLiveData<User_Information_response>()
    val userInformationData: LiveData<User_Information_response>
        get() = _userInformationData

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String>
        get() = _userName

    private val riotApiData = object : RiotApiData {
        override fun onRiotAPI_GetUser_Information(data: User_Information_response) {
            Log.d("상태", data.toString())
            _userInformationData.value = data
        }
    }

    fun setInputUserName(text: String) {
        _userName.value = text

        if (!_userName.value.isNullOrEmpty()) {
            repository.GetUserInformation(text, riotApiData)
        }
    }
}