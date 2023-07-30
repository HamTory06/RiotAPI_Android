package com.api.study.riot_api.viewModel.fragment.main

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.api.study.riot_api.data.model.dto.LolUserDto
import com.api.study.riot_api.data.model.dto.MatchInformationDto
import com.api.study.riot_api.data.model.dto.MatchesId
import com.api.study.riot_api.data.network.retrofit.client.ClientRetrofit
import androidx.databinding.PropertyChangeRegistry
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import com.api.study.riot_api.data.model.dto.ErrorResponseDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LolMainViewModel : ViewModel() {
    private val _backButtonStatus = MutableLiveData<Boolean>()
    val backButtonStatus: LiveData<Boolean> = _backButtonStatus


    private val _lolMatchInformation =
        MutableLiveData<MatchInformationDto>()
    val lolMatchInformation: LiveData<MatchInformationDto> = _lolMatchInformation

    private val _lolUserInformation = MutableLiveData<LolUserDto>()
    val lolUserInformation: LiveData<LolUserDto> = _lolUserInformation

    private val _lolMatchsId = MutableLiveData<MatchesId>()
    val lolMatchsId: LiveData<MatchesId> = _lolMatchsId


    fun onclickBackButton() {
        _backButtonStatus.value = true
    }

    fun onClickSearchButton(name: String) {
        search(name)
    }

    private fun search(name: String) {
        ClientRetrofit.api.findUser(name).enqueue(object : Callback<LolUserDto> {
            override fun onResponse(call: Call<LolUserDto>, response: Response<LolUserDto>) {
                if (response.isSuccessful) {
                    _lolUserInformation.value = response.body()
                }
            }

            override fun onFailure(call: Call<LolUserDto>, t: Throwable) {
                Log.d("상태", t.message.toString())
            }

        })
    }

    fun getMatchId(page: Int, puuid: String?) {
        ClientRetrofit.api.matchId(puuid, page * 10, 10).enqueue(object : Callback<MatchesId> {
            override fun onResponse(call: Call<MatchesId>, response: Response<MatchesId>) {
                if (response.isSuccessful) {
                    _lolMatchsId.value = response.body()
                }
            }

            override fun onFailure(call: Call<MatchesId>, t: Throwable) {
                Log.d("getMatchId", t.message.toString())
            }

        })
    }


    fun getMatchInformation(matchId: String, puuid: String) {
        ClientRetrofit.api.matchInformation(matchId, puuid)
            .enqueue(object : Callback<MatchInformationDto> {
                override fun onResponse(
                    call: Call<MatchInformationDto>,
                    response: Response<MatchInformationDto>
                ) {
                    if (response.isSuccessful) {
                        _lolMatchInformation.value = response.body() // LiveData에 업데이트된 데이터 설정
                    }
                }

                override fun onFailure(call: Call<MatchInformationDto>, t: Throwable) {
                    Log.d("getMatchInformation", t.message.toString())
                }

            })
    }

}