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

    val idErrorMessageTextView = MutableLiveData<String>()
    val passwordErrorMessageTextView = MutableLiveData<String>()
    val nameEditTextErrorTextView = MutableLiveData<String>()
    val idSameCheckStatus = MutableLiveData<Boolean>()
    val passwordSameCheckStatus = MutableLiveData<Boolean>()
    val nameNullCheckStatus = MutableLiveData<Boolean>()
    val idErrorMessageTextColor = MutableLiveData<Int>()
    val passwordErrorMessageTextColor = MutableLiveData<Int>()
    val checkPassword = ObservableField<String>("")


    fun changeIdErrorMessageTextViewColor(textColor: Int) {
        idErrorMessageTextColor.value = textColor
    }

    fun changePasswordErrorMessageTextViewColor(textColor: Int) {
        passwordErrorMessageTextColor.value = textColor
    }

    fun onClickSignupButton() {
        _signupButtonStatus.value = true
    }

    fun onClickSameIdCheckButton() {
        _sameIdCheckButtonStatus.value = true
    }

    fun idErrorTextView(errorMessage: String) {
        idErrorMessageTextView.value = errorMessage
    }

    fun passwordErrorTextView(errorMessage: String) {
        passwordErrorMessageTextView.value = errorMessage
    }

    fun idSameCheckStatus(status: Boolean) {
        idSameCheckStatus.value = status
    }

    fun passwordSameCheckStatus(status: Boolean) {
        passwordSameCheckStatus.value = status
    }

    fun nameNullCheckStatus(status: Boolean) {
        nameNullCheckStatus.value = status
    }

    fun nameNullErrorTextView(errorMessage: String) {
        nameEditTextErrorTextView.value = errorMessage
    }


}