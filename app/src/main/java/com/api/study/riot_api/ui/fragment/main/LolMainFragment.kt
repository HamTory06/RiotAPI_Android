package com.api.study.riot_api.ui.fragment.main

import android.os.Bundle
import android.util.Log
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
import com.api.study.riot_api.data.model.dto.LolUserDto
import com.api.study.riot_api.data.network.retrofit.client.ClientRetrofit
import com.api.study.riot_api.databinding.FragmentLolMainBinding
import com.api.study.riot_api.viewModel.fragment.main.LolMainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            backFragment()
        }

        viewModel.backButtonStatus.observe(viewLifecycleOwner, Observer{
            if(it){
                backFragment()
            }
        })

        viewModel.searchButtonStatus.observe(viewLifecycleOwner, Observer {
            if(it){
                Log.d("상태","클릭")
                val name = binding.searchEdittext.text.toString()
                search(name)
            }
        })

        return binding.root
    }
    private fun backFragment() {
        val action = LolMainFragmentDirections.actionLolMainFragmentToMainScreenFragment()
        findNavController().navigate(action)
    }

    private fun search(name: String){
        ClientRetrofit.api.findUser(name).enqueue(object : Callback<LolUserDto>{
            override fun onResponse(call: Call<LolUserDto>, response: Response<LolUserDto>) {
                if(response.isSuccessful){
                    Log.d("상태",response.body().toString())

                } else {
                    Log.d("상태",response.code().toString())

                }
            }

            override fun onFailure(call: Call<LolUserDto>, t: Throwable) {

            }

        })
    }

}