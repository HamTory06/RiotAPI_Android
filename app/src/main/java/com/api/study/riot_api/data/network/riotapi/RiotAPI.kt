package com.api.study.riot_api.data.network.riotapi

import android.util.Log
import android.widget.ImageView

import com.api.study.riot_api.ui.activity.MainActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class RiotAPI {

    val requestOptions = RequestOptions()
        .transform(RoundedCorners(10))
    fun getImage_Champion_Profile_icon(binding: ImageView, ID: String, version: String) {
        Glide.with(MainActivity.ApplicationContext())
            .load("https://ddragon.leagueoflegends.com/cdn/13.10.1/img/champion/${ID}.png")
            .into(binding)
    }

    fun getImage_Champion_spell(binding: ImageView, ID: String, version: String) {
        Glide.with(MainActivity.ApplicationContext())
            .load("https://ddragon.leagueoflegends.com/cdn/${version}/img/spell/${ID}.png")
            .apply(requestOptions)
            .into(binding)
    }

    fun getImage_Champion_item(binding: ImageView, ID: String, version: String) {
        Log.d("상태","https://ddragon.leagueoflegends.com/cdn/${version}/img/item/${ID}.png")
        Glide.with(MainActivity.ApplicationContext())
            .load("https://ddragon.leagueoflegends.com/cdn/${version}/img/item/${ID}.png")
            .apply(requestOptions)
            .into(binding)
    }


}