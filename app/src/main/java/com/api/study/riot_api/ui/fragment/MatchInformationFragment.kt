package com.api.study.riot_api.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.api.study.riot_api.databinding.FragmentLolMatchInformationBinding
import com.api.study.riot_api.viewModel.fragment.MatchInformationViewModel


class MatchInformationFragment : Fragment() {

    lateinit var binding: FragmentLolMatchInformationBinding

    private val viewModel: MatchInformationViewModel by lazy {
        ViewModelProvider(this)[MatchInformationViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLolMatchInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.matchInformation = viewModel
    }
}