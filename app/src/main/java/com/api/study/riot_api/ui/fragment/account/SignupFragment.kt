package com.api.study.riot_api.ui.fragment.account

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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
            val idValid = checkId(binding.idEdittext.text.toString())
            val nameValid = checkName(binding.nameEdittext.text.toString())
            val passwordValid = checkPassword(binding.passwordEdittext1.text.toString())
            val passwordSameValid = checkPasswordSame(binding.passwordEdittext1.text.toString(), binding.passwordEdittext2.text.toString())

            if (!idValid && !nameValid && !passwordValid && passwordSameValid) {
                signup(
                    id = binding.idEdittext.text.toString(),
                    name = binding.nameEdittext.text.toString(),
                    password = binding.passwordEdittext1.text.toString()
                )
                passwordErrorMessage(Color.RED, "")
                idErrorMessage(Color.RED, "")
                nameErrorMessage("")
            } else {
            }
        })

        binding.toolBar.setNavigationOnClickListener {
            onBackButtonClicked()
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            onBackButtonClicked()
        }
        return binding.root
    }

    private fun onBackButtonClicked() {
        val action = SignupFragmentDirections.actionSignupFragmentToLoginFragment()
        findNavController().navigate(action)
    }

    private fun checkPassword(password: String): Boolean {
        if (PatternUtils.isPasswordValid(password)) {
            passwordErrorMessage(Color.RED, "대소문자 특수문자 포함해서 적어주세요")
        } else {
            passwordErrorMessage(Color.RED, "")
        }
        return PatternUtils.isPasswordValid(password)
    }

    private fun checkPasswordSame(password1: String, password2: String): Boolean{
        if(password2 != password1){
            passwordErrorMessage(Color.RED, "비밀번호가 같은지 확인해주세요")
        }
        return password1 == password2
    }

    private fun checkId(id: String): Boolean {
        if (PatternUtils.isIdValid(id)) {
            idErrorMessage(Color.RED, "다시 적어주세요")
        } else {
            idErrorMessage(Color.RED, "")
        }
        return PatternUtils.isIdValid(id)
    }

    private fun checkName(name: String): Boolean {
        if (name.isEmpty()) {
            nameErrorMessage("이름이 비어있습니다")
        } else {
            nameErrorMessage("")
        }
        return name.isEmpty()
    }


    private fun signup(id: String, name: String, password: String) {
        ClientRetrofit.api.signup(SignupRequestDto(id = id, name = name, password = password))
            .enqueue(object : Callback<Void> {
                override fun onResponse(
                    call: Call<Void>, response: Response<Void>
                ) {
                    if (response.isSuccessful) {
                        val navController = requireActivity().findNavController(R.id.account_screen)
                        navController.navigate(R.id.action_signupFragment_to_loginFragment)
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
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
                        idErrorMessage(Color.RED, "존재하는 아이디입니다")
                    } else {
                        idErrorMessage(Color.GREEN, "성공")
                    }
                } else if (response.code() == 400) {
                    idErrorMessage(Color.RED, "아이디를 적어주세요")
                }
            }

            override fun onFailure(call: Call<CheckSameIdDto>, t: Throwable) {

            }

        })
    }

    fun idErrorMessage(textColor: Int, text: String) {
        viewModel.changeIdErrorMessageTextColor(textColor)
        viewModel.idErrorText(text)
    }

    fun passwordErrorMessage(textColor: Int, text: String) {
        viewModel.changePasswordErrorMessageTextColor(textColor)
        viewModel.passwordErrorText(text)
    }

    fun nameErrorMessage(text: String) {
        viewModel.nameErrorText(text)
    }

}