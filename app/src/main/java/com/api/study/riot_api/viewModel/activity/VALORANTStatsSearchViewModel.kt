package com.api.study.riot_api.viewModel.activity

import android.util.Log
import androidx.lifecycle.ViewModel
import com.api.study.riot_api.data.App
import com.api.study.riot_api.data.network.retrofit.client.ClientRetrofit
import com.api.study.riot_api.data.network.retrofit.riot.RiotApi
import com.api.study.riot_api.data.network.retrofit.`val`.VALORANTapi
import com.api.study.riot_api.data.network.retrofit.`val`.response.valStatusResponse.ValStatusResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class VALORANTStatsSearchViewModel: ViewModel() {
    companion object {
        const val API_KEY = "RGAPI-a3858bd5-0dd4-44ea-9af6-5016712cb9b9"
    }

    private var krRetrofitInstance: VALORANTapi = ClientRetrofit.krGetInstance().create(VALORANTapi::class.java)
    private var asiarRetrofitInstance:  RiotApi = ClientRetrofit.AsiarGetInstance().create(RiotApi::class.java)
    private var ddragonGetInstance: RiotApi = ClientRetrofit.ddragonGetInstance().create(RiotApi::class.java)


    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    fun getCountry(){
        var data: ValStatusResponse
        CoroutineScope(Dispatchers.IO).async{
            data = krRetrofitInstance.get_val_status(API_KEY)
            App.prefs.locale = data.id
        }
    }

    fun setInputUserName(text: String) = viewModelScope.launch {

        CoroutineScope(Dispatchers.IO).async {
            val gameName = text.split("#")[0]
            val tagLine = text.split("#")[1]
            App.prefs.valpuuid = asiarRetrofitInstance.get_user_puuid(gameName, tagLine, API_KEY).puuid

            val data = async {
                krRetrofitInstance.get_matchId(App.prefs.valpuuid.toString(), API_KEY)
            }

            Log.d("상태",data.await().toString())
        }
    }
}