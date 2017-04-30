package me.maximpestryakov.fintechmessenger.splash

import android.graphics.drawable.Animatable
import android.os.Build
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*
import me.maximpestryakov.fintechmessenger.R
import me.maximpestryakov.fintechmessenger.login.LoginActivity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import java.lang.Thread.sleep

class SplashActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            (logo.drawable as Animatable).start()
        }
        doAsync {
            sleep(2000)
            start()
        }
    }

    fun start() {
        startActivity<LoginActivity>()
        finish()
    }
}
