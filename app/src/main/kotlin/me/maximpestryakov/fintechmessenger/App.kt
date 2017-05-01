package me.maximpestryakov.fintechmessenger

import android.app.Application
import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration
import me.maximpestryakov.fintechmessenger.model.Migration


class App : Application() {

    companion object {
        val Context.app: App
            get() = applicationContext as App
    }

    override fun onCreate() {
        Realm.init(this)

        val config = RealmConfiguration.Builder()
                .schemaVersion(2)
                .migration(Migration)
                .build()

        Realm.setDefaultConfiguration(config)
    }
}
