package com.api.study.riot_api.viewModel.fragment.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    private val _loginButtonStatus = MutableLiveData<Boolean>()
    val loginButtonEvent: LiveData<Boolean> = _loginButtonStatus

    private val _signupButtonStatus = MutableLiveData<Boolean>()
    val signupButtonStatus: LiveData<Boolean> = _signupButtonStatus

    val idErrorMessage = MutableLiveData<String>()
    val passwordErrorMessage = MutableLiveData<String>()
    val idErrorMessageTextColor = MutableLiveData<Int>()
    val passwordErrorMessageTextColor = MutableLiveData<Int>()

    fun changeIdErrorMessageTextViewColor(textColor: Int) {
        idErrorMessageTextColor.value = textColor
    }

    fun changePasswordErrorMessageTextViewColor(textColor: Int) {
        passwordErrorMessageTextColor.value = textColor
    }
    fun onClickSignupButton(){
        _signupButtonStatus.value = true
    }

    fun onClickLoginButton(){
        _loginButtonStatus.value = true
    }
    fun setIdErrorMessage(errorMessage: String){
        idErrorMessage.value = errorMessage
    }

    fun setPasswordErrorMessage(errorMessage: String){
        passwordErrorMessage.value = errorMessage
    }





}