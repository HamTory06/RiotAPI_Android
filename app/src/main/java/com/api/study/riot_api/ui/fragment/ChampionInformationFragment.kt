package com.api.study.riot_api.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.api.study.riot_api.databinding.FragmentLolChampionInformationBinding
import com.api.study.riot_api.viewModel.fragment.ChampionInformationViewModel

class ChampionInformationFragment : Fragment() {

    lateinit var binding: FragmentLolChampionInformationBinding

    private val viewModel by lazy {
        ViewModelProvider(this)[ChampionInformationViewModel::class.java]
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLolChampionInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.championInformation = viewModel
    }

}