package com.api.study.riot_api.viewModel.fragment.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainScreenViewModel : ViewModel() {
    private val _lolButtonStatus = MutableLiveData<Boolean>()
    val lolButtonStatus: LiveData<Boolean> = _lolButtonStatus

    private val _valorantButtonStatus = MutableLiveData<Boolean>()
    val valorantButtonStatus: LiveData<Boolean> = _valorantButtonStatus

    fun onclickLolButton() {
        _lolButtonStatus.value = true
    }

    fun onclickValorantButton() {
        _valorantButtonStatus.value = true
    }

}