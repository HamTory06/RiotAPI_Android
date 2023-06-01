package com.api.study.riot_api.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.api.study.riot_api.R
import com.api.study.riot_api.databinding.ActivityLolStatsSearchBinding
import com.api.study.riot_api.ui.adapter.ItemRecyclerViewClickEvent
import com.api.study.riot_api.ui.adapter.LOLRecyclerAdapter
import com.api.study.riot_api.viewModel.activity.LOLStatsSearchViewModel

class LOLStatsSearchActivity : AppCompatActivity(), ItemRecyclerViewClickEvent {

    companion object {
        lateinit var instance: LOLStatsSearchActivity
        fun ApplicationContext() : Context {
            return instance.applicationContext
        }
    }

    init{
        instance = this
    }

    private val binding: ActivityLolStatsSearchBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_lol_stats_search
        )
    }



    private val viewModel: LOLStatsSearchViewModel by lazy { ViewModelProvider(this)[LOLStatsSearchViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lolStatsSearch = viewModel
        binding.lifecycleOwner = this
        binding.LOLRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        viewModel.recycler.observe(this, Observer {
            var newAdapter = LOLRecyclerAdapter(it,this)
            binding.LOLRecyclerView.adapter = newAdapter
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

    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }
}