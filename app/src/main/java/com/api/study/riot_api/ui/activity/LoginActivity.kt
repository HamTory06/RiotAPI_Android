package com.api.study.riot_api.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.api.study.riot_api.R
import com.api.study.riot_api.databinding.ActivityLoginBinding
import com.api.study.riot_api.viewModel.activity.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_login
        )
    }
    private val viewModel: LoginViewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }

    companion object {
        lateinit var instance: LoginActivity
        fun ApplicationContext() : Context {
            return instance.applicationContext
        }
    }

    init{
        LoginActivity.instance = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.login = viewModel
        binding.lifecycleOwner = this
    }
}