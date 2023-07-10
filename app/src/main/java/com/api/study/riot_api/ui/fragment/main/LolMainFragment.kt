package com.api.study.riot_api.ui.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.api.study.riot_api.R
import com.api.study.riot_api.databinding.FragmentLolMainBinding
import com.api.study.riot_api.databinding.FragmentMainScreenBinding
import com.api.study.riot_api.viewModel.fragment.main.LolMainViewModel
import com.api.study.riot_api.viewModel.fragment.main.MainScreenViewModel


class LolMainFragment : Fragment() {

    private lateinit var binding: FragmentLolMainBinding
    private val viewModel: LolMainViewModel by lazy { ViewModelProvider(this)[LolMainViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lol_main, container, false)
        binding.lol = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

}