package com.api.study.riot_api.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.api.study.riot_api.R
import com.api.study.riot_api.data.model.MySharedPreferences
import com.api.study.riot_api.data.network.retrofit.response.user_matches_response.UserMatchesResponse
import com.api.study.riot_api.data.network.riotapi.RiotAPI
import com.api.study.riot_api.databinding.ItemRecyclerviewMainBinding
import com.api.study.riot_api.ui.activity.MainActivity

class MainRecyclerAdapter(var data: ArrayList<UserMatchesResponse>, val context: Context) :
    RecyclerView.Adapter<MainRecyclerAdapter.MyViewHolder>() {

    private val riotAPI = RiotAPI()

    class MyViewHolder(private val binding: ItemRecyclerviewMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var championProfileIcon = binding.ChampionProfileIcon
        var championLevel = binding.level
        var item = binding.item
        var championSpellId1 = binding.ChampionSpellId1
        var championSpellId2 = binding.ChampionSpellId2
        var championItemId0 = binding.ChampionSpellItem0
        var championItemId1 = binding.ChampionSpellItem1
        var championItemId2 = binding.ChampionSpellItem2
        var championItemId3 = binding.ChampionSpellItem3
        var championItemId4 = binding.ChampionSpellItem4
        var championItemId5 = binding.ChampionSpellItem5
        var championItemId6 = binding.ChampionSpellItem6
        val championKill = binding.K
        val championDeath = binding.D
        val championAssist = binding.A
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemRecyclerviewMainBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_recyclerview_main, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val version = MySharedPreferences(MainActivity.ApplicationContext()).version.toString()

        for (i in 0..9) {
            if (data[position].info.participants[i].puuid == MySharedPreferences(MainActivity.ApplicationContext()).puuid) {
                when (data[position].info.participants[i].summoner1Id.toString()) {
                    "21" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId1,
                        "SummonerBarrier",
                        version
                    )

                    "1" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId2,
                        "SummonerBoost",
                        version
                    )

                    "14" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId1,
                        "SummonerDot",
                        version
                    )

                    "3" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId1,
                        "SummonerExhaust",
                        version
                    )

                    "4" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId1,
                        "SummonerFlash",
                        version
                    )

                    "6" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId1,
                        "SummonerHaste",
                        version
                    )

                    "7" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId1,
                        "SummonerHeal",
                        version
                    )

                    "13" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId1,
                        "SummonerMana",
                        version
                    )

                    "30" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId1,
                        "SummonerPoroRecall",
                        version
                    )

                    "32" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId1,
                        "SummonerSnowball",
                        version
                    )

                    "11" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId1,
                        "SummonerSmite",
                        version
                    )

                    "31" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId1,
                        "SummonerPoroThrow",
                        version
                    )

                    "39" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId1,
                        "SummonerSnowball",
                        version
                    )

                    "12" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId1,
                        "SummonerTeleport",
                        version
                    )

                    "54" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId1,
                        "Summoner_UltBookPlaceholder",
                        version
                    )

                    "55" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId1,
                        "Summoner_UltBookSmitePlaceholder",
                        version
                    )
                }

                when (data[position].info.participants[i].summoner2Id.toString()) {
                    "21" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId2,
                        "SummonerBarrier",
                        version
                    )

                    "1" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId2,
                        "SummonerBoost",
                        version
                    )

                    "14" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId2,
                        "SummonerDot",
                        version
                    )

                    "3" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId2,
                        "SummonerExhaust",
                        version
                    )

                    "4" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId2,
                        "SummonerFlash",
                        version
                    )

                    "6" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId2,
                        "SummonerHaste",
                        version
                    )

                    "7" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId2,
                        "SummonerHeal",
                        version
                    )

                    "13" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId2,
                        "SummonerMana",
                        version
                    )

                    "30" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId2,
                        "SummonerPoroRecall",
                        version
                    )

                    "32" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId2,
                        "SummonerSmite",
                        version
                    )

                    "11" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId2,
                        "SummonerSnowURFSnowball_Mark",
                        version
                    )

                    "31" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId2,
                        "SummonerPoroThrow",
                        version
                    )

                    "39" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId2,
                        "SummonerSnowball",
                        version
                    )

                    "12" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId2,
                        "SummonerTeleport",
                        version
                    )

                    "54" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId2,
                        "Summoner_UltBookPlaceholder",
                        version
                    )

                    "55" -> riotAPI.getImage_Champion_spell(
                        holder.championSpellId2,
                        "Summoner_UltBookSmitePlaceholder",
                        version
                    )
                }

                riotAPI.getImage_Champion_Profile_icon(
                    holder.championProfileIcon,
                    data[position].info.participants[i].championName,
                    version
                )
                riotAPI.getImage_Champion_item(
                    holder.championItemId0,
                    data[position].info.participants[i].item0.toString(),
                    version
                )
                riotAPI.getImage_Champion_item(
                    holder.championItemId1,
                    data[position].info.participants[i].item1.toString(),
                    version
                )
                riotAPI.getImage_Champion_item(
                    holder.championItemId2,
                    data[position].info.participants[i].item2.toString(),
                    version
                )
                riotAPI.getImage_Champion_item(
                    holder.championItemId3,
                    data[position].info.participants[i].item3.toString(),
                    version
                )
                riotAPI.getImage_Champion_item(
                    holder.championItemId4,
                    data[position].info.participants[i].item4.toString(),
                    version
                )
                riotAPI.getImage_Champion_item(
                    holder.championItemId5,
                    data[position].info.participants[i].item5.toString(),
                    version
                )
                riotAPI.getImage_Champion_item(
                    holder.championItemId6,
                    data[position].info.participants[i].item6.toString(),
                    version
                )

                holder.championLevel.text =
                    data[position].info.participants[i].champLevel.toString()

                holder.championKill.text = data[position].info.participants[i].kills.toString()
                holder.championDeath.text = data[position].info.participants[i].deaths.toString()
                holder.championAssist.text = data[position].info.participants[i].assists.toString()


                if (data[position].info.participants[i].win && data[position].info.participants[i].champLevel == 1) {
                    //무승부
                    holder.item.setBackgroundColor(Color.rgb(247, 247, 249))
                } else if (data[position].info.participants[i].win) {
                    //패
                    holder.item.setBackgroundColor(Color.rgb(255, 240, 243))
                } else {
                    //승
                    holder.item.setBackgroundColor(Color.rgb(236, 242, 255))
                }
            }
        }


    }

}