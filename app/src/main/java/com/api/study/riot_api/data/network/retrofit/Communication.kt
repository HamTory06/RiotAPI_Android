package com.api.study.riot_api.data.network.retrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.api.study.riot_api.data.network.retrofit.response.User_Information_response
import com.api.study.riot_api.data.repository.RiotApiData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class Communication {

    companion object {
        const val api_key = "RGAPI-f9035c09-8617-4247-92e1-023c40f5b83b"
    }

    fun GetUserInformation(username: String, riotAPIData: RiotApiData) {
        ClientRetrofit.api.get_user_information_name(username, api_key)
            .enqueue(object : Callback<User_Information_response> {
                override fun onResponse(
                    call: Call<User_Information_response>,
                    response: Response<User_Information_response>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()!!
                        riotAPIData.onRiotAPI_GetUser_Information(data)
                    } else {
                        Log.d("onResponse", "response.code : ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<User_Information_response>, t: Throwable) {
                    Log.d("onFailure", "${t.message}")
                }
            })
    }
}

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
        // 최종 URL을 확인할 수 있습니다.
        val url = request.url()
        Log.d("URL", url.toString())
        // 다른 작업을 수행할 수 있습니다.
        return chain.proceed(request)
    }
}