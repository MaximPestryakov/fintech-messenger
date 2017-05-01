package me.maximpestryakov.fintechmessenger.dialog_list

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.realm.Realm
import me.maximpestryakov.fintechmessenger.model.Dialog

@InjectViewState
class DialogListPresenter : MvpPresenter<DialogListView>() {

    private val realm = Realm.getDefaultInstance()

    override fun attachView(view: DialogListView) {
        super.attachView(view)
        viewState.updateDialogList()
    }

    fun onAddDialog() {
        realm.executeTransactionAsync({ realm ->
            val lastId = realm.where(Dialog::class.java).max("id")?.toInt() ?: 0
            realm.copyToRealm(Dialog(id = lastId + 1).apply {
                title = "Dialog #$id"
                description = "Description #$id"
            })
        }, {
            viewState.updateDialogList()
        }, { error ->
            Log.e("Realm", error.message)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
