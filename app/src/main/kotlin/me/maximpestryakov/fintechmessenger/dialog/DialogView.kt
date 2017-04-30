package me.maximpestryakov.fintechmessenger.dialog

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface DialogView : MvpView {

    @StateStrategyType(value = OneExecutionStateStrategy::class)
    fun initMessageList()

    @StateStrategyType(value = OneExecutionStateStrategy::class)
    fun updateMessageList()
}
