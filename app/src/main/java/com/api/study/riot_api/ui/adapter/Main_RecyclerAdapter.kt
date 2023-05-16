package com.api.study.riot_api.ui.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.api.study.riot_api.R
import com.api.study.riot_api.data.model.MySharedPreferences
import com.api.study.riot_api.data.network.retrofit.response.user_matches_response.User_matches_response
import com.api.study.riot_api.databinding.ItemRecyclerviewMainBinding
import com.api.study.riot_api.ui.activity.MainActivity
import com.bumptech.glide.Glide

class Main_RecyclerAdapter(var data: ArrayList<User_matches_response>, val context: Context) :
    RecyclerView.Adapter<Main_RecyclerAdapter.MyViewHolder>() {
    class MyViewHolder (private val binding: ItemRecyclerviewMainBinding) : RecyclerView.ViewHolder(binding.root) {
        var Champion_Profile_icon = binding.ChampionProfileIcon
        var Champion_level = binding.level
        var item = binding.item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemRecyclerviewMainBinding = DataBindingUtil.inflate(inflater, R.layout.item_recyclerview_main, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        for(i in 0..9){
            if(data[position].info.participants[i].puuid == MySharedPreferences(MainActivity.ApplicationContext()).puuid){
                Glide.with(context)
                    .load("https://ddragon.leagueoflegends.com/cdn/13.9.1/img/champion/${data[position].info.participants[i].championName}.png")
                    .into(holder.Champion_Profile_icon)

                holder.Champion_level.text = data[position].info.participants[i].champLevel.toString()

                if(data[position].info.participants[i].win){
                    holder.item.setBackgroundColor(Color.rgb(39,52,79))
                } else {
                    holder.item.setBackgroundColor(Color.rgb(89,52,59))
                }
            }
        }



    }

}