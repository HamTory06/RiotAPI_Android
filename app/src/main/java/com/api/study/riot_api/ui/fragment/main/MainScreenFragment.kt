package com.api.study.riot_api.ui.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.api.study.riot_api.R
import com.api.study.riot_api.databinding.FragmentMainScreenBinding
import com.api.study.riot_api.viewModel.fragment.main.MainScreenViewModel

class MainScreenFragment : Fragment() {

    private lateinit var binding: FragmentMainScreenBinding
    private val viewModel: MainScreenViewModel by lazy { ViewModelProvider(this)[MainScreenViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_screen, container, false)
        binding.mainScreen = viewModel
        binding.lifecycleOwner = this

        viewModel.lolButtonStatus.observe(viewLifecycleOwner) {
            val navController = requireActivity().findNavController(R.id.main_screen)
            navController.navigate(R.id.action_mainScreenFragment_to_lolMainFragment)
        }

        viewModel.valorantButtonStatus.observe(viewLifecycleOwner) {
            val navController = requireActivity().findNavController(R.id.main_screen)
            navController.navigate(R.id.action_mainScreenFragment_to_valorantMainFragment)
        }

        return binding.root
    }

//    fun lolScreen() {
//        navController.navigate(R.id.action_loginFragment_to_signupFragment)
//    }
//
//    fun valorantScreen() {
//        navController.navigate(R.id.action_loginFragment_to_signupFragment)
//    }

}