package me.maximpestryakov.fintechmessenger

import android.app.Application
import android.content.Context
import com.raizlabs.android.dbflow.config.FlowManager
import java.util.*

class App : Application() {

    companion object {
        val Context.app: App
            get() = applicationContext as App
    }

    override fun onCreate() {

        resources.configuration.locale

        FlowManager.init(this)
    }
}
