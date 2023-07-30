package com.api.study.riot_api.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.api.study.riot_api.R
import com.api.study.riot_api.databinding.ActivityMainBinding
import com.api.study.riot_api.viewModel.activity.MainViewModel

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
    }
    private val viewModel: MainViewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    companion object {
        lateinit var instance: MainActivity
            private set
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this // instance 변수를 현재 MainActivity로 초기화
        binding.main = viewModel
        binding.lifecycleOwner = this
    }

}