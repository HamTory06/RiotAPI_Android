package com.api.study.riot_api.ui.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.api.study.riot_api.data.model.dto.MatchInformationDto
import com.api.study.riot_api.data.network.retrofit.client.ClientRetrofit
import com.api.study.riot_api.databinding.ItemRecyclerviewLolBinding
import com.api.study.riot_api.ui.activity.MainActivity
import com.bumptech.glide.Glide
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LolMatchListAdapter(private val matchList: List<MatchInformationDto>): RecyclerView.Adapter<LolMatchListAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemRecyclerviewLolBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerviewLolBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = matchList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.level.text = matchList[position].championLevel.toString()
        getImage(matchList[position].championName, "champion", holder.binding.ChampionProfileIcon)
        holder.binding.K.text = matchList[position].championKill.toString()
        holder.binding.D.text = matchList[position].championDeath.toString()
        holder.binding.A.text = matchList[position].championAssist.toString()
//        getImage(matchList[position].summoner1Id.toString(), "spell", holder.binding.ChampionSpellId1)
//        getImage(matchList[position].summoner2Id.toString(), "spell", holder.binding.ChampionSpellId2)
//        getImage(matchList[position])
    }

    private fun getImage(imageFileName: String, type: String, imageView: ImageView) {
        ClientRetrofit.api.imageDownload(type,imageFileName)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val photoBytes = response.body()?.bytes()
                        if (photoBytes != null) {
                            val image = getBitmapFromBytes(photoBytes)
                            Glide.with(MainActivity.instance)
                                .load(image)
                                .into(imageView)
                        } else {
                            Log.e("ApiError", "Photo data is null.")
                        }
                    } else {
                        Log.e("ApiError", "Failed to get photo: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                }

            }
            )
    }


    fun getBitmapFromBytes(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}