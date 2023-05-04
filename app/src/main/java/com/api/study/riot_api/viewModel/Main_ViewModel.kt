package com.api.study.riot_api.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.api.study.riot_api.data.network.retrofit.Communication
import com.api.study.riot_api.data.network.retrofit.LoggingInterceptor
import com.api.study.riot_api.data.network.retrofit.response.User_Information_response

class Main_ViewModel: ViewModel() {
    private val repository = Communication()


    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String>
        get() = _userName

    fun setInputUserName(text: String) {
        _userName.value = text
        if(!_userName.value.isNullOrEmpty()){
            val userData: LiveData<User_Information_response> = repository.GetUserInformation(userName.value!!)
            Log.d("상태", userData.value.toString())
        }
    }
}