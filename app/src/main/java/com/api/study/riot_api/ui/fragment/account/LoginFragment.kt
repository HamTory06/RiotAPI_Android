package com.api.study.riot_api.ui.fragment.account

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.api.study.riot_api.R
import com.api.study.riot_api.data.model.dto.loginDto.Request.LoginRequestDto
import com.api.study.riot_api.data.model.dto.loginDto.Response.LoginResponseDto
import com.api.study.riot_api.data.network.retrofit.client.ClientRetrofit
import com.api.study.riot_api.databinding.FragmentLoginBinding
import com.api.study.riot_api.ui.activity.MainActivity
import com.api.study.riot_api.viewModel.fragment.account.LoginViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    val viewModel: LoginViewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.login = viewModel
        binding.lifecycleOwner = this

        viewModel.loginButtonEvent.observe(viewLifecycleOwner) {
            val password: String = binding.passwordTextview.text.toString()
            val id: String = binding.idTextview.text.toString()
            login(id, password)
        }

        viewModel.signupButtonStatus.observe(viewLifecycleOwner, Observer { clicked ->
            if (clicked) {
                findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
            }
        })

        return binding.root
    }


    private fun login(id: String, password: String) {
        ClientRetrofit.api.login(
            LoginRequestDto(
                id, password
            )
        ).enqueue(object : Callback<LoginResponseDto> {
            override fun onResponse(
                call: Call<LoginResponseDto>, response: Response<LoginResponseDto>
            ) {
                if (response.isSuccessful) {
                    loginButton()
                } else {
                    viewModel.loginErrorMessage("비밀번호나 아이디가 틀렸습니다.")
                }
            }

            override fun onFailure(call: Call<LoginResponseDto>, t: Throwable) {
                Log.d("ERROR", t.message.toString())
            }
        })
    }

    private fun loginButton(){
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }


}