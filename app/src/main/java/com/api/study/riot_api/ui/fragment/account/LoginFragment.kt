package com.api.study.riot_api.ui.fragment.account

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.api.study.riot_api.R
import com.api.study.riot_api.data.model.dto.LoginRequestDto
import com.api.study.riot_api.data.network.retrofit.client.ClientRetrofit
import com.api.study.riot_api.databinding.FragmentLoginBinding
import com.api.study.riot_api.viewModel.fragment.account.LoginViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.login = loginViewModel // LoginViewModel을 바인딩
        binding.lifecycleOwner = this // lifecycleOwner 설정
        return binding.root
    }

    private fun login(id: String, password: String){
        ClientRetrofit.api.login(LoginRequestDto(id, password)).enqueue(object :
            Callback<LoginRequestDto> {
            override fun onResponse(call: Call<LoginRequestDto>, response: Response<LoginRequestDto>) {
                if(response.isSuccessful){
                    Log.d("인터넷",response.body().toString())
                } else {
                    Log.d("인터넷",response.code().toString())
                }
            }

            override fun onFailure(call: Call<LoginRequestDto>, t: Throwable) {
                Log.d("ERROR",t.message.toString())
            }
        })
    }
}