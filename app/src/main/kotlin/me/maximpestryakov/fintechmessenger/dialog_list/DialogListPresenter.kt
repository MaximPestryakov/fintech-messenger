package me.maximpestryakov.fintechmessenger.dialog_list

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.realm.Realm
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
        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                val lastId = realm.where(Dialog::class.java).max("id")?.toInt() ?: 0
                val dialog = Dialog(id = lastId + 1).apply {
                    title = "Dialog #$id"
                    description = "Description #$id"
                }
                realm.copyToRealm(dialog)
            }
        }
        uiThread {
            viewState.updateDialogList()
        }
    }
}
