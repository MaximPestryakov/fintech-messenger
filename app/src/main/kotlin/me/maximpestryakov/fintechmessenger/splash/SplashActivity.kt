package me.maximpestryakov.fintechmessenger.splash

import android.app.Activity
import android.os.Bundle
import me.maximpestryakov.fintechmessenger.BuildConfig
import me.maximpestryakov.fintechmessenger.login.LoginActivity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread
import java.lang.Thread.sleep

class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (BuildConfig.DEBUG) {
            doAsync {
                sleep(0)
                uiThread { start() }
            }
        } else {
            start()
        }
    }

    fun start() {
        startActivity<LoginActivity>()
        finish()
    }
}
