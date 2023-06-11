package com.api.study.riot_api.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.api.study.riot_api.R
import com.api.study.riot_api.databinding.ActivityLoginBinding
import com.api.study.riot_api.databinding.ActivityLolbaseBinding
import com.api.study.riot_api.viewModel.activity.LOLBaseViewModel
import com.api.study.riot_api.viewModel.activity.LoginViewModel

class LOLBaseActivity : AppCompatActivity() {

    companion object {
        lateinit var instance: LOLBaseActivity
        fun ApplicationContext() : Context {
            return instance.applicationContext
        }
    }

    init{
        instance = this
    }


    private val binding: ActivityLolbaseBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_lolbase
        )
    }

    private val viewModel: LOLBaseViewModel by lazy { ViewModelProvider(this)[LOLBaseViewModel::class.java] }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.base = viewModel
        binding.lifecycleOwner = this
    }
}