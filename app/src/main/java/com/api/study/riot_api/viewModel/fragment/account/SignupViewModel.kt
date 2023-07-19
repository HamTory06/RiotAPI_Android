package com.api.study.riot_api.viewModel.fragment.account

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignupViewModel : ViewModel() {
    private val _signupButtonStatus = MutableLiveData<Boolean>()
    val signupButtonStatus: LiveData<Boolean> = _signupButtonStatus

    private val _sameIdCheckButtonStatus = MutableLiveData<Boolean>()
    val sameIdCheckButtonStatus: LiveData<Boolean> = _sameIdCheckButtonStatus

    private val _passwordErrorMessageTextColor = MutableLiveData<Int>()
    val passwordErrorMessageTextColor: LiveData<Int> = _passwordErrorMessageTextColor

    private val _idErrorMessageTextColor = MutableLiveData<Int>()
    val idErrorMessageTextColor: LiveData<Int> = _idErrorMessageTextColor

    private val _nameErrorMessageText = MutableLiveData<String>()
    val nameErrorMessageText: LiveData<String> = _nameErrorMessageText


    val idErrorMessageTextView = MutableLiveData<String>()
    val passwordErrorMessageTextView = MutableLiveData<String>()
    val checkPassword = ObservableField<String>("")


    fun changeIdErrorMessageTextColor(textColor: Int) {
        _idErrorMessageTextColor.value = textColor
    }

    fun changePasswordErrorMessageTextColor(textColor: Int) {
        _passwordErrorMessageTextColor.value = textColor
    }

    fun onClickSignupButton() {
        _signupButtonStatus.value = true
    }

    fun onClickSameIdCheckButton() {
        _sameIdCheckButtonStatus.value = true
    }

    fun idErrorText(errorMessage: String) {
        idErrorMessageTextView.value = errorMessage
    }

    fun passwordErrorText(errorMessage: String) {
        passwordErrorMessageTextView.value = errorMessage
    }

    fun nameErrorText(errorMessage: String){
        _nameErrorMessageText.value = errorMessage
    }


}