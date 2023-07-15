package com.api.study.riot_api.ui.fragment.account

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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
import com.api.study.riot_api.util.PatternUtils
import com.api.study.riot_api.viewModel.fragment.account.SignupViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupFragment : Fragment() {
    private lateinit var binding: FragmentSignupBinding
    private val viewModel: SignupViewModel by lazy { ViewModelProvider(this)[SignupViewModel::class.java] }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)
        binding.signup = viewModel
        binding.lifecycleOwner = this
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolBar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.sameIdCheckButtonStatus.observe(viewLifecycleOwner, Observer {
            click(binding.idEdittext.text.toString())
        })

        viewModel.signupButtonStatus.observe(viewLifecycleOwner, Observer {
            if (checkPassword(binding.passwordEdittext1.text.toString()) && checkId(binding.idEdittext.text.toString()) && binding.nameEdittext.text.toString()
                    .isNotEmpty()
            ) {
                signup(
                    id = binding.idEdittext.text.toString(),
                    name = binding.nameEdittext.text.toString(),
                    password = binding.passwordEdittext1.text.toString()
                )
            } else {
                passwordErrorMessage(Color.RED, "비밀번호가 다릅니다.")
            }
        })

        binding.toolBar.setNavigationOnClickListener {
            onBackButtonClicked()
        }
        return binding.root
    }

    private fun onBackButtonClicked() {
        //TODO(뒤로가기)
    }

    private fun checkPassword(password: String): Boolean {
        return PatternUtils.isPasswordValid(password)
    }

    private fun checkId(id: String): Boolean {
        return PatternUtils.isIdValid(id)
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
                        viewModel.idSameCheckStatus(false)
                        idErrorMessage(Color.RED, "존재하는 아이디입니다")
                    } else {
                        viewModel.idSameCheckStatus(true)
                        idErrorMessage(Color.GREEN, "성공")
                    }
                } else if (response.code() == 400) {
                    viewModel.idSameCheckStatus(false)
                    idErrorMessage(Color.RED, "아이디를 적어주세요")
                }
            }

            override fun onFailure(call: Call<CheckSameIdDto>, t: Throwable) {

            }

        })
    }

    private fun idErrorMessage(textColor: Int, text: String) {
        viewModel.changeIdErrorMessageTextViewColor(textColor)
        viewModel.idErrorTextView(text)
    }

    private fun passwordErrorMessage(textColor: Int, text: String) {
        viewModel.changePasswordErrorMessageTextViewColor(textColor)
        viewModel.passwordErrorTextView(text)
    }
}