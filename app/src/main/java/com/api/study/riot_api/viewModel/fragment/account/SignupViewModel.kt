package com.api.study.riot_api.viewModel.fragment.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignupViewModel: ViewModel() {
    private val _signupButtonStatus = MutableLiveData<Boolean>()
    val signupButtonStatus: LiveData<Boolean> = _signupButtonStatus

    private val _sameIdCheckButtonStatus = MutableLiveData<Boolean>()
    val sameIdCheckButtonStatus: LiveData<Boolean> = _sameIdCheckButtonStatus

    val idEditTextErrorTextView = MutableLiveData<String>()
    val passwordEditTextErrorTextView = MutableLiveData<String>()
    val idSameCheckStatus = MutableLiveData<Boolean>()
    val passwordSameCheckStatus = MutableLiveData<Boolean>()
    val nameNullCheckStatus = MutableLiveData<Boolean>()
    val nameEditTextErrorTextView = MutableLiveData<String>()

    fun onClickSignupButton(){
        _signupButtonStatus.value = true
    }

    fun onClickSameIdCheckButton(){
        _sameIdCheckButtonStatus.value = true
    }

    fun idErrorTextView(errorMessage: String){
        idEditTextErrorTextView.value = errorMessage
    }

    fun passwordErrorTextView(errorMessage: String){
        passwordEditTextErrorTextView.value = errorMessage
    }

    fun idSameCheckStatus(status: Boolean){
        idSameCheckStatus.value = status
    }

    fun passwordSameCheckStatus(status: Boolean){
        passwordSameCheckStatus.value = status
    }

    fun nameNullCheckStatus(status: Boolean){
        nameNullCheckStatus.value = status
    }

    fun nameNullErrorTextView(errorMessage: String){
        nameEditTextErrorTextView.value = errorMessage
    }



}