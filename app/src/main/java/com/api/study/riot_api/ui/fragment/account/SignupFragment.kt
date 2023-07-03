package com.api.study.riot_api.ui.fragment.account

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.api.study.riot_api.R
import com.api.study.riot_api.data.model.dto.signupDto.request.SignupRequestDto
import com.api.study.riot_api.data.network.retrofit.client.ClientRetrofit
import com.api.study.riot_api.databinding.FragmentSignupBinding
import com.api.study.riot_api.viewModel.fragment.account.SignupViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupFragment : Fragment() {
    private lateinit var binding: FragmentSignupBinding
    private val signupViewModel: SignupViewModel by lazy { ViewModelProvider(this)[SignupViewModel::class.java] }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)
        binding.signup = signupViewModel // LoginViewModel을 바인딩
        binding.lifecycleOwner = this // lifecycleOwner 설정

        signupViewModel.signupButtonStatus.observe(viewLifecycleOwner, Observer {
            signup(id = binding.idEdittext.text.toString(), name = binding.nameEdittext1.text.toString(), password = binding.passwordEdittext1.text.toString())
        })
        return binding.root
    }

    private fun signup(id: String, name: String, password: String){
        ClientRetrofit.api.signup(SignupRequestDto(id = id, name = name, password = password)).enqueue(object : Callback<SignupRequestDto> {
            override fun onResponse(
                call: Call<SignupRequestDto>,
                response: Response<SignupRequestDto>
            ) {
                if(response.isSuccessful){
                    Log.d("애러",response.code().toString())

                } else {
                    Log.d("애러",response.code().toString())
                    Log.d("애러","password: $password, id: $id, name: $name")
                }
            }

            override fun onFailure(call: Call<SignupRequestDto>, t: Throwable) {
                Log.d("애러","${t.message}")
            }

        })
    }
}