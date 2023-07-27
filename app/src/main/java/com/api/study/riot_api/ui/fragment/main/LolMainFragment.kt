package com.api.study.riot_api.ui.fragment.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.api.study.riot_api.R
import com.api.study.riot_api.data.model.dto.LolUserDto
import com.api.study.riot_api.data.model.dto.MatchInformationDto
import com.api.study.riot_api.data.model.dto.MatchesId
import com.api.study.riot_api.data.network.retrofit.client.ClientRetrofit
import com.api.study.riot_api.databinding.FragmentLolMainBinding
import com.api.study.riot_api.ui.adapter.LolMatchListAdapter
import com.api.study.riot_api.viewModel.fragment.main.LolMainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LolMainFragment : Fragment() {

    private lateinit var binding: FragmentLolMainBinding
    private val viewModel: LolMainViewModel by lazy { ViewModelProvider(this)[LolMainViewModel::class.java] }

    private lateinit var adapter: LolMatchListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lol_main, container, false)
        binding.lol = viewModel
        binding.lifecycleOwner = this
//        binding.matchListRecyclerview.layoutManager = LinearLayoutManager(activity)


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            backFragment()
        }

        viewModel.backButtonStatus.observe(viewLifecycleOwner, Observer {
            if (it) {
                backFragment()
            }
        })

        viewModel.searchButtonStatus.observe(viewLifecycleOwner, Observer {
            val name = binding.searchEdittext.text.toString()
            if (it) {
                search(name)
            }
        })

        return binding.root
    }

    private fun backFragment() {
        val action = LolMainFragmentDirections.actionLolMainFragmentToMainScreenFragment()
        findNavController().navigate(action)
    }

    private fun search(name: String) {
        ClientRetrofit.api.findUser(name).enqueue(object : Callback<LolUserDto> {
            override fun onResponse(call: Call<LolUserDto>, response: Response<LolUserDto>) {
                if (response.isSuccessful) {
                    val puuid = response.body()?.lolUserPuuId.toString()
                    getMatchId(0, puuid)
                } else {
                    Log.d("search", response.code().toString())
                }
            }

            override fun onFailure(call: Call<LolUserDto>, t: Throwable) {
                Log.d("상태", t.message.toString())
            }

        })
    }

    private fun getMatchId(page: Int, puuid: String?) {
        ClientRetrofit.api.matchId(puuid, page * 10, 10).enqueue(object : Callback<MatchesId> {
            override fun onResponse(call: Call<MatchesId>, response: Response<MatchesId>) {
                if (response.isSuccessful) {
                    Log.d("getMatchId", response.body().toString())
                    for(matchId in response.body()!!){
                        getMatchInformation(matchId)
                    }
                } else {
                    Log.d("getMatchId", response.code().toString())
                }
            }

            override fun onFailure(call: Call<MatchesId>, t: Throwable) {
                Log.d("getMatchId", t.message.toString())
            }

        })
    }


    private fun getMatchInformation(matchId: String) {
        ClientRetrofit.api.matchInformation(matchId)
            .enqueue(object : Callback<MatchInformationDto> {
                override fun onResponse(
                    call: Call<MatchInformationDto>,
                    response: Response<MatchInformationDto>
                ) {
                    if (response.isSuccessful) {
                        Log.d("getMatchInformation", response.body().toString())
                    } else {
                        Log.d("getMatchInformation", response.code().toString())
                    }
                }

                override fun onFailure(call: Call<MatchInformationDto>, t: Throwable) {
                    Log.d("getMatchInformation", t.message.toString())
                }

            })

    }


    private fun toastMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

}