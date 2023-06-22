package com.api.study.riot_api.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.api.study.riot_api.R
import com.api.study.riot_api.data.network.retrofit.client.ClientRetrofit
import com.api.study.riot_api.data.network.retrofit.mainserver.request.LoginRequest
import com.api.study.riot_api.data.network.retrofit.mainserver.response.LoginResponse
import com.api.study.riot_api.databinding.ActivityLoginBinding
import com.api.study.riot_api.viewModel.activity.LoginViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountActivity : AppCompatActivity() {


    companion object {
        lateinit var instance: AccountActivity
        fun ApplicationContext() : Context {
            return instance.applicationContext
        }
    }

    init{
        AccountActivity.instance = this
    }

    private val binding: ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_login
        )
    }
    private val viewModel: LoginViewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.login = viewModel
        binding.lifecycleOwner = this



    }

    private fun login(id: String, password: String){
        ClientRetrofit.api.postLogin(LoginRequest(id, password)).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.isSuccessful){
                    Log.d("인터넷",response.body().toString())
                } else {
                    Log.d("인터넷",response.code().toString())
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("ERROR",t.message.toString())
            }
        })
    }
}