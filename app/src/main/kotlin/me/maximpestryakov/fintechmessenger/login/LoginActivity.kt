package me.maximpestryakov.fintechmessenger.login

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_login.*
import me.maximpestryakov.fintechmessenger.R
import me.maximpestryakov.fintechmessenger.navigation.NavigationActivity
import me.maximpestryakov.fintechmessenger.navigation.NavigationActivity.Companion.EXTRA_EMAIL
import me.maximpestryakov.fintechmessenger.navigation.NavigationActivity.Companion.EXTRA_USER_ID
import org.jetbrains.anko.sdk19.listeners.onClick
import org.jetbrains.anko.startActivity

class LoginActivity : MvpAppCompatActivity(), LoginView {

    @InjectPresenter
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login.onClick {
            presenter.onLogin(email.text.toString(), password.text.toString())
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

    override fun loginSuccess(userId: Int, email: String) {
        startActivity<NavigationActivity>(EXTRA_USER_ID to userId, EXTRA_EMAIL to email)
        finish()
    }

    override fun loginFailure(messageId: Int) {
        // TODO
    }
}
