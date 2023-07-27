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
import androidx.recyclerview.widget.LinearLayoutManager
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

    private var matchInformationData: MutableList<MatchInformationDto> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lol_main, container, false)
        binding.lol = viewModel
        binding.lifecycleOwner = this
        binding.matchListRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        adapter = LolMatchListAdapter(matchInformationData)
        binding.matchListRecyclerview.adapter = adapter


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            backFragment()
        }

        viewModel.backButtonStatus.observe(viewLifecycleOwner, Observer {
            if (it) {
                backFragment()
            }
        })

        viewModel.lolUserInformation.observe(viewLifecycleOwner, Observer {
            viewModel.getMatchId(0, it.lolUserPuuId)
        })

        viewModel.lolMatchsId.observe(viewLifecycleOwner, Observer {
            for(matchId in it){
                viewModel.getMatchInformation(matchId)
            }
        })

        viewModel.lolMatchInformation.observe(viewLifecycleOwner, Observer {
            matchInformationData.add(it)
            adapter.notifyDataSetChanged()
        })



        return binding.root
    }

    private fun backFragment() {
        val action = LolMainFragmentDirections.actionLolMainFragmentToMainScreenFragment()
        findNavController().navigate(action)
    }






    private fun toastMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

}