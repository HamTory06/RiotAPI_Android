package com.api.study.riot_api.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.api.study.riot_api.R
import com.api.study.riot_api.databinding.FragmentChampionInformationBinding
import com.api.study.riot_api.databinding.FragmentUserInformationBinding
import com.api.study.riot_api.viewModel.fragment.UserInformationViewModel


class UserInformationFragment : Fragment() {

    lateinit var binding: FragmentUserInformationBinding

    private val viewModel by lazy {
        ViewModelProvider(this)[UserInformationViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.userInformation = viewModel
    }
}