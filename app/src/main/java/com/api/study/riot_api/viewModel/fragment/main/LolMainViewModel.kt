package com.api.study.riot_api.viewModel.fragment.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LolMainViewModel: ViewModel() {
    private val _backButtonStatus = MutableLiveData<Boolean>()
    val backButtonStatus: LiveData<Boolean> = _backButtonStatus

    private val _searchButtonStatus = MutableLiveData<Boolean>()
    val searchButtonStatus: LiveData<Boolean> = _searchButtonStatus


    fun onclickBackButton(){
        _backButtonStatus.value = true
    }

    fun onClickSearchButton(){
        _searchButtonStatus.value = true
    }


}