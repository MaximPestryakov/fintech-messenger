package me.maximpestryakov.fintechmessenger.dialog_list

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.raizlabs.android.dbflow.kotlinextensions.save
import com.raizlabs.android.dbflow.kotlinextensions.update
import me.maximpestryakov.fintechmessenger.model.Dialog
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

@InjectViewState
class DialogListPresenter : MvpPresenter<DialogListView>() {

    init {
        viewState.initDialogList()
        viewState.updateDialogList()
    }

    fun onAddDialog() = doAsync {
        val dialog = Dialog()
        dialog.save()
        dialog.apply {
            title = "Dialog #$id"
            description = "Description #$id"
        }.update()
        uiThread {
            viewState.updateDialogList()
        }
    }
}
