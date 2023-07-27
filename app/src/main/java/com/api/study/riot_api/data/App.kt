package com.api.study.riot_api.data

import android.app.Application
import com.api.study.riot_api.data.model.MySharedPreferences

class App: Application() {
    companion object {
        lateinit var prefs : MySharedPreferences
    }
    override fun onCreate() {
        prefs = MySharedPreferences(applicationContext)
        super.onCreate()
    }
}