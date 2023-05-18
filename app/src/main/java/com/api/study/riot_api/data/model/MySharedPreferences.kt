package com.api.study.riot_api.data.model

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences(context: Context) {
    val PREFS_FILENAME = "prefs"
    val PREF_KEY_MY_PUUID = "puuid"
    val PREF_KEY_MY_version = "version"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)
    var puuid: String?
        get() = prefs.getString(PREF_KEY_MY_PUUID, "")
        set(value) = prefs.edit().putString(PREF_KEY_MY_PUUID, value).apply()

    var version: String?
        get() = prefs.getString(PREF_KEY_MY_version,"")
        set(value) = prefs.edit().putString(PREF_KEY_MY_version, value).apply()
}