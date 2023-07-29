package com.api.study.riot_api.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.api.study.riot_api.data.model.dto.MatchInformationDto
import com.api.study.riot_api.databinding.ItemRecyclerviewLolBinding

class LolMatchListAdapter(private val matchList: List<MatchInformationDto>): RecyclerView.Adapter<LolMatchListAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemRecyclerviewLolBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerviewLolBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = matchList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding
    }
}