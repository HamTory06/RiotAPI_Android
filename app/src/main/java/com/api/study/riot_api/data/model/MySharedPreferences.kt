package com.api.study.riot_api.data.model

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences(context: Context) {
    companion object {
        const val PREFS_FILENAME = "prefs"

    }

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

}