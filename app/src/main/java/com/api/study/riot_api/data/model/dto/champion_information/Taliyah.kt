package com.api.study.riot_api.data.model.dto.champion_information

import com.api.study.riot_api.data.model.dto.champion_information.Image
import com.api.study.riot_api.data.model.dto.champion_information.Info
import com.api.study.riot_api.data.model.dto.champion_information.Stats

data class Taliyah(
    val blurb: String,
    val id: String,
    val image: Image,
    val info: Info,
    val key: String,
    val name: String,
    val partype: String,
    val stats: Stats,
    val tags: List<String>,
    val title: String,
    val version: String
)