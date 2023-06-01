package com.api.study.riot_api.data.model

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences(context: Context) {
    companion object {
        const val PREFS_FILENAME = "prefs"
        const val PREF_KEY_MY_LOL_PUUID = "lolpuuid"
        const val PREF_KEY_MY_LOL_VERSION = "lolversion"
        const val PREF_KEY_MY_VAL_PUUID = "valpuuid"
        const val PREF_KEY_MY_VAL_VERSION = "valversion"
        const val PREF_KEY_MY_LOCALE = "locale"
    }

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)
    var lolpuuid: String?
        get() = prefs.getString(PREF_KEY_MY_LOL_PUUID, "")
        set(value) = prefs.edit().putString(PREF_KEY_MY_LOL_PUUID, value).apply()

    var lolVersion: String?
        get() = prefs.getString(PREF_KEY_MY_LOL_VERSION,"")
        set(value) = prefs.edit().putString(PREF_KEY_MY_LOL_VERSION, value).apply()

    var valpuuid: String?
        get() = prefs.getString(PREF_KEY_MY_VAL_PUUID, "")
        set(value) = prefs.edit().putString(PREF_KEY_MY_VAL_PUUID, value).apply()

    var ValVersion: String?
        get() = prefs.getString(PREF_KEY_MY_LOL_VERSION,"")
        set(value) = prefs.edit().putString(PREF_KEY_MY_LOL_VERSION, value).apply()

    var locale: String?
        get() = prefs.getString(PREF_KEY_MY_LOCALE, "")
        set(value) = prefs.edit().putString(PREF_KEY_MY_LOL_VERSION, value).apply()
}