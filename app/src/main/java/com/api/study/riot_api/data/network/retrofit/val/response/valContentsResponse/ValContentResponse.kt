package com.api.study.riot_api.data.network.retrofit.`val`.response.valContentsResponse

data class ValContentResponse(
    val acts: List<Act>,
    val ceremonies: List<Ceremony>,
    val characters: List<Character>,
    val charmLevels: List<CharmLevel>,
    val charms: List<Charm>,
    val chromas: List<Chroma>,
    val equips: List<Equip>,
    val gameModes: List<GameMode>,
    val maps: List<Map>,
    val playerCards: List<PlayerCard>,
    val playerTitles: List<PlayerTitle>,
    val skinLevels: List<SkinLevel>,
    val skins: List<Skin>,
    val sprayLevels: List<SprayLevel>,
    val sprays: List<Spray>,
    val version: String
)