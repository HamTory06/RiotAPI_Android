package com.api.study.riot_api.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.api.study.riot_api.R
import com.api.study.riot_api.databinding.ActivityMainBinding
import com.api.study.riot_api.databinding.ActivitySplashScreenBinding
import com.api.study.riot_api.viewModel.activity.MainViewModel
import com.api.study.riot_api.viewModel.activity.SplashScreenViewModel

class SplashScreenActivity : AppCompatActivity() {
    private val binding: ActivitySplashScreenBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_splash_screen
        )
    }
    private val viewModel: SplashScreenViewModel by lazy { ViewModelProvider(this)[SplashScreenViewModel::class.java] }
    private val splashTimeOut: Long = 1500
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.splashscreen = viewModel
        binding.lifecycleOwner = this
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(this, AccountActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }, splashTimeOut)
    }
}