package me.maximpestryakov.fintechmessenger.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View.GONE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.activity_login.*
import me.maximpestryakov.fintechmessenger.R
import me.maximpestryakov.fintechmessenger.login.LoginTaskFragment.Companion.LoginListener
import me.maximpestryakov.fintechmessenger.navigation.NavigationActivity
import me.maximpestryakov.fintechmessenger.navigation.NavigationActivity.Companion.EXTRA_EMAIL
import org.jetbrains.anko.startActivity
import java.lang.Thread.sleep

class LoginActivity : AppCompatActivity(), LoginView, LoginListener {

    lateinit var loginTaskFragment: LoginTaskFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val fragment = supportFragmentManager.findFragmentByTag(LoginTaskFragment.TAG)
        if (fragment == null) {
            loginTaskFragment = LoginTaskFragment.newInstance
            supportFragmentManager.beginTransaction().apply {
                add(loginTaskFragment, LoginTaskFragment.TAG)
                addToBackStack(null)
            }.commit()
        } else {
            loginTaskFragment = fragment as LoginTaskFragment
        }

        if (loginTaskFragment.started) {
            showLoading()
        } else {
            showLoginForm()
        }

        login.setOnClickListener {
            showLoading()
            loginTaskFragment.start {
                sleep(0)
                email.text.toString()
            }
        }
    }

    override fun onLogin(email: String) {
        startActivity<NavigationActivity>(EXTRA_EMAIL to email)
        finish()
    }

    override fun showLoginForm() {
        email.isEnabled = true
        password.isEnabled = true
        progressBar.visibility = GONE
        login.visibility = VISIBLE
    }

    override fun showLoading() {
        email.isEnabled = false
        password.isEnabled = false
        progressBar.visibility = VISIBLE
        login.visibility = GONE
    }
}
