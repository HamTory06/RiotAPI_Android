package com.api.study.riot_api.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.api.study.riot_api.R
import com.api.study.riot_api.databinding.ActivityLoginBinding
import com.api.study.riot_api.databinding.ActivityMainBinding
import com.api.study.riot_api.viewModel.Login_ViewModel
import com.api.study.riot_api.viewModel.Main_ViewModel

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
    }
    private val viewModel: Main_ViewModel by lazy { ViewModelProvider(this)[Main_ViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.main = viewModel
        binding.lifecycleOwner = this
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        viewModel.userInformationData.observe(this, Observer {
            binding.accountId.text = it.accountId
            binding.id.text = it.id
            binding.name.text = it.name
            binding.profileIconId.text = it.profileIconId.toString()
            binding.puuid.text = it.puuid
            binding.revisionDate.text = it.revisionDate.toString()
            binding.summonerLevel.text = it.summonerLevel.toString()
        })
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}