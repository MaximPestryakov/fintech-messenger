package me.maximpestryakov.fintechmessenger.login

import android.os.Bundle
import android.support.v4.app.LoaderManager.LoaderCallbacks
import android.support.v4.content.Loader
import android.support.v7.app.AppCompatActivity
import android.view.View.GONE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.activity_login.*
import me.maximpestryakov.fintechmessenger.R
import me.maximpestryakov.fintechmessenger.navigation.NavigationActivity
import me.maximpestryakov.fintechmessenger.navigation.NavigationActivity.Companion.EXTRA_EMAIL
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity(), LoginView {

    lateinit var loginLoader: Loader<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val args = LoginLoader.getInitBundle(email.text.toString(), password.text.toString())
        loginLoader = supportLoaderManager.initLoader(0, args, object : LoaderCallbacks<String> {
            override fun onCreateLoader(id: Int, args: Bundle) = LoginLoader(this@LoginActivity, args)

            override fun onLoaderReset(loader: Loader<String>) = Unit

            override fun onLoadFinished(loader: Loader<String>, email: String) {
                startActivity<NavigationActivity>(EXTRA_EMAIL to email)
                finish()
            }
        })

        if (loginLoader.isStarted) {
            showLoading()
        } else {
            showLoginForm()
        }

        login.setOnClickListener {
            showLoading()
            supportLoaderManager.getLoader<String>(0).forceLoad()
        }
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
