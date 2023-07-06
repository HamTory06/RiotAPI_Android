package com.api.study.riot_api.viewModel.fragment.account

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    private val _loginButtonStatus = MutableLiveData<Boolean>()
    val loginButtonEvent: LiveData<Boolean> = _loginButtonStatus

    private val _signupButtonStatus = MutableLiveData<Boolean>()
    val signupButtonStatus: LiveData<Boolean> = _signupButtonStatus
    fun onClickSignupButton(){
        _signupButtonStatus.value = true
    }

    fun onClickLoginButton(){
        _loginButtonStatus.value = true
    }



}