package com.api.study.riot_api.ui.fragment.account

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.api.study.riot_api.R
import com.api.study.riot_api.data.model.dto.CheckSameIdDto
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
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolBar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)

        signupViewModel.sameIdCheckButtonStatus.observe(viewLifecycleOwner, Observer {
            if (binding.idEdittext.text.toString() != "") {
                click(binding.idEdittext.text.toString())
            } else {
                signupViewModel.idSameCheckStatus(false)
                signupViewModel.idErrorTextView("아이디를 입력해주세요")
            }
        })

        signupViewModel.signupButtonStatus.observe(viewLifecycleOwner, Observer {
            if (binding.passwordEdittext1.text.toString() == binding.passwordEdittext2.text.toString()) {
                signupViewModel.passwordSameCheckStatus(true)
                signupViewModel.passwordErrorTextView("")
                if (signupViewModel.idSameCheckStatus.value == true && signupViewModel.passwordSameCheckStatus.value == true) {
                    if (binding.nameEdittext.text.isNullOrEmpty()) {
                        signupViewModel.nameNullErrorTextView("이름을 입력해주세요.")
                    } else {
                        signup(
                            id = binding.idEdittext.text.toString(),
                            name = binding.nameEdittext.text.toString(),
                            password = binding.passwordEdittext1.text.toString()
                        )
                    }
                } else {

                    if (signupViewModel.idSameCheckStatus.value == true && signupViewModel.passwordSameCheckStatus.value != true) {
                        signupViewModel.passwordErrorTextView("비밀번호가 중복인지 확인 해주세요")
                    } else if (signupViewModel.idSameCheckStatus.value != true && signupViewModel.passwordSameCheckStatus.value == true) {
                        signupViewModel.idErrorTextView("아이디 중복채크를 해주세요.")
                    } else {
                        signupViewModel.passwordErrorTextView("비밀번호가 중복인지 확인 해주세요")
                        signupViewModel.idErrorTextView("아이디 중복채크를 해주세요.")
                    }
                }
            } else {
                signupViewModel.passwordErrorTextView("비밀번호가 중복인지 확인 해주세요")
                signupViewModel.passwordSameCheckStatus(false)
            }
        })

        binding.toolBar.setNavigationOnClickListener {
            onBackButtonClicked()
        }
        return binding.root
    }

    private fun onBackButtonClicked() {
        // 뒤로가기 버튼을 클릭했을 때 수행할 동작을 여기에 작성합니다.
        requireActivity().onBackPressed()
    }


    private fun signup(id: String, name: String, password: String) {
        ClientRetrofit.api.signup(SignupRequestDto(id = id, name = name, password = password))
            .enqueue(object : Callback<SignupRequestDto> {
                override fun onResponse(
                    call: Call<SignupRequestDto>,
                    response: Response<SignupRequestDto>
                ) {
                    if (response.isSuccessful) {
                        val navController = requireActivity().findNavController(R.id.account_screen)
                        navController.navigate(R.id.action_signupFragment_to_loginFragment)
                    }
                }

                override fun onFailure(call: Call<SignupRequestDto>, t: Throwable) {

                }
            })
    }


    private fun click(id: String) {
        ClientRetrofit.api.checkSameId(id).enqueue(object : Callback<CheckSameIdDto> {
            override fun onResponse(
                call: Call<CheckSameIdDto>,
                response: Response<CheckSameIdDto>
            ) {
                if (response.isSuccessful) {
                    if (response.body()?.sameId != false) {
                        Log.d("아이디 중복 채크", response.body()?.sameId.toString())
                        signupViewModel.idSameCheckStatus(false)
                        signupViewModel.idErrorTextView("이미 존재하는 아이디입니다")
                    } else {
                        Log.d("아이디 중복 채크", response.body()?.sameId.toString())
                        signupViewModel.idSameCheckStatus(true)
                        signupViewModel.idErrorTextView("")
                    }
                } else {
                    signupViewModel.idSameCheckStatus(false)
                    signupViewModel.idErrorTextView("이미 존재하는 아이디입니다")
                    Log.d("실패", response.code().toString())
                }
            }

            override fun onFailure(call: Call<CheckSameIdDto>, t: Throwable) {

            }

        })
    }
}