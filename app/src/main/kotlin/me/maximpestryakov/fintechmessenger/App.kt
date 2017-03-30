package me.maximpestryakov.fintechmessenger

import android.app.Application
import android.content.Context

class App : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        fun from(context: Context) = context.applicationContext as App
    }
}
