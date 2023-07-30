package com.api.study.riot_api.data.model

import android.content.Context
import android.content.SharedPreferences
import com.api.study.riot_api.data.App.Companion.prefs

class MySharedPreferences(context: Context) {
    companion object {
        const val PREFS_FILENAME = "prefs"
        const val PREFS_PUUIDKEY = "puuid_key"

    }

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var puuid: String?
        get() = prefs.getString(PREFS_PUUIDKEY, "")
        set(value) = prefs.edit().putString(PREFS_PUUIDKEY, value).apply()


}