package me.maximpestryakov.fintechmessenger

import android.app.Application
import android.content.Context
import io.realm.Realm


class App : Application() {

    companion object {
        val Context.app: App
            get() = applicationContext as App
    }

    override fun onCreate() {
        Realm.init(this);
    }
}
