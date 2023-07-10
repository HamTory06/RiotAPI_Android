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
import androidx.navigation.findNavController
import com.api.study.riot_api.R
import com.api.study.riot_api.data.model.dto.loginDto.Request.LoginRequestDto
import com.api.study.riot_api.data.model.dto.loginDto.Response.LoginResponseDto
import com.api.study.riot_api.data.network.retrofit.client.ClientRetrofit
import com.api.study.riot_api.databinding.FragmentLoginBinding
import com.api.study.riot_api.ui.activity.MainActivity
import com.api.study.riot_api.util.PasswordUtils
import com.api.study.riot_api.viewModel.fragment.account.LoginViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.login = viewModel
        binding.lifecycleOwner = this

        viewModel.loginButtonEvent.observe(viewLifecycleOwner) {
            viewModel.setIdErrorMessage("")
            viewModel.setPasswordErrorMessage("")
            if (PasswordUtils.isPasswordValid(binding.passwordTextview.text.toString()) && binding.idTextview.text.toString()
                    .isNotEmpty()
            ) {
                login(binding.idTextview.text.toString(), binding.passwordTextview.text.toString())
            } else {
                if (!PasswordUtils.isPasswordValid(binding.passwordTextview.text.toString()) && binding.idTextview.text.toString()
                        .isEmpty()
                ) {
                    //TODO(id가 비었을때 password는 되어있고)
                    viewModel.setIdErrorMessage("아이디를 적어 주세요.")
                } else if (PasswordUtils.isPasswordValid(binding.passwordTextview.text.toString()) && binding.idTextview.text.toString()
                        .isNotEmpty()
                ) {
                    //TODO(id는 적혀 있고 password는 패턴에 맞지 않거나 비어있을때)
                    viewModel.setPasswordErrorMessage("비밀번호를 적어 주세요.\n대소문자,숫자,특수문자 포함")
                } else {
                    //TODO(id도 비어있고 password도 패턴에 일치하지 않으면)
                    viewModel.setIdErrorMessage("아이디를 적어 주세요.")
                    viewModel.setPasswordErrorMessage("비밀번호를 적어 주세요.\n(대소문자,숫자,특수문자 포함)")
                }
            }
        }

        viewModel.signupButtonStatus.observe(viewLifecycleOwner, Observer { clicked ->
            if (clicked) {
                val navController = requireActivity().findNavController(R.id.account_screen)
                navController.navigate(R.id.action_loginFragment_to_signupFragment)
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
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                }
            }

            override fun onFailure(call: Call<LoginResponseDto>, t: Throwable) {
                Log.d("ERROR", t.message.toString())
            }
        })
    }

}