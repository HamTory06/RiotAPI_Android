package com.api.study.riot_api.ui.fragment

import android.os.Bundle
import android.util.Log
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
        val bundle = arguments
        if (bundle != null) {
            // 데이터를 추출하여 변수에 할당
            val value = bundle.getInt("position")
            Log.d("상태",value.toString())
            // 변수를 사용하여 작업 수행
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.matchInformation = viewModel
    }
}