package me.maximpestryakov.fintechmessenger.dialog_list

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface DialogListView : MvpView {

    @StateStrategyType(value = OneExecutionStateStrategy::class)
    fun initDialogList()

    @StateStrategyType(value = OneExecutionStateStrategy::class)
    fun updateDialogList()
}
