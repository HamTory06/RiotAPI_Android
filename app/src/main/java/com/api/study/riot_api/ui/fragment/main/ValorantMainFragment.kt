package com.api.study.riot_api.ui.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.api.study.riot_api.R
import com.api.study.riot_api.databinding.FragmentValorantMainBinding
import com.api.study.riot_api.ui.fragment.account.SignupFragmentDirections
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

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            onBackButtonClicked()
        }

        viewModel.backButtonStatus.observe(viewLifecycleOwner, Observer{
            if(it){
                onBackButtonClicked()
            }
        })

        return binding.root
    }

    private fun onBackButtonClicked() {
        val action = ValorantMainFragmentDirections.actionValorantMainFragmentToMainScreenFragment()
        findNavController().navigate(action)
    }
}