package com.api.study.riot_api.ui.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.api.study.riot_api.R
import com.api.study.riot_api.databinding.FragmentValorantMainBinding
import com.api.study.riot_api.viewModel.fragment.main.ValorantMainViewModel


class ValorantMainFragment : Fragment() {

    private lateinit var binding: FragmentValorantMainBinding
    private val viewModel: ValorantMainViewModel by lazy { ViewModelProvider(this)[ValorantMainViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_valorant_main, container, false)
        binding.valorant = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}