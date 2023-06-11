package com.api.study.riot_api.ui.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.api.study.riot_api.OnItemClickListener
import com.api.study.riot_api.R
import com.api.study.riot_api.data.model.MySharedPreferences
import com.api.study.riot_api.data.network.retrofit.lol.response.user_matches_response.UserMatchesResponse
import com.api.study.riot_api.data.network.riotapi.RiotAPI
import com.api.study.riot_api.databinding.ItemRecyclerviewLolBinding
import com.api.study.riot_api.ui.activity.LOLBaseActivity
import com.api.study.riot_api.viewModel.activity.LOLStatsSearchViewModel

class LOLRecyclerAdapter(
    var data: ArrayList<UserMatchesResponse>,
    val context: Context,
    private val clickListener: OnItemClickListener
) :
    RecyclerView.Adapter<LOLRecyclerAdapter.MyViewHolder>() {

    private val riotAPI = RiotAPI()
    private val mainViewModel = LOLStatsSearchViewModel()
    class MyViewHolder(private val binding: ItemRecyclerviewLolBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val championProfileIcon = binding.ChampionProfileIcon
        val championLevel = binding.level
        val item = binding.item
        val championSpellId1 = binding.ChampionSpellId1
        val championSpellId2 = binding.ChampionSpellId2
        val championItemId0 = binding.ChampionSpellItem0
        val championItemId1 = binding.ChampionSpellItem1
        val championItemId2 = binding.ChampionSpellItem2
        val championItemId3 = binding.ChampionSpellItem3
        val championItemId4 = binding.ChampionSpellItem4
        val championItemId5 = binding.ChampionSpellItem5
        val championItemId6 = binding.ChampionSpellItem6
        val championKill = binding.K
        val championDeath = binding.D
        val championAssist = binding.A
        val laterTime = binding.laterTime

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemRecyclerviewLolBinding: ItemRecyclerviewLolBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_recyclerview_lol, parent, false)
        return MyViewHolder(itemRecyclerviewLolBinding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val version =
            MySharedPreferences(LOLBaseActivity.ApplicationContext()).lolVersion.toString()

        holder.championProfileIcon.setOnClickListener {
            clickListener.onClickchampionProfileIcon(position)
        }
        holder.item.setOnClickListener {
            clickListener.onClickItem(position)
        }

        for (i in 0..9) {
            if (data[position].info.participants[i].puuid == MySharedPreferences(
                    LOLBaseActivity.ApplicationContext()
                ).lolpuuid
            ) {
                when (data[position].info.participants[i].summoner1Id.toString()) {
                    "21" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId1,
                        "SummonerBarrier",
                        version
                    )

                    "1" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId2,
                        "SummonerBoost",
                        version
                    )

                    "14" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId1,
                        "SummonerDot",
                        version
                    )

                    "3" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId1,
                        "SummonerExhaust",
                        version
                    )

                    "4" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId1,
                        "SummonerFlash",
                        version
                    )

                    "6" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId1,
                        "SummonerHaste",
                        version
                    )

                    "7" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId1,
                        "SummonerHeal",
                        version
                    )

                    "13" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId1,
                        "SummonerMana",
                        version
                    )

                    "30" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId1,
                        "SummonerPoroRecall",
                        version
                    )

                    "32" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId1,
                        "SummonerSnowball",
                        version
                    )

                    "11" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId1,
                        "SummonerSmite",
                        version
                    )

                    "31" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId1,
                        "SummonerPoroThrow",
                        version
                    )

                    "39" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId1,
                        "SummonerSnowball",
                        version
                    )

                    "12" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId1,
                        "SummonerTeleport",
                        version
                    )

                    "54" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId1,
                        "Summoner_UltBookPlaceholder",
                        version
                    )

                    "55" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId1,
                        "Summoner_UltBookSmitePlaceholder",
                        version
                    )
                }

                when (data[position].info.participants[i].summoner2Id.toString()) {
                    "21" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId2,
                        "SummonerBarrier",
                        version
                    )

                    "1" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId2,
                        "SummonerBoost",
                        version
                    )

                    "14" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId2,
                        "SummonerDot",
                        version
                    )

                    "3" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId2,
                        "SummonerExhaust",
                        version
                    )

                    "4" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId2,
                        "SummonerFlash",
                        version
                    )

                    "6" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId2,
                        "SummonerHaste",
                        version
                    )

                    "7" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId2,
                        "SummonerHeal",
                        version
                    )

                    "13" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId2,
                        "SummonerMana",
                        version
                    )

                    "30" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId2,
                        "SummonerPoroRecall",
                        version
                    )

                    "32" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId2,
                        "SummonerSmite",
                        version
                    )

                    "11" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId2,
                        "SummonerSnowURFSnowball_Mark",
                        version
                    )

                    "31" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId2,
                        "SummonerPoroThrow",
                        version
                    )

                    "39" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId2,
                        "SummonerSnowball",
                        version
                    )

                    "12" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId2,
                        "SummonerTeleport",
                        version
                    )

                    "54" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId2,
                        "Summoner_UltBookPlaceholder",
                        version
                    )

                    "55" -> riotAPI.getImageChampionSpell(
                        holder.championSpellId2,
                        "Summoner_UltBookSmitePlaceholder",
                        version
                    )
                }

                riotAPI.getImageChampionProfileIcon(
                    holder.championProfileIcon,
                    data[position].info.participants[i].championName,
                    version
                )
                riotAPI.getImageChampionItem(
                    holder.championItemId0,
                    data[position].info.participants[i].item0.toString(),
                    version
                )
                riotAPI.getImageChampionItem(
                    holder.championItemId1,
                    data[position].info.participants[i].item1.toString(),
                    version
                )
                riotAPI.getImageChampionItem(
                    holder.championItemId2,
                    data[position].info.participants[i].item2.toString(),
                    version
                )
                riotAPI.getImageChampionItem(
                    holder.championItemId3,
                    data[position].info.participants[i].item3.toString(),
                    version
                )
                riotAPI.getImageChampionItem(
                    holder.championItemId4,
                    data[position].info.participants[i].item4.toString(),
                    version
                )
                riotAPI.getImageChampionItem(
                    holder.championItemId5,
                    data[position].info.participants[i].item5.toString(),
                    version
                )
                riotAPI.getImageChampionItem(
                    holder.championItemId6,
                    data[position].info.participants[i].item6.toString(),
                    version
                )

                holder.championLevel.text =
                    data[position].info.participants[i].champLevel.toString()

                holder.championKill.text = data[position].info.participants[i].kills.toString()
                holder.championDeath.text = data[position].info.participants[i].deaths.toString()
                holder.championAssist.text = data[position].info.participants[i].assists.toString()

                holder.laterTime.text =
                    mainViewModel.getTimeAfterGameOver(data[position].info.gameEndTimestamp)

                if (data[position].info.participants[i].win && data[position].info.participants[i].champLevel < 3) {
                    //무승부
                    holder.item.setBackgroundColor(Color.rgb(247, 247, 249))
                } else if (!data[position].info.participants[i].win) {
                    //패
                    holder.item.setBackgroundColor(Color.rgb(255, 240, 243))
                } else {
                    //승
                    holder.item.setBackgroundColor(Color.rgb(236, 242, 255))
                }
            }
        }
    }
    fun on(){
        Log.d("상태","클릭")
    }




}