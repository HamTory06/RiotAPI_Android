package com.api.study.riot_api.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.api.study.riot_api.R
import com.api.study.riot_api.databinding.ActivityValorantStatsSearchBinding
import com.api.study.riot_api.viewModel.activity.LOLStatsSearchViewModel
import com.api.study.riot_api.viewModel.activity.VALORANTStatsSearchViewModel

class VALORANTStatsSearchActivity : AppCompatActivity() {

    companion object {
        lateinit var instance: VALORANTStatsSearchActivity
        fun ApplicationContext() : Context {
            return instance.applicationContext
        }
    }

    init{
        instance = this
    }

    private val binding: ActivityValorantStatsSearchBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_valorant_stats_search
        )
    }

    private val viewModel: VALORANTStatsSearchViewModel by lazy { ViewModelProvider(this)[VALORANTStatsSearchViewModel::class.java] }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.valorantStatsSearch = viewModel
        binding.lifecycleOwner = this
    }

    override fun onStart() {
        super.onStart()
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