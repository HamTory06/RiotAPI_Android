package com.api.study.riot_api.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.api.study.riot_api.OnItemClickListener
import com.api.study.riot_api.R
import com.api.study.riot_api.databinding.FragmentLolStatsSearchBinding
import com.api.study.riot_api.ui.activity.LOLBaseActivity
import com.api.study.riot_api.ui.adapter.LOLRecyclerAdapter
import com.api.study.riot_api.viewModel.fragment.LOLStatsSearchViewModel


class LOLStatsSearchFragment : Fragment() {


    lateinit var binding: FragmentLolStatsSearchBinding

    private val viewModel by lazy {
        ViewModelProvider(this)[LOLStatsSearchViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLolStatsSearchBinding.inflate(inflater, container, false)
        binding.main = viewModel
        binding.lifecycleOwner = this
        binding.LOLRecyclerView.layoutManager =
            LinearLayoutManager(LOLBaseActivity.ApplicationContext())
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.recycler.observe(this, Observer {
            var newAdapter = LOLRecyclerAdapter(
                it,
                LOLBaseActivity.ApplicationContext(),
                object : OnItemClickListener {
                    override fun onClickchampionProfileIcon(item: Int) {
                        val bundle = Bundle()
                        bundle.putInt("position",item)
                        findNavController().navigate(R.id.action_LOLStatsSearchFragment_to_championInformationFragment,bundle)
                    }

                    override fun onClickItem(item: Int) {
                        val bundle = Bundle()
                        bundle.putInt("position",item)
                        findNavController().navigate(R.id.action_LOLStatsSearchFragment_to_matchInformationFragment,bundle)
                    }
                }
            )
            binding.LOLRecyclerView.adapter = newAdapter
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.main = viewModel
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}