package com.api.study.riot_api.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.api.study.riot_api.R
import com.api.study.riot_api.databinding.ActivityLoginBinding
import com.api.study.riot_api.viewModel.Login_ViewModel

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_login
        )
    }
    private val viewModel: Login_ViewModel by lazy { ViewModelProvider(this)[Login_ViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.login = viewModel
        binding.lifecycleOwner = this
    }
}