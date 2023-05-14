package com.api.study.riot_api.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.api.study.riot_api.R
import com.api.study.riot_api.data.network.retrofit.response.user_matches_response.User_matches_response

class Main_RecyclerAdapter(var data: ArrayList<User_matches_response>) :
    RecyclerView.Adapter<Main_RecyclerAdapter.MyViewHolder>() {
    inner class MyViewHolder constructor(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview_main, parent, false)
    ) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(parent)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("상태",data.toString())
    }

}