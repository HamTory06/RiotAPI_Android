package com.api.study.riot_api.ui.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.api.study.riot_api.R
import com.api.study.riot_api.databinding.FragmentMainScreenBinding
import com.api.study.riot_api.viewModel.fragment.account.LoginViewModel

class MainScreenFragment : Fragment() {

    private lateinit var binding: FragmentMainScreenBinding
    private val mainScreenViewModel: LoginViewModel by lazy { ViewModelProvider(this)[mainScreenViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_screen, container, false)
        binding.mainScreen
        return binding.root
    }

}