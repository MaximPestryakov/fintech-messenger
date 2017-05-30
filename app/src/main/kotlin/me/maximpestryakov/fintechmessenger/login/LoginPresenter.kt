package me.maximpestryakov.fintechmessenger.login

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.lang.Thread.sleep

@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {

    init {
        viewState.showLoginForm()
    }

    fun onLogin(email: String, password: String) = doAsync {
        uiThread {
            viewState.showLoading()
        }
        sleep(1000)
        uiThread {
            viewState.loginSuccess(42, email)
        }
    }
}
