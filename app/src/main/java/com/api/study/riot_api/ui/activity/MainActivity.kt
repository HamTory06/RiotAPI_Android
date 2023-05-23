package com.api.study.riot_api.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.api.study.riot_api.R
import com.api.study.riot_api.databinding.ActivityMainBinding
import com.api.study.riot_api.ui.adapter.MainRecyclerAdapter
import com.api.study.riot_api.viewModel.activity.MainViewModel

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var instance: MainActivity
        fun ApplicationContext() : Context {
            return instance.applicationContext
        }
    }

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
    }

    init{
        instance = this
    }

    private val viewModel: MainViewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.main = viewModel
        binding.lifecycleOwner = this
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
    }


    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        viewModel.recycler.observe(this, Observer {
            var newAdapter = MainRecyclerAdapter(it,this)
            binding.mainRecyclerView.adapter = newAdapter
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