package com.api.study.riot_api

import android.app.Application
import com.api.study.riot_api.data.model.MySharedPreferences


class MyApplication: Application() {

    companion object{
        lateinit var preferences: MySharedPreferences
    }

    override fun onCreate() {
        preferences = MySharedPreferences(applicationContext)
        super.onCreate()
    }
}