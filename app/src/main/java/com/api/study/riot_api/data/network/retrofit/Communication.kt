package com.api.study.riot_api.data.network.retrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.api.study.riot_api.data.network.retrofit.response.User_Information_response
import okhttp3.Interceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Communication {

    companion object{
        const val api_key = "RGAPI-f9035c09-8617-4247-92e1-023c40f5b83b"
    }

    fun GetUserInformation(username: String): MutableLiveData<User_Information_response>{
        val data = MutableLiveData<User_Information_response>()
        ClientRetrofit.api.get_user_information_name(username, api_key).enqueue(object : Callback<User_Information_response>{
            override fun onResponse(
                call: Call<User_Information_response>,
                response: Response<User_Information_response>
            ) {
                if(response.isSuccessful){
                    data.value = response.body()
//                    Log.d("상태","${data.value}")
                } else {
                    Log.d("상태","response.code : ${response.code()}")
                }
            }

            override fun onFailure(call: Call<User_Information_response>, t: Throwable) {
                Log.d("상태","${t.message}")
            }
        })

        return data
    }
}

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()

        // 최종 URL을 확인할 수 있습니다.
        val url = request.url()
        Log.d("상태",url.toString())

        // 다른 작업을 수행할 수 있습니다.

        return chain.proceed(request)
    }
}