package me.maximpestryakov.fintechmessenger.login

import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface LoginView : MvpView {

    @StateStrategyType(value = SingleStateStrategy::class)
    fun showLoginForm()

    @StateStrategyType(value = SingleStateStrategy::class)
    fun showLoading()

    @StateStrategyType(value = OneExecutionStateStrategy::class)
    fun loginSuccess(userId: Int, email: String)

    @StateStrategyType(value = SingleStateStrategy::class)
    fun loginFailure(@StringRes messageId: Int)
}
