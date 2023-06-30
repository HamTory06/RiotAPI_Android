package com.api.study.riot_api.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.api.study.riot_api.R
import com.api.study.riot_api.databinding.ActivityAccountBinding
import com.api.study.riot_api.viewModel.activity.AccountViewModel

class AccountActivity : AppCompatActivity() {


    companion object {
        lateinit var instance: AccountActivity
        fun ApplicationContext() : Context {
            return instance.applicationContext
        }
    }

    init{
        AccountActivity.instance = this
    }

    private val binding: ActivityAccountBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_account
        )
    }
    private val viewModel: AccountViewModel by lazy { ViewModelProvider(this)[AccountViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.account = viewModel
        binding.lifecycleOwner = this

    }
}